name := "S99"

version := "0.1"

scalaVersion := "2.13.2"

connectInput in run := true

libraryDependencies += "com.typesafe.akka" %% "akka-http"   % "10.1.12"
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % "2.5.26"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "junit" % "junit" % "4.12"
libraryDependencies += "org.specs2" % "specs2-core_2.13" % "4.7.0"
libraryDependencies += "org.specs2" % "specs2-junit_2.13" % "4.7.0"
libraryDependencies += "org.specs2" % "specs2-mock_2.13" % "4.7.0"