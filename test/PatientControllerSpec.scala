import controllers.PatientController
import play.api.test.FakeRequest
import play.api.test.Helpers._
import play.api.mvc._

class PatientControllerSpec extends BaseSpec with Results {

  describe("index") {
    it("returns OK") {
      val result = PatientController.index()(FakeRequest())
      status(result) shouldBe OK
    }
  }

}

