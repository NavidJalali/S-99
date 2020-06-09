package Lists

import org.specs2.mutable._

class ListsSpecs extends SpecificationWithJUnit {
  "Give some list I" should {
    val empty = List.empty[Int]
    val one = List(1)
    val two = List(1, 2)
    val ten = (1 to 10).toList
    import Lists._
    "find a last element" in {
      last(empty) === None
      last(one) === Some(1)
      last(two) === Some(2)
      last(ten) === Some(10)
    }

    "find one to last element" in {
      penultimate(empty) === None
      penultimate(one) === None
      penultimate(two) === Some(1)
      penultimate(ten) === Some(9)
    }

    "find nth element" in {
      (-1 to 3).forall(nth(_, empty) === None)
      ten.forall(e => nth(e, ten) === Some(e))
    }

    "reverse it" in {
      Seq(empty, one, two, ten).forall(l => reverse(l) === l.reverse)
    }

    "check if it is a palindrome" in {
      isPalindrome(List(1, 2, 3, 2, 1)) && !isPalindrome(ten)
    }

    "flatten nested lists" in {
      (flatten(List(List(1, 1), 2, List(3, List(5, 8)))) === List(1, 1, 2, 3, 5, 8)) && flatten(empty) === empty
    }

    "eliminate consecutive elements" in {
      compress(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) ===
        List('a', 'b', 'c', 'a', 'd', 'e')
    }

    "pack consecutive elements" in {
      pack(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) ===
        List(List('a', 'a', 'a', 'a'), List('b'), List('c', 'c'), List('a', 'a'), List('d'), List('e', 'e', 'e', 'e'))
    }

    "give back run length encoding" in {
      encode(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) ===
        List((4,'a'), (1,'b'), (2,'c'), (2,'a'), (1,'d'), (4,'e'))
    }

    "give back modified run length encoding (type safe)" in {
      encodeModified(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) ===
        List(Right((4,'a')), Left('b'), Right((2,'c')), Right((2,'a')), Left('d'), Right((4,'e')))
    }

    "give back modified run length encoding (type unsafe)" in {
      unsafeEncodeModified(List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')) ===
        List((4,'a'), 'b', (2,'c'), (2,'a'), 'd', (4,'e'))
    }

    "decode run length encoded" in {
      decode(List((4, 'a'), (1, 'b'), (2, 'c'), (2, 'a'), (1, 'd'), (4, 'e'))) ===
        List('a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e')
    }
  }
}
