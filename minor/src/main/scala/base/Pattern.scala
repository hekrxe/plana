package base

/**
  * User: tanhuayou  
  * Date: 2018/7/3
  */
class Pattern {

}

object Pattern extends App {
  val regex = """([0-9]+) ([a-z]+)""".r

  val line = "12321 dsadsad"
  line match {
    case regex(num, item) => println(s"$num $item")
  }
}
