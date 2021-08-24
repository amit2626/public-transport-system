package util

import scala.util.{Failure, Try}

import org.scalatest.Inside
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers

final class CharacterIndicesConverterSpec
    extends AnyFreeSpec
    with Matchers
    with Inside {

  "it should covert character into array index" in {

    CharacterIndicesConverter.indexFromChar('A') should be(0)
    CharacterIndicesConverter.indexFromChar('Z') should be(25)
  }

  "it should fail to covert character into array index if character is out of range ('A' to 'Z')" in {

    val testData = 'a'
    inside(Try(CharacterIndicesConverter.indexFromChar(testData))) {
      case Failure(exception) =>
        exception.getMessage should be(s"Not Supported char:- $testData")
    }
  }

  "it should covert valid index (0 to 25) into character" in {

    CharacterIndicesConverter.charFromIndex(0) should be('A')
    CharacterIndicesConverter.charFromIndex(25) should be('Z')
  }

  "it should fail to covert if index is out of range 0 to 25" in {

    val testData = 555
    inside(Try(CharacterIndicesConverter.charFromIndex(testData))) {
      case Failure(exception) =>
        exception.getMessage should be(s"Not supported index:- $testData")
    }
  }
}
