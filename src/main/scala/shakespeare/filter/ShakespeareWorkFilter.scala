package shakespeare.filter

import org.apache.spark.sql.DataFrame

class ShakespeareWorkFilter extends Filter {

  override def filter(file: DataFrame): DataFrame = {

    file.filter(file("value").contains("S h a k e s p e a r e   - -"))
  }
}
