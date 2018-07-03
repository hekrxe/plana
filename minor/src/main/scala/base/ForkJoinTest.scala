package base

/**
  * User: tanhuayou  
  * Date: 2018/6/25
  */
class ForkJoinTest

object ForkJoinTest extends App {
  val pool = new collection.parallel.ForkJoinTaskSupport(new scala.concurrent.forkjoin.ForkJoinPool(2))
  for (i <- 1 to 100) {
    val range = 1 to 1000
    val par = range.par
    par.tasksupport = pool
    val r = par.map(_ * 2)
    println(r)
  }
}
