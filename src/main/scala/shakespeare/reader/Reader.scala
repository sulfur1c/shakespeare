package shakespeare.reader

import org.apache.spark.sql.DataFrame

trait Reader {
  def read(): DataFrame
}
