// SPARK_LOCAL_IP=10.85.68.28

spark-shell --driver-class-path /root/Documents/Spark/mysql-connector-java-8.0.26.jar --jars /root/Documents/Spark/mysql-connector-java-8.0.26.jar

val df = spark.read.format("jdbc").option("url", "jdbc:mysql://rbsvweb01.huels-group.net:3306/hostdaten").option("driver", "com.mysql.cj.jdbc.Driver").option("dbtable","staat").option("user", "mysqladmin").option("password", "******").load()

val output_path = "file:///root/Documents/Spark/staat/staat.json"

df.write.format("json").mode("overwrite").save(output_path)

df.createOrReplaceTempView("staat")

val sqlDF = spark.sql("SELECT staatnr, staatenbez FROM staat where staatnr between 100 and 105 order by staatnr")

sqlDF.show()

// -- tsteilestammx --

val df = spark.read.format("jdbc").option("url", "jdbc:mysql://rbsvweb01.huels-group.net:3306/hostdaten").option("driver", "com.mysql.cj.jdbc.Driver").option("dbtable","tsteilestammx").option("user", "mysqladmin").option("password", "*******").load()

df.printSchema

df.groupBy("tsterzeugnisschl").pivot("tstcoll").agg(count("tsttnr") as "count").show()

df.where("tsterzeugnisschl = 1").select("tsttnr_c","tstbez").show()