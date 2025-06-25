val jsonFile = "C:/Users/speerro/UM2024.json"

val df = spark.read.options(options).json(jsonFile)

df.printSchema

import spark.implicits._

// eq op === and =!=

val result1 = df.filter($"otp_code" === "IGUT").select("karabnr").collect()

val result2 = spark.sql("select karabnr from umdat where otp_code == 'IGUT'").collect()

val result3 = df.filter($"otp_code" === "IGUT").select("karabnr").sort()

result3.show(result3.count().toInt)
