import scala.collection.{GenSeq, GenSet, mutable}

object parCollections {
  def largestPalindrome(xs: GenSeq[Int]): Int = {
    xs.aggregate(Int.MinValue)(
      (largest, n) =>
        if (n > largest && n.toString == n.toString.reverse) n else largest,
      math.max
    )
  }

  val array: Array[Int] = 0.until(1000000).toArray

  largestPalindrome(array)


  def intesection(a: GenSet[Int], b: GenSet[Int]): Set[Int] = {
    var result = Set[Int]()
    for (x <- a) if (b.contains(x)) result += x
    result
  }

  val intesection1: Set[Int] = intesection(0.until(1000).toSet, 0.until(1000).by(4).toSet)
  val intesection2: Set[Int] = intesection(0.until(1000).par.toSet, 0.until(1000).by(4).par.toSet)
  println(intesection1.size)
  println(intesection2.size)


}