package models.dto

import play.api.libs.json.Json

/** ComplexName DTO (Data Transfer Object)
  *
  * Entity class storing rows of table ComplexNames.
  *
  * @param complexNameId Database column COMPLEX_NAME_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  * @param nameParts
  */
case class ComplexName(
    complexNameId: Option[Int],
    registryObjectKey: Long,
    value: Option[String],
    `type`: Option[String],
    lang: Option[String],
    var nameParts: List[NamePart] = Nil)

object ComplexName {
  implicit val complexNameReads = Json.reads[ComplexName]
  implicit val complexNameWrites = Json.writes[ComplexName]

  def interpret(complexNameId: Option[Int], registryObjectKey: Long, value: Option[String], `type`: Option[String], lang: Option[String]) = {
    ComplexName(complexNameId, registryObjectKey, value, `type`, lang)
  }

  def represent(e: ComplexName): Option[(Option[Int], Long, Option[String], Option[String], Option[String])] = {
    Some((e.complexNameId, e.registryObjectKey, e.value, e.`type`, e.lang))
  }
}
