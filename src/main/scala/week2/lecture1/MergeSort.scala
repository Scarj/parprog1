package week2.lecture1

import common.common.parallel
import scala.util.Sorting

object MergeSort {

  def parMergeSort(xs: Array[Int], maxDepth: Int): Unit = {
    val ys = new Array[Int](xs.length)

    def quickSort(xs: Array[Int], from: Int, len: Int): Unit = {
      val sliced = xs.slice(from, from + len)
      Sorting.quickSort(sliced)
      Array.copy(sliced, 0, xs, from, len)
    }

    def merge(src: Array[Int], target: Array[Int], from: Int, mid: Int, until: Int): Unit = {
      var left = from
      var right = mid
      var i = from
      while (left < mid && right < until) {
        while (left < mid && src(left) <= src(right)) {
          target(i) = src(left)
          i += 1
          left += 1
        }
        while (right < until && src(right) <= src(left)) {
          target(i) = src(right)
          i += 1
          right += 1
        }
      }
      while (left < mid) {
        target(i) = src(left)
        i += 1
        left += 1
      }
      while (right < until) {
        target(i) = src(right)
        i += 1
        right += 1
      }
    }

    def copy(src: Array[Int], target: Array[Int], from: Int, until: Int, depth: Int): Unit = {
      if (depth == maxDepth) {
        Array.copy(src, from, target, from, until - from)
      } else {
        val mid = (from + until) / 2
        parallel(
          copy(src, target, mid, until, depth + 1),
          copy(src, target, from, mid, depth + 1)
        )
      }
    }

    if (maxDepth % 2 != 0) copy(ys, xs, 0, xs.length, 0)


    def sort(from: Int, until: Int, depth: Int): Unit = {
      if (depth == maxDepth) {
        quickSort(xs, from, until - from)
      } else {
        val mid = (from + until) / 2
        parallel(sort(mid, until, depth + 1), sort(from, mid, depth + 1))

        val flip = (maxDepth - depth) % 2 == 0
        val src = if (flip) ys else xs
        val dst = if (flip) xs else ys
        merge(src, dst, from, mid, until)
      }
    }

    sort(0, xs.length, 0)
  }
}
