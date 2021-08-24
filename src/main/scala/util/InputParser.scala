package util

/**
 * A InputParser is design to parse the command line input.
 */
object InputParser {

  /**
   * A trait which represents query type.
   * A query can be of two types :-
   * 1. route
   * 2. nearby
   */
  sealed trait QueryType

  /**
   * Representation of route query
   *
   * Example :- "route A -> B" will be parsed to RouteQuery('A', 'B')
   *
   * @param source      source as input by user via command line
   * @param destination destination as input by user via command line
   */
  final case class RouteQuery(source: Char, destination: Char) extends QueryType

  /**
   * Representation of nearby query
   *
   * Example :- "nearby A, 130" will be parsed to NearbyQuery('A', 130.0)
   *
   * @param source     source as input by user via command line
   * @param travelTime travel time as input by user via command line
   */
  final case class NearbyQuery(source: Char, travelTime: Double)
      extends QueryType

  /**
   * Representation of graph input to make complete graph
   *
   * Example :- "A -> C: 70" will be parsed to GraphInput('A', 'C', 70.0)
   *
   * @param source      source as input by user via command line
   * @param destination destination as input by user via command line
   * @param travelTime  travel time as input by user via command line
   */
  final case class GraphInput(source: Char,
                              destination: Char,
                              travelTime: Double)

  /**
   * A function which take [[java.lang.String]] as an input parse it
   * and return [[util.InputParser.GraphInput]]
   *
   * Example :-
   * Input -> "A -> C: 70"
   * Output -> GraphInput('A', 'B', 70.0)
   *
   * @param input input string in the format "A -> C: 70".
   * @return object of [[util.InputParser.GraphInput]]
   */
  def parseGraphInput(input: String): GraphInput = {
    val indexOfDestinationSeparator = input.indexOf("->")
    val indexOfTravelTimeSeparator = input.indexOf(":")
    val source = input.substring(0, indexOfDestinationSeparator).trim.head
    val destination = input
      .substring(indexOfDestinationSeparator + 2, indexOfTravelTimeSeparator)
      .trim
      .head
    val travelTime = input
      .substring(indexOfTravelTimeSeparator + 1, input.length)
      .trim
      .toDouble

    GraphInput(source, destination, travelTime)
  }

  /**
   * A function which take [[java.lang.String]] as an input parse it
   * and return [[util.InputParser.QueryType]]
   *
   * Example :-
   * Input -> "route A -> B"
   * Output -> RouteQuery('A', 'B')
   *
   * Input -> "nearby A, 130"
   * Output -> NearbyQuery('A', 130.0)
   *
   * @param input input string in the format "nearby A, 130" or "route A -> B".
   * @return object of [[util.InputParser.QueryType]]
   */
  def parseQueryInput(input: String): QueryType = {

    if (input.contains("route")) {
      val temp = input.split(" -> ")
      val destination = temp.tail.head.trim.head
      val source = temp.head.split(" ").tail.head.trim.head
      RouteQuery(source, destination)
    } else {
      val temp = input.split(", ")
      val travelTime = temp.tail.head.trim.toDouble
      val source = temp.head.split(" ").tail.head.trim.head
      NearbyQuery(source, travelTime)
    }
  }
}
