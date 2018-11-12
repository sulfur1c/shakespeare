package shakespeare.reader
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

class FileTextReader(sc: SparkContext) extends Reader {
  override def read(): RDD[String] = {
    sc.textFile("src/main/resources/shakespeare/*/*")
  }
}
