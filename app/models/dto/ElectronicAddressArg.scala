package models.dto

import play.api.libs.json.Json

/** ElectronicAddressArg DTO (Data Transfer Object)
  *
  * Entity class storing rows of table ElectronicAddressArgs.
  *
  * @param electronicAddressArgId Database column ELECTRONIC_ADDRESS_ARG_ID PrimaryKey
  * @param electronicAddressId Database column ELECTRONIC_ADDRESS_ID
  * @param name Database column NAME
  * @param required Database column REQUIRED
  * @param `type` Database column TYPE
  * @param use Database column USE
  */
case class ElectronicAddressArg(
    electronicAddressArgId: Option[Int],
    electronicAddressId: Int,
    name: Option[String],
    required: Option[Boolean],
    `type`: Option[String],
    use: Option[String])

object ElectronicAddressArg {
  implicit val electronicAddressArgReads = Json.reads[ElectronicAddressArg]
  implicit val electronicAddressArgWrites = Json.writes[ElectronicAddressArg]
}
