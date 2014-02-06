package models.dto

import play.api.libs.json.Json

/** AddressLocation DTO (Data Transfer Object)
  *
  * Entity class storing rows of table AddressLocations.
  *
  * @param addressLocationId Database column ADDRESS_LOCATION_ID PrimaryKey
  * @param locationId Database column LOCATION_ID
  * @param physicalAddresses
  * @param electronicAddresses
  */
case class AddressLocation(
    addressLocationId: Option[Int],
    locationId: Int,
    var physicalAddresses: List[PhysicalAddress] = Nil,
    var electronicAddresses: List[ElectronicAddress] = Nil)

object AddressLocation {
  implicit val addressLocationReads = Json.reads[AddressLocation]
  implicit val addressLocationWrites = Json.writes[AddressLocation]

  def interpret(addressLocationId: Option[Int], locationId: Int) = {
    AddressLocation(addressLocationId, locationId)
  }

  def represent(e: AddressLocation): Option[(Option[Int], Int)] = {
    Some((e.addressLocationId, e.locationId))
  }
}
