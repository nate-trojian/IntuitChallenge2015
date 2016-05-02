import web.GoogleScraper

import scala.io.Source

/**
 * Created by Nate on 2/5/16.
 */
object Runner {
  def main(args:Array[String]) = {
    if(args.length < 1) {
      usage
    }
    /*
     * Normally, for larger scale applications, I would use a Map/Reduce framework or Spark to process the file
     * If this was done in Spark, I would use their Latent Dirichlet allocation system
     * instead of my WordGraph implementation to parse the Google Search results.
     */
    val file = args(0)
    val trans = Source.fromFile(getClass.getResource(file).toURI).getLines().map { s =>
      val spl = s.split(",", 3)
      Transaction(spl(0), spl(1), spl(2))
    }.toList

    trans.groupBy{ s=>
      s.desc
    }

    //println(trans.mkString(","))

    //val t = -System.currentTimeMillis()
    val g:GoogleScraper = new GoogleScraper
    val test = g.search("Italian")
    //println(test)
    //println(System.currentTimeMillis()+t)
  }

  def usage = {
    throw new IllegalArgumentException("usage: <filename>")
  }
}
