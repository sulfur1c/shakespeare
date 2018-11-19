package shakespeare.filter

import org.apache.spark.sql.DataFrame

class ShakespeareWorkFilter extends Filter {

  override def filter(file: DataFrame): DataFrame = {

    file.where(file("value").contains("a"))
  }
}
