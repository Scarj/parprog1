import common.common._

object main {

  val threshold = 5

  def power(x: Int, p: Double): Int = math.exp(p * math.log(math.abs(x))).toInt

  def sumSegment(a: Array[Int], p: Double, s: Int, t: Int): Int = {
    var sum: Int = 0
    for (i <- s.until(t - 1)) {
      sum += power(a(i), p)
    }
    sum
  }

  def pNorm(a: Array[Int], p: Double): Int = {
    power(sumSegment(a, p, 0, a.length), 1 / p)
  }

  def pNormTwoPart(a: Array[Int], p: Double): Int = {
    val m = a.length / 2
    val (sum1: Int, sum2: Int) = parallel(sumSegment(a, p, 0, m), sumSegment(a, p, m, a.length))

    power(sum1 + sum2, 1 / p)
  }

  def pNormRec(a: Array[Int], p: Double): Int = {
    power(segmentRec(a, p, 0, a.length), 1 / p)
  }

  def segmentRec(a: Array[Int], p: Double, s: Int, t: Int): Int = {
    if (t - s < threshold) sumSegment(a, p, s, t)
    else {
      val m = s + (t - s) / 2
      val (sum1: Int, sum2: Int) = parallel(segmentRec(a, p, s, m), segmentRec(a, p, m, t))
      sum1 + sum2
    }
  }
}