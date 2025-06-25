// spark-sales.scala

// /opt/spark-3.5.0-bin-hadoop3/bin/spark-shell --driver-class-path /root/Documents/Spark/Drivers/mssql-jdbc-12.2.0.jre11.jar --jars /root/Documents/Spark/Drivers/mssql-jdbc-12.2.0.jre11.jar -i /root/Documents/Spark/spark-sales.scala

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

var now = LocalDateTime.now()
val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss")
var formattedDateTime = now.format(formatter)

println(s"-- load Spark/Input/spark-sales.json at $formattedDateTime")

val optDatabase = sys.env.get("RB_PRODUCTION_INSIGHT")
// val optDatabase = sys.env.get("RB_DEVELOPMENT_INSIGHT")

if (optDatabase.isEmpty) {
  println("-- no configuration for database found!")
  System.exit(1)
}

val url = optDatabase.get

println(s"-- connected to $url")

val driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
val user = "webservice"
val password = "dt6v42@XwUb"

val df = spark.read.format("json")
  .option("inferSchema", "true")
  .load("/root/Documents/Spark/Input/spark-sales.json")


if (df.isEmpty) {
  println("-- DataFrame is empty. Exit processing!")
  System.exit(1)
}

println("-- load RB.rb_umdat --")

val umdat = df.select("insight_ord_id",
  "insight_ven_id",
  "karabnr",
  "faktdatjj",
  "faktdatmm",
  "faktdattt",
  "faktwoche",
  "umdatsa",
  "faktdat",
  "kardfs_n",
  "umdataart",
  "karaart_1",
  "karaart_2",
  "karaart_3",
  "kargart",
  "karkdnr",
  "karprnr",
  "kardwk",
  "kadfil",
  "kadvart",
  "kadplanqu",
  "kadstaat",
  "kadland",
  "kadkreis",
  "kadabweg",
  "kadbokz_a",
  "kadmwst",
  "kadvkg_1",
  "kadvkgpro_1",
  "kadvkg_2",
  "kadvkgpro_2",
  "umdatver_1",
  "kadver_1",
  "kadverpro_1",
  "umdatver_2",
  "kadver_2",
  "kadverpro_2",
  "kadval",
  "karpg",
  "kadnww_1",
  "kadekg",
  "kadnetg",
  "kadskfr",
  "kadskto",
  "umdaterlkt",
  "kdrkkbez",
  "kdrart",
  "kdrstat",
  "kddkonz",
  "kdclief",
  "kdcrevst",
  "kdcmitgl",
  "kddvkg_1",
  "kddvkg_2",
  "karkdtxt",
  "faktart",
  "kafsw",
  "kadver_ziel",
  "kaflterm",
  "insight_auftragstyp",
  "insight_markt",
  "insight_vertriebsschiene")

val datSchema = Map("insight_ord_id" -> "INT",
  "faktdat"        -> "DATE",
  "kardfs_n"       -> "DATE",
  "umdataart"      -> "VARCHAR(1)",
  "kadplanqu"      -> "VARCHAR(6)",
  "kadbokz_a"      -> "VARCHAR(1)",
  "kdrkkbez"       -> "VARCHAR(32)",
  "kdcrevst"       -> "VARCHAR(255)",
  "karkdtxt"       -> "VARCHAR(255)",
  "kafsw"          -> "VARCHAR(2)",
  "insight_auftragstyp" -> "VARCHAR(50)",
  "insight_markt"  -> "VARCHAR(50)",
  "insight_vertriebsschiene" -> "VARCHAR(50)"
)

umdat.write.format("jdbc")
  .option("url", url)
  .option("driver", driver)
  .option("dbtable","RB.rb_umdat")
  .option("user", user)
  .option("password", password)
  .options(Map( "createTableColumnTypes" -> datSchema.map { case (col, dataType) => s"$col $dataType" }.mkString(",")))
  .mode("overwrite")
  .save()

println("-- load RB.rb_umpos --")

val explodedDF = df.withColumn("positions", explode(col("positions")))

val umpos = explodedDF.select("karabnr", "faktdatjj", "faktdatmm", "faktdattt", "umdatsa", "positions.*")
  .select("karabnr",
    "faktdatjj",
    "faktdatmm",
    "faktdattt",
    "umposnr",
    "umdatsa",
    "insight_oln_id",
    "insight_pd_id",
    "insight_cl_id",
    "insight_clv_ver_no",
    "kapprog",
    "kapmod",
    "tsrbez",
    "kapcoll",
    "kaphofa",
    "kapprgr",
    "kapzufa",
    "kapmeng",
    "umposseg",
    "umposnetg",
    "umposnww_1",
    "kapek",
    "kapcoll_a",
    "kapkalk_kl",
    "kaptrend_kl",
    "kapgew_netto",
    "kapme",
    "kapmeng_intra",
    "tsprlizenznr"
  )

val posSchema = Map("tsrbez" -> "VARCHAR(255)",
  "kaphofa" -> "VARCHAR(255)",
  "tsprlizenznr" -> "VARCHAR(255)"
)

umpos.write.format("jdbc")
  .option("url", url)
  .option("driver", driver)
  .option("dbtable","RB.rb_umpos")
  .option("user", user)
  .option("password", password)
  .options(Map( "createTableColumnTypes" -> posSchema.map { case (col, dataType) => s"$col $dataType" }.mkString(",")))
  .mode("overwrite")
  .save()

now = LocalDateTime.now()
formattedDateTime = now.format(formatter)
println(s"-- end at $formattedDateTime --")

System.exit(0)
