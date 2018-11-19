package shakespeare

import SparkAccessor.{spark, sc}

object ShakespeareApp {

  def main(args: Array[String]): Unit = {
    val shakespeareRunner = new ShakespeareRunner(sc)
    shakespeareRunner.run()
    spark.close()
  }
}