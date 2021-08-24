package algorithm

import scala.annotation.tailrec

import model.Graph
import util.CharacterIndicesConverter
import util.InputParser.RouteQuery

/**
 * A cass to calculate the shortest path using "Floyd Warshall Algorithm"
 *
 * @param graph input graph on which algo is to be applied.
 */
final class ShortestPathAlgorithm private (val graph: Graph) {

  /**
   * A variable that holds the resultant graph after applying "Floyd Warshall Algorithm"
   */
  private[this] lazy val sortedGraph: Array[Array[Int]] = {
    lazy val array: Array[Array[Double]] =
      graph.vertices.values.map(map => map.values.toArray).toArray

    lazy val outputArray = array.map(_.zipWithIndex.map {
      case (Double.PositiveInfinity, _) => -1
      case (_, index)                   => index
    })

    for (k <- array.indices) {
      for (i <- array.indices) {
        for (j <- array.indices) {
          if (
            !(array(i)(k) == Double.PositiveInfinity || array(k)(
              j) == Double.PositiveInfinity)
          ) {
            if (array(i)(j) > array(i)(k) + array(k)(j)) {
              array(i)(j) = array(i)(k) + array(k)(j)
              outputArray(i)(j) = outputArray(i)(k)
            }
          }
        }
      }
    }

    outputArray
  }

  /**
   * A method to produce the output based on the input query.
   *
   * @param routeQuery input query from user.
   * @return routed path.
   */
  def query(routeQuery: RouteQuery): List[Int] = routeQuery match {
    case RouteQuery(source, destination) =>
      @tailrec
      def compute(source: Int,
                  destination: Int,
                  output: List[Int]): List[Int] = {
        if (sortedGraph(source)(destination) == -1) {
          List.empty
        } else {
          if (source != destination) {
            compute(
              sortedGraph(source)(destination),
              destination,
              output :+ sortedGraph(source)(destination))
          } else {
            output
          }
        }
      }

      compute(
        CharacterIndicesConverter.indexFromChar(source),
        CharacterIndicesConverter.indexFromChar(destination),
        CharacterIndicesConverter.indexFromChar(source) :: Nil
      )
  }

  /**
   * A method to produce the output based on the input query.
   *
   * @param routeQuery input query from user.
   * @return routed path to a string format for example if path from A -> B is
   *         via C then output would be A -> B -> C.
   */
  def queryToString(routeQuery: RouteQuery): String =
    query(routeQuery)
      .map(CharacterIndicesConverter.charFromIndex)
      .mkString(" -> ")
}

object ShortestPathAlgorithm {

  def apply(graph: Graph): ShortestPathAlgorithm =
    new ShortestPathAlgorithm(graph)
}
