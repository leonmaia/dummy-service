package controllers

import java.util.UUID

import akka.util.Timeout
import model._
import play.api.data.validation.ValidationError
import play.api.i18n.{Lang, Messages}
import play.api.libs.json._
import play.api.libs.json.Json._
import play.api.mvc._
import play.api.{Configuration, Play}
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

  def create() = Action.async(parse.json) { request =>
    request.body.validate[Patient]
      .fold(
        errors => Future {
          BadRequest(validationErrors(errors))
        },
        patient => repository.insert(patient)
                             .map({ patientId =>
                               patient.copy(Some(patientId))
                               Created(toJson(patient))
                             }
        )
      )
  }

  def validationErrors(errors: Seq[(JsPath, Seq[ValidationError])]): JsObject = {
    implicit val defaultLang = Lang.defaultLang
    def toJsonString: Seq[JsString] = {
      errors.map {
        _._2
      }
        .flatten
        .map { error =>
        JsString(Messages(error.message, error.args: _*))
      }
    }
    if (errors.size > 1) {
      obj("messages" -> JsArray(toJsonString))
    }
    else {
      obj("message" -> toJsonString(0))
    }

  }
}

object PatientController extends Controller with PatientController {
  override def repository(): PatientRepository = ???
}

