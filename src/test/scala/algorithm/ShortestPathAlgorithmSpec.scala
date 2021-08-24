package algorithm

import model.{Graph, GraphBuilder}
import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import util.InputParser
import util.InputParser.RouteQuery

final class ShortestPathAlgorithmSpec extends AnyFreeSpec with Matchers {

  val testGraphInput: List[InputParser.GraphInput] = List(
    "A -> B: 240",
    "A -> C: 70",
    "A -> D: 120",
    "C -> B: 60",
    "D -> E: 480",
    "C -> E: 240",
    "B -> E: 210",
    "E -> A: 300").map(InputParser.parseGraphInput)

  val graphBuilder: GraphBuilder = new GraphBuilder

  testGraphInput.foreach(graphBuilder.add)

  val graph: Graph = graphBuilder.build()

  val shortestPathAlgorithm: ShortestPathAlgorithm = ShortestPathAlgorithm(
    graph)

  "ShortestPathQuery" - {

    "A -> B" in {
      shortestPathAlgorithm.queryToString(RouteQuery('A', 'B')) should be(
        "A -> C -> B")
    }
  }
}
