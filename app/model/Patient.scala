package model

import java.util.UUID

import play.api.Logger
import play.api.libs.functional.syntax._
import play.api.libs.json._

object Patient {

  implicit val patientWriter: Writes[Patient] = (
    (JsPath \ "patientId").writeNullable[UUID] and
    (JsPath \ "patientName").writeNullable[String] and
    (JsPath \ "patientAge").writeNullable[Int]
  )(unlift(unapply))

  implicit val patientReader: Reads[Patient] = (
    (JsPath \ "patientId").readNullable[UUID] and
    (JsPath \ "patientName").readNullable[String] and
    (JsPath \ "patientAge").readNullable[Int]
  )(apply _)
}

case class Patient(id: Option[UUID] = None,
                   name: Option[String] = None,
                   age: Option[Int] = None) {

  lazy val logger = Logger("model.Patient")

}
