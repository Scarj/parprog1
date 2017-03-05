object main {
  val list: List[Int] = List(1,3,8)
  list.map(x => x * x)
  list.fold(100)(_ + _)
  list.scan(100)(_ + _)

  val array: Array[Int] = Array(1,2,3,4)
  val array2: Array[Int] = Array(0,0,0,0)
}