package nlp

import scala.io.Source

/**
 * Created by Nate on 2/7/16.
 */
object Dict {
  private lazy val dict = Source.fromFile(getClass.getResource("dict.txt").toURI)
  private val values = Map(
    "N" -> 3f,
    "P" -> 1f,
    "h" -> 1f,
    "V" -> 2f,
    "t" -> 2f,
    "i" -> 2f,
    "A"	-> 2f,
    "v"	-> 2f,
    "C"	-> 1f,
    "P"	-> 1f,
    "!"	-> 1f,
    "r"	-> 2f,
    "D" -> 1f,
    "I" -> 1f,
    "o" -> 1f)
  val r = "(\\S+)\\s(.?)".r
  val map = dict.getLines().map{ s=>
    s match {
      case r(m, n) => (m, values(n))
    }
  }.toMap
}
