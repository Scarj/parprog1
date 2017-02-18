package week1

class HelloThread extends Thread {
  override def run() {
    println("Hello")
    println("world!")
  }
}