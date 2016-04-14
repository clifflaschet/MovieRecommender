//General config
name := "MovieRecommender"
version := "1.0"
scalaVersion := "2.10.5"

//Spark dependencies
libraryDependencies ++= Seq(
  ("org.apache.spark" % "spark-core_2.10" % "1.6.1").
    exclude("org.scala-lang", "scala-compiler").
    exclude("org.slf4j", "slf4j-api"),
  "org.apache.spark" % "spark-sql_2.10" % "1.6.1",
  "org.apache.spark" % "spark-mllib_2.10" % "1.6.1",
  "com.databricks" % "spark-csv_2.10" % "1.4.0"
)

//Config for jar assembly
assemblyJarName in assembly := "MovieRecommender.jar"
mainClass in assembly := some("com.laschet.cliff.movierecommender.MovieRecommender")
assemblyJarName := "MovieRecommender.jar"
val meta = """META.INF(.)*""".r
assemblyMergeStrategy in assembly := {
  case PathList("javax", "servlet", xs @ _*) => MergeStrategy.first
  case PathList(ps @ _*) if ps.last endsWith ".html" => MergeStrategy.first
  case n if n.startsWith("reference.conf") => MergeStrategy.concat
  case n if n.endsWith(".conf") => MergeStrategy.concat
  case meta(_) => MergeStrategy.discard
  case x => MergeStrategy.first
}
