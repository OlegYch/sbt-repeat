import sbt.complete.DefaultParsers._
import sbt.IO

@transient
val write = TaskKey[Unit]("write")
@transient
val check = InputKey[Unit]("check")
lazy val root = project
  .in(file("."))
  .settings(
    write := {
      IO.append(target.value / "count.txt", "a")
    },
    check := {
      val n = (Space ~> NatBasic).parsed
      val len = IO.read(target.value / "count.txt").length
      if (n != len) {
        sys.error(s"len $len != $n")
      }
      ()
    }
  )
