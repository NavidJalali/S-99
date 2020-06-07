package Main

import org.specs2.mutable._

class MainSpecs extends SpecificationWithJUnit {
  "Given some list I should be able to" should {
    val empty = List.empty[Int]
    val one = List(1)
    val two = List(1, 2)
    val ten = (1 to 10).toList
    import Main._
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
  }
}
