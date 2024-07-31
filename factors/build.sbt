ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.14"

lazy val root = (project in file("."))
  .settings(
    name := "factors",
    idePackagePrefix := Some("fpdesign.chapter6")
  )

libraryDependencies += {
  "org.scalatest" %% "scalatest-flatspec" % "3.2.19" % "test"
}