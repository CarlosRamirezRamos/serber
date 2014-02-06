package models.dto

import play.api.libs.json.Json

/** Identifier DTO (Data Transfer Object)
  *
  * Entity class storing rows of table Identifiers.
  *
  * @param identifierId Database column IDENTIFIER_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  */
case class Identifier(
    identifierId: Option[Int],
    registryObjectKey: Long,
    value: Option[String],
    `type`: Option[String])

object Identifier {
  implicit val identifierReads = Json.reads[Identifier]
  implicit val identifierWrites = Json.writes[Identifier]
}
