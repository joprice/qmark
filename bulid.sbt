
import Configurations.CompilerPlugin

name := "qmark"

organization := "com.github.joprice"

lazy val plugin = project
  .settings(
    name := "plugin",
    scalaVersion := "2.11.6",
    organization := "com.github.joprice",
    exportJars := true,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler" % scalaVersion.value,
      "org.scala-lang" % "scala-reflect" % scalaVersion.value
    ),
    scalacOptions ++= Seq(
     "-Xlog-free-terms"
    )
  )

lazy val test = project
  .dependsOn(plugin % CompilerPlugin) 
  .aggregate(plugin)
  .settings(
    autoCompilerPlugins := true,
    compile in Compile <<= (compile in Compile).dependsOn(clean),
    scalaVersion := "2.11.6"
  )

lazy val qmark = project.in(file("."))
  .settings(
    scalaVersion := "2.11.6",
    mainClass in Compile := Some("com.github.joprice.qmark.Test")
  )
  .dependsOn(test)
  .aggregate(test)
