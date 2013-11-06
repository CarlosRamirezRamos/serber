name := "serber"

version := "0.2-SNAPSHOT"

libraryDependencies ++= Seq(
  javaJdbc,
  javaEbean,
  cache
)

play.Project.playJavaSettings
