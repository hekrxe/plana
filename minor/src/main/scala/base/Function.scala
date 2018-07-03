package base

/**
  * User: tanhuayou  
  * Date: 2018/6/25
  */
class Function

object Function extends App {
  at()

  def at() {
    var a@b = 1000 //a是别名(a被绑定到b上),背后是模式匹配完成的
    b += 1 //a = 1000, b = 1001
    a += 1
    println("a = " + a + ", b = " + b)
  }

  def matchFun(): Unit = {
    abstract class Item

    case class Book(description: String, price: Double) extends Item
    //Item*: 不定参数
    case class Bundle(description: String, price: Double, items: Item*) extends Item

    def casclass_nested(person: Item) = person match {
      //使用@来引用匹配到的对象
      case Bundle(_, _, art@Book(_, _), rest@_*) => println(art.description + " : " + art.price)
      case Bundle(_, _, Book(descr, _), _*) => println("The first description is: " + descr)
      case Bundle(_, _, _, art@Book(_, _), rest@_*) => println(art.description + " : " + art.price)
      case _ => println("Oops!")
    }

    casclass_nested(Bundle(
      "111 Special's",
      30.0,
      Bundle("Hadoop", 40.0),
      Book("Scala for the Spark Developer", 69.5),
      Book("Hive", 75.5),
      Book("HBase", 32.9)
    ))

  }

  def sort(): Unit = {
    "Spark is the most exciting thing happening in big data tody"
      .split(" ")
      .sortWith((a, b) => a < b) foreach println
  }

  def bfun(): Unit = {
    def app[A, B](list: List[A])(f: A => B): List[B] = list.map(f)

    val list = (1 to 10).toList

    val ints: List[Int] = app(list)(_ + 1)
    println(ints)
  }

  def afun(): Unit = {
    def sum(a: Int, b: Int, c: Int) = a + b + c

    println(sum(1, 2, 3))

    val sum1 = sum(1, _: Int, 2)
    println(sum1(4))

    val sum2 = sum(_: Int, _: Int, 3)
    println(sum2)
    println(sum2(1, 5))

  }
}
