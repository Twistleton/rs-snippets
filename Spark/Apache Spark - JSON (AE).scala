val jsonFile = "/root/Documents/Spark/ae.json"

val options = Map("inferSchema" -> "true", "header" -> "false")

val df = spark.read.options(options).json(jsonFile)

val explodedDF = df.withColumn("aepositions", explode(col("aepositions")))

explodedDF.select("aepositions.*").select("kapmod", "tsrbez", "kapmeng", "bezug").show

val groupedDF = explodedDF.groupBy("aepositions.karprog").pivot("kadstaat").agg(sum("aepositions.aeposnww_1").alias(""))

groupedDF.orderBy("karprog").show