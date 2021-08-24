package util

/**
 * A scala object to convert array index into Char and vice versa.
 */
object CharacterIndicesConverter {

  /**
   * A function to convert array index into char.
   *
   * Example:-
   * 0 -> 'A'
   * 1 -> 'B'
   * 2 -> 'C'
   * ...
   * 25 -> 'Z'
   *
   * @param index array index
   * @throws Exception if the index lies outside the inclusive range of 0 to 25
   * @return character based on index.
   */
  @throws[Exception]
  def charFromIndex(index: Int): Char = index match {
    case x if x < 26 && x >= 0 => (65 + x).toChar
    case x                     => throw new Exception(s"Not supported index:- $x")
  }

  /**
   * A function to convert Char to index.
   *
   * Example:-
   * 'A' -> 0
   * 'B' -> 1
   * 'C' -> 2
   * ...
   * 'Z' -> 25
   *
   * @param char input character
   * @throws Exception if the char lies outside the inclusive range of 'A' to 'Z'
   * @return index based on character
   */
  @throws[Exception]
  def indexFromChar(char: Char): Int = char match {
    case x if x <= 90 && x >= 65 => x - 65
    case x                       => throw new Exception(s"Not Supported char:- $x")
  }
}
