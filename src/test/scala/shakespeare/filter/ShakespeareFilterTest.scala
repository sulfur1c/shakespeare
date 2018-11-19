package shakespeare.filter

import org.apache.spark.sql.{SQLContext, SQLImplicits}
import shakespeare.AbstractTest

class ShakespeareFilterTest extends AbstractTest{

  private object testImplicits extends SQLImplicits {
    protected override def _sqlContext: SQLContext = spark.sqlContext
  }
  import testImplicits._

  test("test Shakespeare's work") {

    /*val textToAnalyze = Array("< S\u0000h\u0000a\u0000k\u0000e\u0000s\u0000p\u0000e\u0000a\u0000r\u0000e\u0000 \u0000-\u0000- -- HAMLET, PRINCE OF DENMARK >")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareWorkFilter = new ShakespeareWorkFilter()

    assert(shakespeareWorkFilter.filter(rddTextToAnalyze).count() == 1)*/
  }

  test("test not Shakespeare's work") {

    /*val textToAnalyze = Array("< Ramon -- HAMLET, PRINCE OF DENMARK >")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareWorkFilter = new ShakespeareWorkFilter()

    assert(shakespeareWorkFilter.filter(rddTextToAnalyze).count() == 0)*/
  }
}
