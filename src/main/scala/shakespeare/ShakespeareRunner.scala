package shakespeare

import org.apache.spark.sql.SparkSession
import shakespeare.filter.ShakespeareWorkFilter
import shakespeare.reader.FileTextReader

class ShakespeareRunner(spark: SparkSession) {

  def run(): Unit = {
    val textToAnalyze = new FileTextReader(spark).read()
    println("Antes de filtrado: "+ textToAnalyze.count() + println(textToAnalyze.take(5).foreach(x => println(x))))
    // println("Nombre de fichero: " + textToAnalyze.select())
    val shakespeareWork = new ShakespeareWorkFilter().filter(textToAnalyze)
    println("Despues de filtrado: " + shakespeareWork.count())
  }
}
