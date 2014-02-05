package models.dto

import play.api.libs.json.Json

/** AddressPart DTO (Data Transfer Object)
  *
  * Entity class storing rows of table AddressParts.
  *
  * @param addressPartId Database column ADDRESS_PART_ID PrimaryKey
  * @param physicalAddressId Database column PHYSICAL_ADDRESS_ID
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  */
case class AddressPart(
    addressPartId: Option[Int],
    physicalAddressId: Int,
    value: Option[String],
    `type`: Option[String],
    lang: Option[String])

object AddressPart {
  implicit val addressPartReads = Json.reads[AddressPart]
  implicit val addressPartWrites = Json.writes[AddressPart]
}
