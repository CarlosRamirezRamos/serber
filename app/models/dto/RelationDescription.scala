package models.dto

import play.api.libs.json.Json

/** RelationDescription DTO (Data Transfer Object)
  *
  * Entity class storing rows of table RelationDescriptions.
  *
  * @param relationDescriptionId Database column RELATION_DESCRIPTION_ID PrimaryKey
  * @param relationId Database column RELATION_ID
  * @param description Database column DESCRIPTION
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  * @param url Database column URL
  */
case class RelationDescription(
    relationDescriptionId: Option[Int],
    relationId: Int,
    description: Option[String],
    `type`: Option[String],
    lang: Option[String],
    url: Option[String])

object RelationDescription {
  implicit val relationDescriptionReads = Json.reads[RelationDescription]
  implicit val relationDescriptionWrites = Json.writes[RelationDescription]
}
