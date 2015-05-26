import Configurations.CompilerPlugin

name := "qmark"

organization := "com.github.joprice"

lazy val plugin = project
  .settings(
    name := "qmark-plugin",
    version := "0.0.1",
    scalaVersion := "2.11.6",
    organization := "com.github.joprice",
    exportJars := true,
    libraryDependencies ++= Seq(
      "org.scala-lang" % "scala-compiler" % scalaVersion.value,
      "org.scala-lang" % "scala-reflect" % scalaVersion.value
    ),
    scalacOptions ++= Seq(
     "-Xlog-free-terms"
    ),
    bintrayRepository := "maven",
    bintrayOrganization in bintray := Some("joprice"),
    licenses += ("Apache-2.0", url("http://www.apache.org/licenses/LICENSE-2.0"))
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

