curl http://rbsvapplserv02:8010/api/um/status/1950/2024-01-01T00:00:00/2024-04-25T23:59:59 > um_2024.json

spark-shell --driver-class-path /root/Documents/Spark/mssql-jdbc-12.2.0.jre11.jar --jars /root/Documents/Spark/mssql-jdbc-12.2.0.jre11.jar

import org.apache.spark.sql.expressions.Window

val df = spark.read.format("json").option("inferSchema", "true").load("/root/Documents/Spark/um_2024.json")

val result = df.select(col("karabnr"), col("karkdnr"))

val stringCols = df.dtypes.filter(_._2 == "StringType").map(_._1)

val convertedDF = stringCols.foldLeft(df)((accDF, colName) => accDF.withColumn(colName, df(colName).cast("string")))

val selectedDF = convertedDF.select(myFields.map(col): _*)

result.write.format("jdbc").option("url", "jdbc:sqlserver://rbsverpdbtest01:1433;databaseName=TEST_INSIGHT;encrypt=true;trustServerCertificate=true").option("driver", "com.microsoft.sqlserver.jdbc.SQLServerDriver").option("dbtable","rb_umsatz_json").option("user", "webservice").option("password", "dt6v42@XwUb").mode("overwrite").save()

