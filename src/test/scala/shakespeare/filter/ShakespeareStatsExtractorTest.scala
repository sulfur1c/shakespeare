package shakespeare.filter

import shakespeare.{AbstractTest, ShakespeareStatsExtractor}

class ShakespeareStatsExtractorTest extends AbstractTest{

  test("test filter Shakespeare's work") {

    val textToAnalyze = Array("< Shakespeare -- -- HAMLET, PRINCE OF DENMARK >")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareStatsExtractor = new ShakespeareStatsExtractor(spark.sparkContext)

    assert(shakespeareStatsExtractor.filterShakespeareWork(rddTextToAnalyze).count() == 1)
  }

  test("test filter not Shakespeare's work") {

    val textToAnalyze = Array("< Ramon -- HAMLET, PRINCE OF DENMARK >")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareStatsExtractor = new ShakespeareStatsExtractor(spark.sparkContext)

    assert(shakespeareStatsExtractor.filterShakespeareWork(rddTextToAnalyze).count() == 0)
  }

  test("find most common words") {

    val textToAnalyze = Array("melon", "supercaliflajidisticopialidoso", "melon", "supercaliflajidisticopialidoso", "melon")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareStatsExtractor = new ShakespeareStatsExtractor(spark.sparkContext)

    assert(shakespeareStatsExtractor.findMostCommonWords(rddTextToAnalyze).take(1)(0) == ("melon", 3))
  }

  test("find longest sentences") {

    val textToAnalyze = Array("melon", "supercaliflajidisticopialidoso es mi frase preferida", "melon", "algo,que,es,largo.peroconpuntuacion")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareStatsExtractor = new ShakespeareStatsExtractor(spark.sparkContext)

    assert(shakespeareStatsExtractor.findLongestSentences(rddTextToAnalyze).take(1)(0) == ("supercaliflajidisticopialidoso es mi frase preferida", 52))
  }

  test("find longest words") {

    val textToAnalyze = Array("melon", "supercaliflajidisticopialidoso", "melon", "algo,que,es,largo.peroconpuntuacion-que no debe contar")
    val rddTextToAnalyzeRaw = spark.sparkContext.parallelize(textToAnalyze)
    val rddTextToAnalyze = rddTextToAnalyzeRaw.map(rec => ("FAKE file name", rec))
    val shakespeareStatsExtractor = new ShakespeareStatsExtractor(spark.sparkContext)

    assert(shakespeareStatsExtractor.findLongestWords(rddTextToAnalyze).take(1)(0) == ("supercaliflajidisticopialidoso", 30))
  }
}
