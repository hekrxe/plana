package base

/**
  * User: tanhuayou  
  * Date: 2018/6/25
  */
class FlattenTest

object FlattenTest extends App {
  val list: List[List[Int]] = List(List(1, 2, 3), List(4, 5, 6), List(11))
  println(list)
  println(list.flatten)

  private val l: Long = get().asInstanceOf[Long]
  println(l)

  def get(): java.lang.Long = null

}
