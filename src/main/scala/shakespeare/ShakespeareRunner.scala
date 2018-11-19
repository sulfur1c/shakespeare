package shakespeare

import org.apache.spark.SparkContext

class ShakespeareRunner(sc: SparkContext) {

  def run(): Unit = {

    val textToAnalyze = sc.wholeTextFiles("src/main/resources/shakespeare/*/*").cache()
    val shakespeareWork = textToAnalyze.filter(rec => rec._2.contains("S\u0000h\u0000a\u0000k\u0000e\u0000s\u0000p\u0000e\u0000a\u0000r\u0000e\u0000 \u0000-\u0000-"))

    println("algo:" + textToAnalyze.take(5).foreach(x => x._2))
  }
}
