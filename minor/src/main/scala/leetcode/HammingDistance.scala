package leetcode

/**
  * @author hztanhuayou
  * @date 2017/12/26
  */
object HammingDistance {
  def hammingDistance(x: Int, y: Int): Int = {
    var sum = 0
    var bitX = x
    var bitY = y
    var bit = 1

    while (bitX != 0 || bitY != 0) {
      if ((bitX & bit) != (bitY & bit)) {
        sum += 1
      }

      bitX = bitX & ~bit
      bitY = bitY & ~bit

      bit = bit << 1
    }
    sum
  }

  def judgeCircle(moves: String): Boolean = {
    var lr = 0
    var ud = 0
    moves.foreach {
      case 'L' => lr += 1
      case 'R' => lr -= 1
      case 'D' => ud -= 1
      case 'U' => ud += 1
    }
    lr == 0 && ud == 0
  }

  def arrayPairSum(nums: Array[Int]): Int = {
    scala.util.Sorting.quickSort(nums)
    0.to((nums.length - 1) / 2).foldLeft(0) { (a, i) => a + Math.min(nums(i * 2), nums(i * 2 + 1)) }
  }

  def main(args: Array[String]): Unit = {
    println(arrayPairSum(Array(1, 4, 3, 2)))
  }

}
