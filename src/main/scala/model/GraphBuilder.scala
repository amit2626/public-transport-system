package model

import scala.collection.mutable

import util.InputParser.GraphInput

/**
 * A class to build the graph
 * for example :-
 *
 * A -> B: 30
 * A -> C: 60
 * B -> C: 90
 * C -> A: 80
 *
 * Final output is :-
 *
 * Map {
 * 'A' -> Map {        'A' -> 0,                         'B' -> 30,              'C' -> 60 }
 * 'B' -> Map { 'A' -> Double.PositiveInfinity,          'B' -> 0,               'C' -> 90 }
 * 'C' -> Map {        'A' -> 80,                'B' -> Double.PositiveInfinity, 'C' -> 0 }
 * }
 */
class GraphBuilder {

  private[this] val mutableMap = mutable.Map[Char, mutable.Map[Char, Double]]()

  def add(input: GraphInput): Unit = {

    mutableMap.get(input.source) match {
      case Some(value) =>
        value += input.destination -> input.travelTime
        value += input.source -> 0
      case None =>
        mutableMap += input.source -> mutable.Map(
          input.source -> 0,
          input.destination -> input.travelTime)
    }

    mutableMap.get(input.destination) match {
      case Some(value) =>
        value += input.source -> Double.PositiveInfinity
      case None =>
        mutableMap += input.destination -> mutable.Map(
          input.source -> Double.PositiveInfinity)
    }

    mutableMap.filterNot(_._1 == input.source).foreach { case (_, valueMap) =>
      if (!valueMap.contains(input.source)) {
        valueMap += input.source -> Double.PositiveInfinity
      }
    }
  }

  def build(): Graph = {
    val sortedValuesMap = mutableMap.view.mapValues(v =>
      scala.collection.immutable.SortedMap(v.toSeq: _*))
    Graph(scala.collection.immutable.SortedMap(sortedValuesMap.toSeq: _*))
  }
}
