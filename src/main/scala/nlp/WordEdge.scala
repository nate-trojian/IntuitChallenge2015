package nlp

/**
 * Created by Nate on 2/6/16.
 */
object WordEdge {
  def apply(weight:Float, v1:WordVertex, v2:WordVertex) = {
    val first = v1.compare(v2)
    if(first > 0)
      new WordEdge(weight, v1, v2)
    else
      new WordEdge(weight, v2, v1)
  }
}

class WordEdge(weight:Float, v1:WordVertex, v2:WordVertex) {
  def getStart = v1
  def getEnd = v2
  def getWeight = weight
}
