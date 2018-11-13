package shakespeare

import org.apache.spark.SparkContext
import org.apache.spark.sql.SparkSession

/**
  * Class to set initial configurations for execution
  */
object SparkAccessor {

  val spark: SparkSession =
    SparkSession
      .builder()
      .appName("Time Usage")
      .config("spark.master", "local")
      .config("spark.hadoop.mapreduce.input.fileinputformat.input.dir.recursive", "true")
      .getOrCreate()

  val sc: SparkContext = spark.sparkContext
}
