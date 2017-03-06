import java.util.concurrent.ForkJoinTask

import common.common._

object splittersCombiners {

  trait Iterator[T] {
    def hasNext: Boolean

    def next(): T

    def foldLeft[S](z: S)(f: (S, T) => S): S = {
      var result = z
      while (hasNext) result = f(result, next())
      result
    }
  }

  trait Splitter[T] extends Iterator[T] {
    def split: Seq[Splitter[T]]

    def remaining: Int

    val threshold = 5

    def fold(z: T)(f: (T, T) => T): T = {
      if (remaining < threshold) foldLeft(z)(f)
      else {
        val children: Seq[ForkJoinTask[T]] = for (child <- split) yield task {
          child.fold(z)(f)
        }
        children.map(_.join()).foldLeft(z)(f)
      }
    }
  }

  trait Builder[A, Repr] {
    def +=(elem: A): Builder[A, Repr]

    def result: Repr
  }

  trait Traversable[T] {
    def foreach(f: T => Unit): Unit

    def newBuilder: Builder[T, Traversable[T]]

    def filter(p: T => Boolean): Traversable[T] = {
      val b = newBuilder
      for (elem <- this) if (p(elem)) b += elem
      b.result
    }
  }

  trait Combiner[A, Repr] extends Builder[A, Repr] {
    def combine(that: Combiner[A, Repr]): Combiner[A, Repr]
  }

}