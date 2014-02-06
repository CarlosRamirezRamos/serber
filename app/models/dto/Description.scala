package models.dto

import play.api.libs.json.Json

/** Description DTO (Data Transfer Object)
  *
  * Entity class storing rows of table Descriptions.
  *
  * @param descriptionId Database column DESCRIPTION_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  */
case class Description(
    descriptionId: Option[Int],
    registryObjectKey: Long,
    value: Option[String],
    `type`: Option[String],
    lang: Option[String])

object Description {
  implicit val descriptionReads = Json.reads[Description]
  implicit val descriptionWrites = Json.writes[Description]
}
