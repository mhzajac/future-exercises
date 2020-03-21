package test.examples

import examples.Simple
import org.specs2.concurrent._
import org.specs2.mutable._
import scala.concurrent.duration._

class SimpleSpec(implicit ee: ExecutionEnv) extends Specification {

    "Examples" should {

        "fetch google" in {
            Simple.fetchTitle("https://google.com") must equalTo("Test").await
        }

    }

}
