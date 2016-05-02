import java.util.Date
/**
 * Created by Nate on 2/6/16.
 */
object Transaction {
  val format = new java.text.SimpleDateFormat("yyyy-MM-dd")
  val r1 = "(.*?)\\s\\d+$".r
  val r2 = "([\\S]*) ATM \\d+-\\d+ #\\d+ WITHDRWL (.*) ID".r
  val r3 = "(.*) DES:\\s?(.*) ID:\\s?\\d+ INDN:\\s?(.*) ID:(.*) WEB".r

  def apply(date:String, des:String, amnt:String) = {
    new Transaction(format.parse(date), regex(des), amnt.toDouble)
  }

  def regex(s:String) = s match {
    case r1(i) => i
    case r2(bank, town) => bank + " " + town
    case r3(act, des, name, id) => act
    case _ => s
  }
}

case class Transaction(date:Date, desc:String, amnt:Double) {
}
