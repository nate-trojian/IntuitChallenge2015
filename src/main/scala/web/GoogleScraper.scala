package web


import java.util.concurrent.Executors

import net.ruippeixotog.scalascraper.browser.Browser
import net.ruippeixotog.scalascraper.dsl.DSL._
import net.ruippeixotog.scalascraper.scraper.ContentExtractors._
import nlp.WordGraph
import org.jsoup.nodes.Element

/**
 * Created by Nate on 2/5/16.
 */
class GoogleScraper {
  //http://www.google.com/search?q= - To search
  //<a href=...&url=[Stuff we want]&
  private val browser:Browser = new Browser
  private val executor = Executors.newFixedThreadPool(10)

  def GoogleScraper() = {
  }

  def search(s:String): WordGraph = {
    val doc = browser.get(s"http://www.google.com/search?q=$s")
    val links = (doc >> elementList(".r > a[href]")).map{
      e:Element => "q=(.*?)&".r findFirstIn e.attr("href") match {
        case Some(s:String) => s.substring(2,s.length-1)
        case None => ""
      }
    }
    //Normally this would be in futures
    val list = new Array[WordGraph](10)

    for(l <- links) {
      val temp = new WebpageProcessor(l).call
      list :+ temp
    }

    //println(list.mkString(","))

    val res = list.reduceLeft{ (i,j)=>
      println(i + " " + j)
      i.compound(j)
    }
    res
  }
}
