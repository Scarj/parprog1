package week1.lecture4

class Account(private var amount: Int = 0) {

  val uid: Int = getUniqueUid

  def getUniqueUid: Int = {
    1
  }

  private def locAndTransfer(target: Account, n: Int) = {
    this.synchronized {
      target.synchronized {
        this.amount -= n
        target.amount += n
      }
    }
  }

  def transfer(target: Account, n: Int): Unit = {
    if (this.uid < target.uid) this.locAndTransfer(target, n)
    else target.locAndTransfer(this, -n)
  }

  def getAmount: Int = this.amount
}
