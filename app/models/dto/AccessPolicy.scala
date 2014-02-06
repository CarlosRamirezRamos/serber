package models.dto

import play.api.libs.json.Json

/** AccessPolicy DTO (Data Transfer Object)
  *
  * Entity class storing rows of table AccessPolicies.
  *
  * @param accessPolicyId Database column ACCESS_POLICY_ID PrimaryKey
  * @param registryObjectKey Database column REGISTRY_OBJECT_KEY
  * @param value Database column VALUE
  */
case class AccessPolicy(
    accessPolicyId: Option[Int],
    registryObjectKey: Long,
    value: Option[String])

object AccessPolicy {
  implicit val accessPolicyReads = Json.reads[AccessPolicy]
  implicit val accessPolicyWrites = Json.writes[AccessPolicy]
}
