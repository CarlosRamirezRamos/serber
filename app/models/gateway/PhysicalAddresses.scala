package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.PhysicalAddress

/** PhysicalAddresses Gateway (Table Data Gateway)
  *
  * Table description of table TBL_PHYSICAL_ADDRESSES
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class PhysicalAddresses extends Table[PhysicalAddress]("TBL_PHYSICAL_ADDRESSES") {

  /** Database column PHYSICAL_ADDRESS_ID PrimaryKey */
  def physicalAddressId: Column[Int] = column[Int]("PHYSICAL_ADDRESS_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column ADDRESS_LOCATION_ID */
  def addressLocationId: Column[Int] = column[Int]("ADDRESS_LOCATION_ID", O.DBType("integer"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database column LANG */
  def lang: Column[Option[String]] = column[Option[String]]("LANG", O.DBType("varchar(64)"))

  /** Database default projection */
  def * = physicalAddressId.? ~ addressLocationId ~ `type` ~ lang <> (PhysicalAddress.interpret _, PhysicalAddress.represent _)

  /** Database insert projection */
  def forInsert = addressLocationId ~ `type` ~ lang <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.physicalAddressId)

  /** Database finder-by-address-location-id */
  //val byAddressLocationId = createFinderBy(e => e.addressLocationId)

  /** Foreign key referencing table AddressLocations
    *
    * (database name FK_TBL_PHYSICAL_ADDRESSES__ADDRESS_LOCATIONS)
    */
  def addressLocations = foreignKey("FK_TBL_PHYSICAL_ADDRESSES__ADDRESS_LOCATIONS", addressLocationId, AddressLocations)(r => r.addressLocationId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(physicalAddress: PhysicalAddress): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning physicalAddressId
    projection.insert(physicalAddress)
  }

  private def interpret(addressLocationId: Int, `type`: Option[String], lang: Option[String]): PhysicalAddress = {
    PhysicalAddress(None, addressLocationId, `type`, lang)
  }

  private def represent(e: PhysicalAddress): Option[(Int, Option[String], Option[String])] = {
    Some((e.addressLocationId, e.`type`, e.lang))
  }
}

object PhysicalAddresses extends PhysicalAddresses {

  def findAll(implicit session: Session): List[PhysicalAddress] = {
    val selection = Query(PhysicalAddresses)
    selection.list map (e => complete(e))
  }

  def findById(id: Int): Option[PhysicalAddress] = DB withSession { implicit session: Session =>
    //val selection = PhysicalAddresses.byId(id)
    val selection = Query(PhysicalAddresses) where (e => e.physicalAddressId === id)
    selection.firstOption map (e => complete(e))
  }

  def findByAddressLocationId(id: Int): List[PhysicalAddress] = DB withSession { implicit session: Session =>
    //val selection = PhysicalAddresses.byAddressLocationId(id)
    val selection = Query(PhysicalAddresses) where (e => e.addressLocationId === id)
    selection.list map (e => complete(e))
  }

  def update(id: Int, physicalAddress: PhysicalAddress): Unit = DB withSession { implicit session: Session =>
    //val selection = PhysicalAddresses.byId(id)
    val selection = Query(PhysicalAddresses) where (e => e.physicalAddressId === id)
    val entityToUpdate = physicalAddress.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = PhysicalAddresses.byId(id)
    val selection = Query(PhysicalAddresses) where (e => e.physicalAddressId === id)
    selection.delete
  }

  private def complete(physicalAddress: PhysicalAddress): PhysicalAddress = {
    // Assume valid physicalAddressId (not None):
    val id = physicalAddress.physicalAddressId.get

    physicalAddress.addressParts = AddressParts.findByPhysicalAddressId(id)
    physicalAddress
  }
}
