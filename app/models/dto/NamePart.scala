package models.dto

import play.api.libs.json.Json

/** NamePart DTO (Data Transfer Object)
  *
  * Entity class storing rows of table NameParts.
  *
  * @param namePartId Database column NAME_PART_ID PrimaryKey
  * @param complexNameId Database column COMPLEX_NAME_ID
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  */
case class NamePart(
    namePartId: Option[Int],
    complexNameId: Int,
    value: Option[String],
    `type`: Option[String])

object NamePart {
  implicit val namePartReads = Json.reads[NamePart]
  implicit val namePartWrites = Json.writes[NamePart]
}
