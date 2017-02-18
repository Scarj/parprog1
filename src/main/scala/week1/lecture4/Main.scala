package week1.lecture4

object Main extends App {
  def startThread(a: Account, b: Account, n: Int): Thread = {
    val thread = new Thread {
      override def run(): Unit = {
        for (_ <- 0 until n) {
          a.transfer(b, 1)
        }
      }
    }
    thread.start()
    thread
  }

  val a1 = new Account(50000)
  val a2 = new Account(70000)

  val t = startThread(a1,a2, 15000)
  val s = startThread(a1,a2, 15000)

  t.join()
  s.join()

  println(a1.getAmount)
  println(a2.getAmount)
}
