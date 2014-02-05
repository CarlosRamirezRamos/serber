package models.dto

import play.api.libs.json.Json

/** ElectronicAddress DTO (Data Transfer Object)
  *
  * Entity class storing rows of table ElectronicAddresses.
  *
  * @param electronicAddressId Database column ELECTRONIC_ADDRESS_ID PrimaryKey
  * @param addressLocationId Database column ADDRESS_LOCATION_ID
  * @param value Database column VALUE
  * @param `type` Database column TYPE
  * @param electronicAddressArgs
  */
case class ElectronicAddress(
    electronicAddressId: Option[Int],
    addressLocationId: Int,
    value: Option[String],
    `type`: Option[String],
    var electronicAddressArgs: List[ElectronicAddressArg] = Nil)

object ElectronicAddress {
  implicit val electronicAddressReads = Json.reads[ElectronicAddress]
  implicit val electronicAddressWrites = Json.writes[ElectronicAddress]

  def interpret(electronicAddressId: Option[Int], addressLocationId: Int, value: Option[String], `type`: Option[String]) = {
    ElectronicAddress(electronicAddressId, addressLocationId, value, `type`)
  }

  def represent(e: ElectronicAddress): Option[(Option[Int], Int, Option[String], Option[String])] = {
    Some((e.electronicAddressId, e.addressLocationId, e.value, e.`type`))
  }
}
