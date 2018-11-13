package shakespeare.filter

import org.apache.spark.sql.DataFrame

trait Filter {
  def filter(file: DataFrame): DataFrame
}
