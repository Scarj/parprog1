package week1

object Main extends App {

  private val x = new AnyRef {}
  private var uidCount = 0L

  def getUniqueID: Long = x. synchronized {
    uidCount = uidCount + 1
    uidCount
  }

  def starThread(): Unit = {
    val t = new Thread() {
      override def run(): Unit = {
        val uids = for (_ <- 0 until 10) yield getUniqueID
        println(uids)
      }
    }
    t.start()
  }

  starThread(); starThread()
  starThread(); starThread()
}
