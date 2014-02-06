package models.dto

import play.api.libs.json.Json

/** Subject DTO (Data Transfer Object)
  *
  * Entity class storing rows of table Subjects.
  *
  * @param subjectId Database column SUBJECT_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  */
case class Subject(
    subjectId: Option[Int],
    registryObjectKey: Long,
    value: Option[String],
    `type`: Option[String],
    lang: Option[String])

object Subject {
  implicit val subjectReads = Json.reads[Subject]
  implicit val subjectWrites = Json.writes[Subject]
}
