name := "spark-sample-project"

version := "0.1"

scalaVersion := "2.12.10"

libraryDependencies ++= {
  val sparkVersion = "3.0.0"
  Seq(
    "org.apache.spark" %% "spark-core" % sparkVersion % "provided" withSources()
  )
}

assemblyMergeStrategy in assembly := {
  case PathList("META-INF", _ @ _*) => MergeStrategy.discard
  case _ => MergeStrategy.first
}

fork in run := true
javaOptions in run ++= Seq(
  "-Dlog4j.configuration=log4j.properties")
fork in Test := false
javaOptions in Test ++= Seq(
  "-Dlog4j.configuration=log4j.properties")

parallelExecution in Test := false

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in(Compile, run), runner in(Compile, run))
runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile, runner in(Compile, run))
