package models.dto

import play.api.libs.json.Json

/** SpatialLocation DTO (Data Transfer Object)
  *
  * Entity class storing rows of table SpatialLocations.
  *
  * @param spatialLocationId Database column SPATIAL_LOCATION_ID PrimaryKey
  * @param locationId Database column LOCATION_ID
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  */
case class SpatialLocation(
    spatialLocationId: Option[Int],
    locationId: Int,
    value: Option[String],
    `type`: Option[String],
    lang: Option[String])

object SpatialLocation {
  implicit val spatialLocationReads = Json.reads[SpatialLocation]
  implicit val spatialLocationWrites = Json.writes[SpatialLocation]
}
