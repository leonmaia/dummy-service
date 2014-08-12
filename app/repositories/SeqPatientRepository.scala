package repositories

import java.util.UUID

import model._
import repositories._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future, Promise}

class SeqPatientRepository(var patients: Seq[Patient], idGenerator: UUIDGenerator) extends PatientRepository {

  override def insert(patient: Patient): Future[UUID] = Future(idGenerator.generate())

}
