package shakespeare

import org.apache.spark.sql.SparkSession
import shakespeare.filter.ShakespeareWorkFilter
import shakespeare.reader.FileTextReader
import org.apache.spark.sql.functions._

class ShakespeareRunner(spark: SparkSession) {

  def run(): Unit = {
    val textToAnalyzeRaw = new FileTextReader(spark).read().cache()

    val fileNames = textToAnalyzeRaw.groupBy("input_file_name")
                                        .agg(first("value") as "value", min("row_number") as "row_number")
    val shakespeareWorkFileNames = new ShakespeareWorkFilter().filter(fileNames)

    val textToAnalyze = textToAnalyzeRaw.where(textToAnalyzeRaw("input_file_name")
                                            .isin(shakespeareWorkFileNames.collect().toList))

    textToAnalyze.select(textToAnalyze("input_file_name")).collect().foreach(println)

    println("Despues de filtrado: " + shakespeareWorkFileNames.count())
  }
}
