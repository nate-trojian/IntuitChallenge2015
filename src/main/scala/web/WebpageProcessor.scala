package web

import java.lang.{NullPointerException, IllegalArgumentException}
import java.util.concurrent.Callable

import net.ruippeixotog.scalascraper.browser.Browser
import nlp.{WordVertex, Dict, WordGraph}

/**
 * Created by Nate on 2/5/16.
 */
object WebpageProcessor {
  private lazy val dict = getClass.getResource("dict.txt")
}

class WebpageProcessor(http:String) extends Callable[WordGraph] {
  private val browser = new Browser
  //We want to do all our processing of each webpage in here
  //Look for bolded words, hyperlinks in text - Given more weight
  //Break apart sentence
  def call: WordGraph = {
    println("Help " + http)
    try {
      val doc = browser.get(http).text()
      val graph = new WordGraph
      val split = doc.split(".")
      split.foreach{ s=>
        graph.addVertex(new WordVertex(s, Dict.map.getOrElse(s, 0f)))
      }
    } catch {
      case _ => new WordGraph
    }
    new WordGraph
  }
}
