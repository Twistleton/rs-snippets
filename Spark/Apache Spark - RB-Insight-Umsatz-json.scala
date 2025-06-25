val jsonFile = "C:/Users/speerro/UM2024.json"

val df = spark.read.json(jsonFile)

val result1 = df.filter($"otp_code" === "IGUT").select("karabnr", "faktdatjj", "faktdatmm", "faktdattt", "umdatsa")

result1.show(result1.count().toInt)

df.createOrReplaceTempView("insight_umdat")

val result2 = spark.sql("select karabnr, faktdat from insight_umdat where otp_code = 'IGUT' ")

result2.foreach(println)