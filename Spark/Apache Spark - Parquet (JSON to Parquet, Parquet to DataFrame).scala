// JSON -> Parquet

val df = spark.read.format("json").option("inferSchema", "true").load("/root/Documents/Spark/ae.json")

df.write.parquet("ae.parquet")

--

// Parquet -> DataFrame

val df = spark.read.option("header", "true").option("inferSchema", "true").parquet("ae.parquet")