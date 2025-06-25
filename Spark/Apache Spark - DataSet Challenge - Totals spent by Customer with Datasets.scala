import org.apache.spark.sql.types._

case class Order(customerID: Int, productID: Int, amount: Double)

val orderSchema = new StructType().add("customerID", IntegerType, nullable = true).add("productID", IntegerType, nullable = true).add("amount", DoubleType, nullable = true)

import spark.implicits._

// Read the file as dataset
val ds =  spark.read.schema(orderSchema).csv("file:///C:/Users/speerro/Documents/IdeaProjects/SparkScalaCourse/SparkScalaCourse/data/customer-orders.csv").as[Order]

val results = ds.select("customerID", "amount").groupBy("customerID").agg(round(sum("amount"),2).alias("total_spent")).sort("total_spent").collect()

// val results = ds.select("customerID", "amount").groupBy("customerID").agg(round(sum("amount"),2).alias("sum")).sort("customerID").collect()

for (result <- results) {
  val customerID = result(0)
  val totalSpent = result(1).asInstanceOf[Double]
  println(s"$customerID $totalSpent")
}

// 45 3309,38
// 79 3790,57
// 96 3924,23
// 23 4042,65
// 99 4172,29
// 75 4178,50
// 36 4278,05
// 98 4297,26
