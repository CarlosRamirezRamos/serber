package models.dto

import play.api.libs.json.Json

/** RelatedInfo DTO (Data Transfer Object)
  *
  * Entity class storing rows of table RelatedInfos.
  *
  * @param relatedInfoId Database column RELATED_INFO_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param value Database column VALUE
  */
case class RelatedInfo(
    relatedInfoId: Option[Int],
    registryObjectKey: Long,
    value: Option[String])

object RelatedInfo {
  implicit val relatedInfoReads = Json.reads[RelatedInfo]
  implicit val relatedInfoWrites = Json.writes[RelatedInfo]
}
