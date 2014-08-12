package repositories

import java.util.UUID

class RandomUUIDGenerator extends UUIDGenerator {

  def generate(): UUID = {
    UUID.randomUUID()
  }

}
