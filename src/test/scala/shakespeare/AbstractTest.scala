package shakespeare

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfter, FunSuite}

abstract class AbstractTest extends FunSuite with BeforeAndAfter{

  var sc: SparkContext = _

  before {
    val conf = new SparkConf().setAppName(this.getClass.getName).setMaster("local[*]")
    sc = new SparkContext(conf)
  }

  after {
    sc.stop()
  }
}
