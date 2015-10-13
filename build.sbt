import org.scalajs.sbtplugin.ScalaJSPlugin
import play.PlayScala
import playscalajs.ScalaJSPlay
import sbt.Project.projectToRef
import play.PlayImport.PlayKeys._

lazy val clients = Seq(exampleClient)
lazy val scalaV = "2.11.6"

lazy val exampleServer = (project in file("example-server")).settings(
  scalaVersion := scalaV,
  routesImport += "config.Routes._",
  scalaJSProjects := clients,
  pipelineStages := Seq(scalaJSProd, gzip),
  libraryDependencies ++= Seq(
    "com.vmunier" %% "play-scalajs-scripts" % "0.1.0",
    "org.webjars" %% "webjars-play" % "2.3.0",
    "org.webjars" % "jquery" % "2.1.1"
  ),
  herokuAppName in Compile := "shrouded-forest-7969",
  EclipseKeys.skipParents in ThisBuild := false
).enablePlugins(PlayScala).
  aggregate(clients.map(projectToRef): _*)

lazy val exampleClient = (project in file("example-client")).settings(
  scalaVersion := scalaV,
  persistLauncher := true,
  persistLauncher in Test := false,
  unmanagedSourceDirectories in Compile := Seq((scalaSource in Compile).value),
  libraryDependencies ++= Seq(
    "be.doeraene" %%% "scalajs-jquery" % "0.8.0"
  )
).enablePlugins(ScalaJSPlugin, ScalaJSPlay)

// loads the jvm project at sbt startup
onLoad in Global := (Command.process("project exampleServer", _: State)) compose (onLoad in Global).value