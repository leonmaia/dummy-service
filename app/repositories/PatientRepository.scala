package repositories

import model.Patient

import scala.concurrent.Future

trait PatientRepository {

  def insert(patient: Patient): Future[Unit]
}
