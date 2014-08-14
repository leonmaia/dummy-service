package repositories

import java.util.UUID

import model._
import repositories._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{ExecutionContext, Future, Promise}

class SeqPatientRepository(var patients: Seq[Patient]) extends PatientRepository {



}
