
spark-shell --driver-class-path /root/Documents/Spark/mysql-connector-java-8.0.26.jar --jars /root/Documents/Spark/mysql-connector-java-8.0.26.jar

val titanic = spark.read.format("csv").option("header", "true").option("inferSchema", "true").option("nullValue", "?").load("/root/Documents/Spark/titanic.csv")

titanic.schema

titanic.write.format("jdbc").option("url", "jdbc:mysql://rbsvweb01.huels-group.net:3306/hostdaten").option("driver", "com.mysql.cj.jdbc.Driver").option("dbtable","titanic").option("user", "mysqladmin").option("password", "escort00RB").mode("overwrite").save()
