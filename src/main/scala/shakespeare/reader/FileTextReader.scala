package shakespeare.reader
import org.apache.spark.sql.{DataFrame, SparkSession}

class FileTextReader(spark: SparkSession) extends Reader {
  override def read(): DataFrame = {
    //TODO extract path to properties file
    spark.read.text("src/main/resources/shakespeare/*/*")
  }
}
