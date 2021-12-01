ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.0"

libraryDependencies += "com.lihaoyi" %% "os-lib" % "0.7.8"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2021"
  )
