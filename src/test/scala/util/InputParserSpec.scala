package util

import org.scalatest.freespec.AnyFreeSpec
import org.scalatest.matchers.should.Matchers
import util.InputParser.{GraphInput, NearbyQuery, RouteQuery}

final class InputParserSpec extends AnyFreeSpec with Matchers {

  "Graph Input" - {

    val graphInput = List(
      "A -> B: 240",
      "A -> C: 70",
      "A -> D: 120",
      "C -> B: 60",
      "D -> E: 480",
      "C -> E: 240",
      "B -> E: 210",
      "E -> A: 300")

    val expectedOutput = List(
      GraphInput('A', 'B', 240),
      GraphInput('A', 'C', 70),
      GraphInput('A', 'D', 120),
      GraphInput('C', 'B', 60),
      GraphInput('D', 'E', 480),
      GraphInput('C', 'E', 240),
      GraphInput('B', 'E', 210),
      GraphInput('E', 'A', 300)
    )

    "it should parse graph input string" in {

      graphInput.map(InputParser.parseGraphInput) should be(expectedOutput)
    }
  }

  "Query Input" -> {

    val routeQuery = "route A -> B"
    val expectedRouteQuery = RouteQuery('A', 'B')
    val nearbyQuery = "nearby A, 130"
    val expectedNearbyQuery = NearbyQuery('A', 130)

    "it should parse route query successfully" in {

      InputParser.parseQueryInput(routeQuery) should be(expectedRouteQuery)
    }

    "it should parse nearby query successfully" in {

      InputParser.parseQueryInput(nearbyQuery) should be(expectedNearbyQuery)
    }
  }
}
