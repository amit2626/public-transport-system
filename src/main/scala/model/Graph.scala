package model

/**
 * A class to wrap the graph
 * @param vertices map of vertices that represents graph
 */
final case class Graph(vertices: Map[Char, Map[Char, Double]])
