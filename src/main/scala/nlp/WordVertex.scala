package nlp

import scala.collection.mutable.ListBuffer

/**
 * Created by Nate on 2/6/16.
 */
object WordVertex {
  implicit def ordering[A <: WordVertex]: Ordering[A] = new Ordering[A] {
    override def compare(x: A, y: A): Int = {
      x.getWeight.compareTo(y.getWeight)
    }
  }
}

class WordVertex(desc:String, weight:Float) extends Ordered[WordVertex] {
  val adj = new ListBuffer[WordEdge]
  def getWeight = weight

  def isAdj(other:WordVertex) = {
    adj.find{e =>
      e.getEnd equals other
    }
  }

  override def equals(other:Any) = {
    if(other.isInstanceOf[WordVertex]) {
      val o = other.asInstanceOf[WordVertex]
      o.getWeight == getWeight
    }
    false
  }

  override def compare(that: WordVertex): Int = {
    Math.round(this.getWeight - that.getWeight)
  }

  def addEdge(other:WordEdge): Boolean = {
    if(this equals other.getStart) {
      adj += other
      true
    }
    false
  }
}
