package models.dto

import play.api.libs.json.Json

/** Relation DTO (Data Transfer Object)
  *
  * Entity class storing rows of table Relations.
  *
  * @param relationId Database column RELATION_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param relatedRegistryObjectKey Database column RELATED_REGISTRY_OBJECT_KEY
  * @param relationDescriptions
  */
case class Relation(
    relationId: Option[Int],
    registryObjectKey: Long,
    relatedRegistryObjectKey: Long,
    var relationDescriptions: List[RelationDescription] = Nil)

object Relation {
  implicit val relationReads = Json.reads[Relation]
  implicit val relationWrites = Json.writes[Relation]

  def interpret(relationId: Option[Int], registryObjectKey: Long, relatedRegistryObjectKey: Long) = {
    Relation(relationId, registryObjectKey, relatedRegistryObjectKey)
  }

  def represent(e: Relation): Option[(Option[Int], Long, Long)] = {
    Some((e.relationId, e.registryObjectKey, e.relatedRegistryObjectKey))
  }
}
