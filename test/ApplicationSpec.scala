import controllers.Application
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.mvc._

class ApplicationSpec extends BaseSpec with Results {

  describe("index") {
    it("returns OK") {
      val result = Application.index()(FakeRequest())
      status(result) shouldBe OK
    }
  }

}

