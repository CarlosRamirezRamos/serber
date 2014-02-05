package models.dto

import play.api.libs.json.Json

/** PhysicalAddress DTO (Data Transfer Object)
  *
  * Entity class storing rows of table PhysicalAddresses.
  *
  * @param physicalAddressId Database column PHYSICAL_ADDRESS_ID PrimaryKey
  * @param addressLocationId Database column ADDRESS_LOCATION_ID
  * @param `type` Database column TYPE
  * @param lang Database column LANG
  * @param addressParts
  */
case class PhysicalAddress(
    physicalAddressId: Option[Int],
    addressLocationId: Int,
    `type`: Option[String],
    lang: Option[String],
    var addressParts: List[AddressPart] = Nil)

object PhysicalAddress {
  implicit val physicalAddressReads = Json.reads[PhysicalAddress]
  implicit val physicalAddressWrites = Json.writes[PhysicalAddress]

  def interpret(physicalAddressId: Option[Int], addressLocationId: Int, `type`: Option[String], lang: Option[String]) = {
    PhysicalAddress(physicalAddressId, addressLocationId, `type`, lang)
  }

  def represent(e: PhysicalAddress): Option[(Option[Int], Int, Option[String], Option[String])] = {
    Some((e.physicalAddressId, e.addressLocationId, e.`type`, e.lang))
  }
}
