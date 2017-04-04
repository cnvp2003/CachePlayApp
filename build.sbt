name := """Agoda"""
organization := "com.agoda"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  cache,
  "org.scalatest" %% "scalatest" % "3.0.0" % Test,
  "net.sf.ehcache" % "ehcache" % "2.10.2.2.21",
  "joda-time" % "joda-time" % "2.9.7",
  "ch.qos.logback" % "logback-classic" % "1.1.2"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.agoda.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.agoda.binders._"
