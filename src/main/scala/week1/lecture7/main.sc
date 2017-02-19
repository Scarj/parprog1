object main {

  object task {
    def apply[B](cB: B):B = cB
  }

  def parallel[A, B](cA: => A, cB: => B): (A, B) = {
    val tB = task {cB}
    val tA: A = cA
    (tA, tB.join)
  }
}