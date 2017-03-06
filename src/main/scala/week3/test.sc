object test {

  def initializeArray(xs: Array[Int])(v: Int): Unit = {
    for (i <- (0 until xs.length).par) {
      xs(i) = v
    }
  }


//  private def computePixel(xc: Double, yc: Double, maxIterations: Int): Int = {
//    var i: Int = 0
//    var x, y = 0.0
//
//    while (x * x + y * y < 4 && i < maxIterations) {
//      x = x * x - y * y + xc
//      y = 2 * x * y * yc
//      i += 1
//    }
//    color(i)
//  }
//
//  def parRender(): Unit = {
//    for (idx <- 0.until(image.length).par) {
//      val (xc, yc) = coordinatesFor(idx)
//      image(idx) = computePixel(xc, yc, maxIterations)
//    }
//  }

}