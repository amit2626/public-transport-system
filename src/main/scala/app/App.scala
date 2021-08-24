package app

import scala.io.StdIn

import algorithm.ShortestPathAlgorithm
import model.GraphBuilder
import util.InputParser
import util.InputParser.{NearbyQuery, RouteQuery}

/**
 * Entry point of the application.
 */
object App extends scala.App {

  val numberOfInputs: Int = StdIn.readInt()
  val numberOfQueries = 2
  val Zero = 0

  val graphBuilder = new GraphBuilder

  for (_ <- Zero until numberOfInputs) {
    graphBuilder.add(InputParser.parseGraphInput(StdIn.readLine()))
  }

  val shortestPathAlgorithm = ShortestPathAlgorithm(graphBuilder.build())

  for (_ <- Zero until numberOfQueries) {

    InputParser.parseQueryInput(StdIn.readLine()) match {
      case routeQuery @ RouteQuery(source, destination) =>
        shortestPathAlgorithm.queryToString(routeQuery) match {
          case x if x.isEmpty =>
            println(s"No route from $source to $destination")
          case x => println(x)
        }

      case NearbyQuery(_, _) =>
        println("Not Supported nearby query")
    }
  }
}
