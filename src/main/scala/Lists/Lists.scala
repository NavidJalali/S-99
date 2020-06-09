package Lists

import scala.util.{Failure, Success, Try}

object Lists {

  // Find the last element of a list.
  @scala.annotation.tailrec
  def last[T](list: List[T]): Option[T] = Try(list.tail) match {
    case Failure(_) => None
    case Success(Nil) => list.headOption
    case Success(_ :: _) => last(list.tail)
  }

  // Find the last but one element of a list.
  @scala.annotation.tailrec
  def penultimate[T](list: List[T]): Option[T] = Try(list.tail.tail) match {
    case Failure(_) => None
    case Success(Nil) => list.headOption
    case Success(_ :: _) => penultimate(list.tail)
  }

  // Find the Nth element of a list.
  @scala.annotation.tailrec
  def nth[T](n: Int, list: List[T]): Option[T] =
    if (n < 1) None
    else if (n == 1) list.headOption
    else if (list.isEmpty) None
    else nth(n - 1, list.tail)

  // Find the number of elements of a list.
  def length[T](list: List[T]): Int = list.foldLeft(0)((accumulator, _) => accumulator + 1)

  // Reverse a list.
  def reverse[T](list: List[T]): List[T] = list.foldLeft(List.empty[T])((l, t) => t :: l)

  // Find out whether a list is a palindrome.
  def isPalindrome[T](list: List[T]): Boolean = list == reverse(list)

  // Flatten a nested list structure.
  def flatten(list: List[Any]): List[Any] = list flatMap {
    case l: List[_] => flatten(l)
    case v => List(v)
  }

  // Eliminate consecutive duplicates of list elements.
  def compress[T](list: List[T]): List[T] =
    list.foldRight(List.empty[T])((t, l) => if (l.nonEmpty && l.head == t) l else t :: l)

  // Pack consecutive duplicates of list elements into sub-lists.
  def pack[T](list: List[T]): List[List[T]] =
    list.foldRight(List.empty[List[T]])(
      (t, l) => if (l.isEmpty) List(List(t))
        else if (l.head.head == t) (t :: l.head) :: l.tail
        else List(t) :: l
    )

  // Run-length encoding of a list.
  def encode[T](list: List[T]): List[(Int, T)] = pack(list) map (l => (l.length, l.head))

  // Modified run-length encoding.
  def encodeModified[T](list: List[T]): List[Either[T, (Int, T)]] = encode(list) map {
    case tuple@(n, t) => if (n == 1) Left(t) else Right(tuple)
  }

  def unsafeEncodeModified[T](list: List[T]): List[Any] = encode(list) map {
    case tuple@(n, t) => if (n == 1) t else tuple
  }

  // Decode a run-length encoded list.
  def decode[T](list: List[(Int, T)]): List[T] = list flatMap {
    case (n, t) => List.fill(n)(t)
  }
}