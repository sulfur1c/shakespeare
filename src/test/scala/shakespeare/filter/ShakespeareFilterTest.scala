package shakespeare.filter

import org.apache.spark.sql.{SQLContext, SQLImplicits}
import shakespeare.AbstractTest

class ShakespeareFilterTest extends AbstractTest{

  private object testImplicits extends SQLImplicits {
    protected override def _sqlContext: SQLContext = spark.sqlContext
  }
  import testImplicits._

  test("test Shakespeare's work") {

    val textToAnalyze = Array("< Shakespeare -- HAMLET, PRINCE OF DENMARK >")
    val rddTextToAnalyze = spark.sparkContext.parallelize(textToAnalyze).toDF
    val shakespeareWorkFilter = new ShakespeareWorkFilter()

    assert(shakespeareWorkFilter.filter(rddTextToAnalyze).count() == 1)
  }

  test("test not Shakespeare's work") {

    val textToAnalyze = Array("< Ramon -- HAMLET, PRINCE OF DENMARK >")
    val rddTextToAnalyze = spark.sparkContext.parallelize(textToAnalyze).toDF
    val shakespeareWorkFilter = new ShakespeareWorkFilter()

    assert(shakespeareWorkFilter.filter(rddTextToAnalyze).count() == 0)
  }
}
