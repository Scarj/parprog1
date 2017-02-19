package week1.lecture9

import org.scalameter._

object Main extends App {
  val time: Quantity[Double] = withMeasurer(new Measurer.MemoryFootprint) measure {
    0.until(1000000).toArray
  }

  val time2: Quantity[Double] = withWarmer(new Warmer.Default) measure {
    0.until(1000000).toArray
  }

  println(s"Array init time: $time2")
  println(s"Array init memory: $time")
}
