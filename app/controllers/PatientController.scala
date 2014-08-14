package controllers


import java.util.UUID

import akka.util.Timeout
import model._
import play.api.data.validation.ValidationError
import play.api.libs.json.Json._
import play.api.libs.json._
import play.api.mvc._
import repositories._
import repositories._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}

trait PatientController {
  this: Controller =>

  implicit val timeout = Timeout(10000)

  def repository(): PatientRepository

  def index = Action {
    Ok("")
  }

  def get(id: String) = Action {
    withPatient(id) { patient => Ok(toJson(patient)) }
  }

  private def withPatient(id: String)(f: Patient => Result): Result = {
    try {
      val patientId = UUID.fromString id
      repository.find(patientId) match {
        case Some(Patient) => f(patient)
        case None => NotFound(obj("message" -> s"You shall not get the patient with $patientId identifier"))
      }
    }
    catch {
      case e: IllegalArgumentException => BadRequest(obj("message" -> e.getMessage))
      case e: Throwable => InternalServerError(obj("error" -> e.getMessage))
    }
  }
}

object PatientController extends Controller with PatientController {
  override def repository(): PatientRepository = new SeqPatientRepository(Nil)
}

