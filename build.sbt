import Dependencies._

description :=
  """
    |A simple project to implement and
    |test Shortest Path Algorithm with a
    |use case of Public Transport System
    |""".stripMargin
startYear := Some(2021)

name := "public-transport-system"
organization := "amit-organization"
version := "0.1.0-SNAPSHOT"

scalaVersion := "2.13.6"

libraryDependencies ++= testDependencies.map(_ % Test)

publishTo := Some(MavenCache("local-maven", file("./jar")))
