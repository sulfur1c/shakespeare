package shakespeare

import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfter, FunSuite}

abstract class AbstractTest extends FunSuite with BeforeAndAfter{

  var spark: SparkSession = _

  before {
    spark = SparkSession.builder().master("local").getOrCreate()
  }

  after {
    spark.sparkContext.stop()
  }
}
