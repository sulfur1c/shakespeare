package shakespeare

import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

class ShakespeareStatsExtractor(sc: SparkContext) {

  def run(): Unit = {
    //TODO extract path to properties file
    val textToAnalyze = readAndReplace("src/main/resources/shakespeare/*/*").cache()
    val shakespeareWork = filterShakespeareWork(textToAnalyze)
    val mostCommmonWords = findMostCommonWords(shakespeareWork)
    val longestSentences = findLongestSentences(shakespeareWork)
    val longestWords = findLongestWords(shakespeareWork)

    println("Most common words: " + mostCommmonWords.foreach(words => println(words._1)))
    println("Longest sentences: " + longestSentences.foreach(sentence => println(sentence._1)))
    println("Longest words: " + longestWords.foreach(words => println(words._1)))
  }

  private def readAndReplace(path: String) = {
    sc.wholeTextFiles(path).map(file => (file._1, file._2.replace("\u0000", "")))
  }

  private def filterShakespeareWork(textToAnalyze: RDD[(String, String)]) = {
    textToAnalyze.filter(file => file._2.contains("Shakespeare --"))
  }

  private def findMostCommonWords(shakespeareWork: RDD[(String, String)]) = {
    shakespeareWork.flatMap(file => file._2.split(" ")).map(word => (word, 1))
                                                       .reduceByKey((a, b) => a + b)
                                                       .takeOrdered(5)(Ordering[Int].reverse.on(rec => rec._2))
  }

  private def findLongestSentences(shakespeareWork: RDD[(String, String)]) = {
    shakespeareWork.flatMap(file => file._2.split("\\.")).flatMap(file => file.split("\\,"))
                                                         .flatMap(file => file.split("\\;"))
                                                         .flatMap(file => file.split("\r\n"))
                                                         .flatMap(file => file.split("\\?"))
                                                         .map(sentence => (sentence, sentence.length))
                                                         .takeOrdered(5)(Ordering[Int].reverse.on(rec => rec._2))
  }

  private def findLongestWords(shakespeareWork: RDD[(String, String)]) = {
    shakespeareWork.flatMap(file => file._2.split(" ")).flatMap(file => file.split("\r\n"))
                                                       .flatMap(file => file.split("-"))
                                                       .flatMap(file => file.split("//"))
                                                       .flatMap(file => file.split("\t"))
                                                       .flatMap(file => file.split("\\."))
                                                       .flatMap(file => file.split("\\="))
                                                       .flatMap(file => file.split("\\,"))
                                                       .flatMap(file => file.split("\\'"))
                                                       .flatMap(file => file.split("\\;"))
                                                       .flatMap(file => file.split("\\:"))
                                                       .map(word => (word, word.length))
                                                       .takeOrdered(5)(Ordering[Int].reverse.on(rec => rec._2))
  }

}