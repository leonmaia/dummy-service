import org.mockito.exceptions.verification.WantedButNotInvoked
import org.scalatest._
import org.scalatest.mock.MockitoSugar

import scala.concurrent.duration._

trait BaseSpec extends FunSpecLike
               with Matchers
               with MockitoSugar
               with BeforeAndAfter
               with BeforeAndAfterAll {

  def waitVerification(interval: Duration = 15.milliseconds,
                       atMost: Duration = 10.seconds)(f: => Unit) {

    def execute(amountOfTime: Duration, error: Option[Throwable]): Unit = {
      if(amountOfTime < atMost)
        try {
          Thread.sleep(interval.toMillis)
          f
        }
        catch {
          case e: WantedButNotInvoked => execute(amountOfTime + interval, Some(e))
        }
      else {
        error.map{ exception =>
          throw new WantedButNotInvoked(s"Wanted but not invoked after ${amountOfTime}. $exception")
        }
      }
    }

    execute(0.milliseconds, None)
  }
}
