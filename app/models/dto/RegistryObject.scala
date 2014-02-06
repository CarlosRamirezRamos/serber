package models.dto

import play.api.libs.json.Json

/** RegistryObject DTO (Data Transfer Object)
  *
  * Entity class storing rows of table RegistryObjects.
  *
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY PrimaryKey
  * @param registryObjectClass Database column REGISTRY_OBJECT_CLASS
  * @param `type` Database column TYPE
  * @param originatingSource Database column ORIGINATING_SOURCE
  * @param originatingSourceType Database column ORIGINATING_SOURCE_TYPE
  * @param identifiers
  * @param subjects
  * @param descriptions
  * @param relatedInfos
  * @param accessPolicies
  * @param complexNames
  * @param locations
  * @param relations
  */
case class RegistryObject(
    registryObjectKey: Option[Long],
    registryObjectClass: Option[String],
    `type`: Option[String],
    originatingSource: Option[String],
    originatingSourceType: Option[String],
    var identifiers: List[Identifier] = Nil,
    var subjects: List[Subject] = Nil,
    var descriptions: List[Description] = Nil,
    var relatedInfos: List[RelatedInfo] = Nil,
    var accessPolicies: List[AccessPolicy] = Nil,
    var complexNames: List[ComplexName] = Nil,
    var locations: List[Location] = Nil,
    var relations: List[Relation] = Nil)

object RegistryObject {
  val Nihil = RegistryObject(None, None, None, None, None)
  implicit val registryObjectReads = Json.reads[RegistryObject]
  implicit val registryObjectWrites = Json.writes[RegistryObject]

  def interpret(registryObjectKey: Option[Long], registryObjectClass: Option[String], `type`: Option[String], originatingSource: Option[String], originatingSourceType: Option[String]) = {
    RegistryObject(registryObjectKey, registryObjectClass, `type`, originatingSource, originatingSourceType)
  }

  def represent(e: RegistryObject): Option[(Option[Long], Option[String], Option[String], Option[String], Option[String])] = {
    Some((e.registryObjectKey, e.registryObjectClass, e.`type`, e.originatingSource, e.originatingSourceType))
  }
}
