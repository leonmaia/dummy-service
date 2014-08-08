package model

import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global


object Patient {

  implicit val patientWriter: Writes[Patient] = (
    (JsPath \ "patientName").writeNullable[String] and
    (JsPath \ "patientAge").writeNullable[Int]
  )(unlift(unapply))

  implicit val patientReader: Reads[Patient] = (
    (JsPath \ "patientName").readNullable[String] and
    (JsPath \ "patientAge").readNullable[Int]
  )(apply _)
}

case class Patient(name: Option[String] = None,
                   age: Option[Int] = None) {

}
