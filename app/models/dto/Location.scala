package models.dto

import play.api.libs.json.Json

/** Location DTO (Data Transfer Object)
  *
  * Entity class storing rows of table Locations.
  *
  * @param locationId Database column LOCATION_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param spatialLocations
  * @param addressLocations
  */
case class Location(
    locationId: Option[Int],
    registryObjectKey: Long,
    var spatialLocations: List[SpatialLocation] = Nil,
    var addressLocations: List[AddressLocation] = Nil)

object Location {
  implicit val locationReads = Json.reads[Location]
  implicit val locationWrites = Json.writes[Location]

  def interpret(locationId: Option[Int], registryObjectKey: Long) = {
    Location(locationId, registryObjectKey)
  }

  def represent(e: Location): Option[(Option[Int], Long)] = {
    Some((e.locationId, e.registryObjectKey))
  }
}
