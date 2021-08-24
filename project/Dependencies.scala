
import sbt._

object Dependencies {

  lazy val scalaTestVersion: String = "3.2.9"

  lazy val scalaTest: ModuleID = "org.scalatest" %% "scalatest" % scalaTestVersion

  def testDependencies: Seq[ModuleID] = Seq(scalaTest)
}
