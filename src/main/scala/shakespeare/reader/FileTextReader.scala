package shakespeare.reader
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.{DataFrame, SparkSession}
import org.apache.spark.sql.functions._

class FileTextReader(spark: SparkSession) extends Reader {
  override def read(): DataFrame = {
    //TODO extract path to properties file and inject in read methos to be reader reusable
    spark.read.text("src/main/resources/shakespeare/*/*")
      .withColumn("input_file_name", input_file_name())
      .withColumn("row_number", row_number().over(Window.partitionBy("input_file_name")
                                                      .orderBy("input_file_name")))
  }
}
