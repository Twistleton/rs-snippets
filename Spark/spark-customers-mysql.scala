// spark-customer-mysql.scala
// [rs] - 2024-11-26
// stack: Apache Spark 3.5.0
//        Scala 2.12.18

import java.net.{HttpURLConnection, URI}
import java.io.InputStreamReader
import java.io.BufferedReader
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import org.apache.spark.sql.{SparkSession, DataFrame}
import org.apache.spark.sql.types._
import scala.util.{Try,Success,Failure, Using}
import java.util.logging.Handler
import java.util.logging.{ConsoleHandler, FileHandler, Level, Logger, LogRecord, Formatter}

val currentTimestamp = LocalDateTime.now()
val timestampFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
val formattedTimestamp = currentTimestamp.format(timestampFormatter)

val logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME)
logger.setLevel(Level.ALL)
val handler = new ConsoleHandler()
handler.setLevel(Level.ALL)
logger.addHandler(handler)

val fileHandler = new FileHandler("/root/Documents/Spark/Logs/spark-customers-mysql.log", true)
fileHandler.setLevel(Level.ALL)
fileHandler.setFormatter(new SimpleLogFormatter())
logger.addHandler(fileHandler)

logger.info(s"load customers at $formattedTimestamp")

val webserviceUrl = "http://rbsvapplserv02:9000/customers"
val databaseEnvVar = "RB_PRODUCTION_MYSQL"
val driver = "com.mysql.cj.jdbc.Driver"

// create Spark session
val spark = SparkSession.builder
  .appName("SparkCustomersMySQL")
  .getOrCreate()

import spark.implicits._

val result: Either[String, String] = for {
  databaseUrl <- getDatabaseUrl(databaseEnvVar)
  jsonResponse <- fetchFromWebServiceWithRetry(webserviceUrl)
  customerDataFrame <- convertJsonToDataFrame(jsonResponse)
  processResult <- writeDataFrameWithRetry(databaseUrl, driver, customerDataFrame)
} yield processResult

result match {
  case Right(processResult) => {
    logger.info(s"successful processing: $processResult")
    System.exit(0)
  }
  case Left(err) => {
    logger.severe(s"error occurred: $err")
    System.exit(1)
  }
}

// ------------------------------------------------------------------------------------------------

def getDatabaseUrl(envVar: String): Either[String, String] = {
  sys.env.get(envVar) match {
    case Some(url) =>
      logger.info(s"connected to $url")
      Right(url)
    case None =>
      Left(s"* no configuration for database found in environment variable '$envVar'!")
  }
}

def fetchFromWebServiceWithRetry(url: String, retries: Int = 5, delayMs: Int = 5000): Either[String, String] = {
  Try {
    val uri = new URI(url)
    val connection = uri.toURL.openConnection().asInstanceOf[HttpURLConnection]

    connection.setRequestMethod("GET")
    connection.setConnectTimeout(5000)
    connection.setReadTimeout(5000)

    val responseCode = connection.getResponseCode
    if (responseCode != HttpURLConnection.HTTP_OK) {
      throw new RuntimeException(s"HTTP error code: $responseCode")
    }

    Using.resource(new BufferedReader(new InputStreamReader(connection.getInputStream))) { reader =>
      Iterator.continually(reader.readLine()).takeWhile(_ != null).mkString("\n")
    }
  } match {
    case Success(value) => Right(value.toString())
    case Failure(e) if retries > 0 =>
      logger.severe(s"Error: ${e.getMessage}. Retrying fetch from web service ($retries retries left)")
      Thread.sleep(delayMs)
      fetchFromWebServiceWithRetry(url, retries - 1, delayMs * 2)
    case Failure(e) =>
      Left(s"Failed after retries: ${e.getMessage}")
  }
}

def convertJsonToDataFrame(jsonResponse: String): Either[String, org.apache.spark.sql.DataFrame] = {
  try {
    Right(spark.read.json(Seq(jsonResponse).toDS))
  } catch {
    case e: Exception => Left(s"Error converting JSON to DataFrame: ${e.getMessage}")
  }
}

def writeDataFrameWithRetry(databaseURL: String, driver: String, customersDataFrame: DataFrame, retries: Int = 5, delayMs: Int = 5000): Either[String, String] = {

  def attemptWrite(remainingRetries: Int, delayMs: Int): Either[String, String] = {

    if (customersDataFrame.isEmpty) {
      Left("customer data frame is empty!")
    } else                          {
      logger.info(s"customer row count: ${customersDataFrame.count()}")
    }

    try {

      val customerDF = customersDataFrame.select("venID", "venCode", "venCompanyName", "venEffective", "venInactive")

      val customerSchema = Map(
        "venCode" -> "VARCHAR(255)",
        "venCompanyName" -> "VARCHAR(255)",
        "venEffective" -> "DATE",
        "venInactive" -> "DATE"
      )

      customerDF.write.format("jdbc")
        .option("url", databaseURL)
        .option("driver", driver)
        .option("dbtable", "insight_customers")
        .options(Map("createTableColumnTypes" -> customerSchema.map { case (col, dataType) => s"$col $dataType" }.mkString(",")))
        .mode("overwrite")
        .save()

      val marketsDF = customersDataFrame.withColumn("markets", explode(col("markets")))
        .select("venID", "venCode", "markets.*")
        .select("venID", "venCode", "maCode", "maDescription")

      marketsDF.write.format("jdbc")
        .option("url", databaseURL)
        .option("driver", driver)
        .option("dbtable", "insight_customer_markets")
        .mode("overwrite")
        .save()

      Right("Write successful")
    } catch {
      case e: Exception =>
        if (remainingRetries > 0) {
          logger.severe(s"Write attempt failed: ${e.getMessage}. Retrying write dataframe  ($remainingRetries retries left)")
          Thread.sleep(delayMs)
          attemptWrite(remainingRetries - 1, delayMs * 2)
        } else {
          Left(s"Failed to write DataFrame after retries: ${e.getMessage}")
        }
    }
  }

  customersDataFrame.printSchema()

  attemptWrite(retries, delayMs)
}

class SimpleLogFormatter extends Formatter {
  override def format(record: LogRecord): String = {
    // Format: [Timestamp] [Level] - Message
    val timestamp = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(record.getMillis))
    s"$timestamp [${record.getLevel}] - ${record.getMessage}\n"
  }
}