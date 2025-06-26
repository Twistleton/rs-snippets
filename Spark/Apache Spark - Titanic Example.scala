./spark-shell

val titanic = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/root/Documents/Spark/titanic.csv")
val result = titanic.filter(titanic("name").contains("Allison"))

result.count()
result.show()

val ports = spark.read.format("csv").option("header", "true").option("inferSchema", "true").load("/root/Documents/Spark/ports.csv")

titanic.createOrReplaceTempView("titanic_view")
ports.createOrReplaceTempView("ports_view")

val joinedDF = spark.sql("SELECT titanic_view.name, ports_view.description FROM titanic_view FULL OUTER JOIN ports_view ON titanic_view.embarked = ports_view.port_number where titanic_view.age between 1 and 99")

val joinedDF = spark.sql("""SELECT titanic_view.name, ports_view.description FROM titanic_view FULL OUTER JOIN ports_view ON titanic_view.embarked = ports_view.port_number where titanic_view.age between 1 and 99 and titanic_view.name like 'Ast%' """)
