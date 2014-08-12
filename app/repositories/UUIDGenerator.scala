package repositories

import java.util.UUID

trait UUIDGenerator {
  def generate(): UUID
}
