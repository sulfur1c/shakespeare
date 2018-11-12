package shakespeare

import org.apache.spark.SparkContext
import shakespeare.reader.FileTextReader

class ShakespeareRunner(sc: SparkContext) {

  def run(): Unit = {
    val textToAnalyze = new FileTextReader(sc).read()
    println(textToAnalyze.count())
    // val shakespeareWork = new Shakespefilter(textToAnalyze)
  }
}
