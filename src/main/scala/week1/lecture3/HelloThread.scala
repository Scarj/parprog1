package week1.lecture3

class HelloThread extends Thread {
  override def run() {
    println("Hello")
    println("world!")
  }
}