val json = spark.read.format("json").option("inferSchema", "true").load("/root/Documents/Insight/umkorr.json")

json.agg(sum("kadnww_1")).collect()(0)(0)

val result = json.select("karabnr", "faktdatjj", "faktdatmm", "faktdattt", "umdatsa", "kadnww_1", "kadnetg")

result.write.format("csv").save("/root/Documents/Spark/umkorr.csv")

val umposDF = json.select(col("karabnr"), col("faktdatjj"), col("faktdatmm"), col("faktdattt"), col("umdatsa"), explode(col("positions")).as("Position")).select(col("karabnr"), col("faktdatjj"), col("faktdatmm"), col("faktdattt"), col("umdatsa"),  col("Position.umposnr"), col("Position.umposnww_1"), col("Position.umposnetg"))

umposDF.write.format("csv").save("/root/Documents/Spark/umkorr_pos.csv")