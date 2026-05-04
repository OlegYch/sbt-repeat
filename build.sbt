Global / onChangedBuildSource := ReloadOnSourceChanges

ThisBuild / scalafmtOnCompile := true

ThisBuild / versionScheme := Some("early-semver")

val commonSettings = Seq(
  organization := "com.github.tkawachi",
  licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
  scmInfo := Some(
    ScmInfo(
      url("https://github.com/tkawachi/sbt-repeat/"),
      "scm:git:github.com:tkawachi/sbt-repeat.git"
    )
  ),
  scalaVersion := "2.12.14",
  crossScalaVersions := Seq(scalaVersion.value, "3.8.2"),
  (pluginCrossBuild / sbtVersion) := {
    scalaBinaryVersion.value match {
      case "2.12" => "1.5.8"
      case _      => "2.0.0-RC10"
    }
  },
  scalacOptions ++= Seq(
    "-deprecation",
    "-encoding",
    "UTF-8",
    "-feature",
    "-unchecked"
  ),
  scalacOptions ++= {
    scalaBinaryVersion.value match {
      case "2.12" =>
        Seq(
          "-release",
          "8",
          "-target:jvm-1.8",
          "-Xfatal-warnings",
          "-Xlint"
        )
      case _ =>
        Seq(
          "-release:17",
          "-Werror",
          "-Wshadow:all"
        )
    }
  }
)

lazy val root = project
  .in(file("."))
  .enablePlugins(SbtPlugin)
  .settings(commonSettings: _*)
  .settings(
    name := "sbt-repeat",
    scriptedLaunchOpts := {
      scriptedLaunchOpts.value ++
        Seq(
          "-Xmx1024M",
          "-Dplugin.version=" + version.value
        )
    },
    scriptedBufferLog := false
  )
