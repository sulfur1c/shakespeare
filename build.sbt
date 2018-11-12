name := "shakespeare"

version := "1.0"

scalaVersion := "2.11.12"

libraryDependencies += "junit" % "junit" % "4.10" % Test
libraryDependencies += "org.apache.spark" % "spark-core_2.11" % "2.4.0"
libraryDependencies += "org.apache.spark" % "spark-sql_2.11" % "2.4.0"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"
// libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
