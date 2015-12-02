import sbt._
import sbt.Keys._
import sbt.Project.projectToRef
import org.scalajs.sbtplugin.ScalaJSPlugin
import play.PlayScala
import play.PlayImport.PlayKeys._
import playscalajs.ScalaJSPlay

lazy val clients = Seq(exampleClient)
lazy val scalaV = "2.11.6"

lazy val exampleServer = (project in file("example-server")).settings(
  scalaVersion := scalaV,
  routesImport += "config.Routes._",
  scalaJSProjects := clients,
  pipelineStages := Seq(scalaJSProd, gzip),
  libraryDependencies ++= Seq(
    "com.typesafe.play" %% "play-ws" % "2.3.7",
    "com.vmunier" %% "play-scalajs-scripts" % "0.1.0",
    "org.webjars" %% "webjars-play" % "2.3.0",
    "org.webjars" % "jquery" % "2.1.1"
  ),
  herokuAppName in Compile := "scalajs-intro",
  EclipseKeys.skipParents in ThisBuild := false)
    .enablePlugins(PlayScala)
    .aggregate(clients.map(projectToRef): _*)
    .dependsOn(exampleCommonJvm)

lazy val exampleClient = (project in file("example-client")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  persistLauncher in Test := false,
  skip in packageJSDependencies := false,
  sourceMapsDirectories += exampleCommonJs.base / "..",
  unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
  libraryDependencies ++= Seq(
    "be.doeraene" %%% "scalajs-jquery" % "0.8.0",
    "com.lihaoyi" %%% "upickle" % "0.2.8"
  ))
    .enablePlugins(ScalaJSPlugin, ScalaJSPlay)
    .dependsOn(exampleCommonJs)

lazy val exampleCommon = (crossProject.crossType(CrossType.Pure) in file("example-common")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "org.scalatest" %% "scalatest" % "2.2.4",
        "org.scalaz" %% "scalaz-core" % "7.1.4"
      )
    ).
    jsConfigure(_ enablePlugins ScalaJSPlay).
    jsSettings(sourceMapsBase := baseDirectory.value / "..")

lazy val exampleCommonJvm = exampleCommon.jvm
lazy val exampleCommonJs = exampleCommon.js


// loads the jvm project at sbt startup
onLoad in Global := (Command.process("project exampleServer", _: State)) compose (onLoad in Global).value
