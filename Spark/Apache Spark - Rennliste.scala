curl http://rbsvapplserv02:8010/api/ae/2024-01-01T00:00:00/2024-03-14T23:59:59 > ae.json

spark-shell --driver-class-path /root/Documents/Spark/mysql-connector-java-8.0.26.jar --jars /root/Documents/Spark/mysql-connector-java-8.0.26.jar


import org.apache.spark.sql.expressions.Window

val df = spark.read.format("json").option("inferSchema", "true").load("/root/Documents/Spark/ae.json")

val result = df.filter(col("karaedat").between("2024-03-01", "2024-03-31")).filter("""markt == "RB" and kadstaat == 101 and kadvkg_1 <> 0 """).groupBy("kadvkg_1").agg(sum("kadnww_1")).orderBy(desc("sum(kadnww_1)")).withColumn("row_number", row_number().over(Window.orderBy(desc("sum(kadnww_1)"))))

result.write.format("jdbc").option("url", "jdbc:mysql://rbsvweb01.huels-group.net:3306/hostdaten").option("driver", "com.mysql.cj.jdbc.Driver").option("dbtable","v2_rennliste").option("user", "mysqladmin").option("password", "******").mode("overwrite").save()