package scalapipe

import simulacrum._

object Pipe {
  implicit class Pipe[A](val a: A) extends AnyVal {
    def |>[B](f: A => B) = f(a)
  }
}
