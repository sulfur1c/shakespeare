package shakespeare.reader

import org.apache.spark.rdd.RDD

trait Reader {
  def read(): RDD[String]
}
