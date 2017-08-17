package scalapipe

import org.scalatest.{FreeSpec, Matchers}
import org.scalacheck.Prop.forAll
import org.scalacheck._
import org.scalatest.prop.Checkers

class PipeTest extends FreeSpec with Matchers with Checkers {

  def checkGenerator[A](arbGen: Gen[A])(implicit cogen: Cogen[A]) =
    check(forAll(Gen.function1[A, A](arbGen), arbGen) { (f, a) =>
      import Pipe._
      f(a) == (a |> f)
    })

  "Endofunctor equality" - {
    "Long" in {
      checkGenerator[Long](Arbitrary.arbLong.arbitrary)
    }
    "String" in {
      checkGenerator[String](Arbitrary.arbString.arbitrary)
    }
    "List" in {
      checkGenerator[List[Int]](Arbitrary.arbContainer[List, Int].arbitrary)
    }
  }

}
