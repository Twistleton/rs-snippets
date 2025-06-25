case class Person(id:Int, name:String, age:Int, friends:Int)

import spark.implicits._

val xs: org.apache.spark.sql.Dataset[Person] =  spark.read.option("header", "true").option("inferSchema", "true").csv("file:///C:/Users/speerro/Documents/IdeaProjects/SparkScalaCourse/SparkScalaCourse/data/fakefriends.csv").as[Person]

xs.printSchema()

xs.show()

// solution with Spark SQL

xs.createOrReplaceTempView("people")

val result = spark.sql("SELECT age, avg(friends) from people group by age")

// alternative solution with Spark core and data sets

val friendsByAge =  xs.select("age","friends")

friendsByAge.groupBy("age").agg(round(avg("friends"), scale=2).alias("friends_avg")).sort("age").show()