package nlp

import scala.collection.mutable.ListBuffer

/**
 * Created by Nate on 2/6/16.
 */
class WordGraph {
  private val verts = ListBuffer[WordVertex]()
  private val edges = ListBuffer[WordEdge]()

  def getVerts = verts

  def getEdge = edges

  def addVertex(v:WordVertex) = {
    verts += v
    verts.sorted
  }

  def addEdge(e: WordEdge) = {
    edges += e
    e.getStart addEdge e
  }

  def compound(wordGraph: WordGraph): WordGraph = {
    verts ++= wordGraph.getVerts
    edges ++= wordGraph.getEdge
    this
  }
}
