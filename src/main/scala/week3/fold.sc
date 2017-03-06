object fold {

  def sum(xs: Array[Int]): Int = {
    xs.par.sum
  }

  def max(xs: Array[Int]): Int = {
    xs.par.fold(xs.head)(math.max)
  }

  val ints: Array[Int] = Array(5, 4, 3, 1, 2, 6, 7, 8)
  max(ints)
  sum(ints)

  val strings: Array[String] = Array("paper", "rock", "paper", "scissors")

  def play(a: String, b: String): String = List(a, b).sorted match {
    case List("paper", "scissors") => "scissors"
    case List("paper", "rock") => "paper"
    case List("rock", "scissors") => "rock"
    case List(x, y) if x == y => x
    case List("", x) => x
  }

  strings.par.fold("")(play)
  play(play("paper", "rock"), play("paper", "scissors"))
  play("paper", play("rock", play("paper", "scissors")))


  val isVowel = Set('A', 'E', 'I', 'O', 'U')

  Array('E', 'P', 'F', 'L').par.aggregate(0)(
    (count, c) => if (isVowel(c)) count + 1 else count,
    _ + _
  )
}