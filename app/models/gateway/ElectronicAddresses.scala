package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.ElectronicAddress

/** ElectronicAddresses Gateway (Table Data Gateway)
  *
  * Table description of table TBL_ELECTRONIC_ADDRESSES
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class ElectronicAddresses extends Table[ElectronicAddress]("TBL_ELECTRONIC_ADDRESSES") {

  /** Database column ELECTRONIC_ADDRESS_ID PrimaryKey */
  def electronicAddressId: Column[Int] = column[Int]("ELECTRONIC_ADDRESS_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column ADDRESS_LOCATION_ID */
  def addressLocationId: Column[Int] = column[Int]("ADDRESS_LOCATION_ID", O.DBType("integer"))

  /** Database column VALUE */
  def value: Column[Option[String]] = column[Option[String]]("VALUE", O.DBType("varchar(512)"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = electronicAddressId.? ~ addressLocationId ~ value ~ `type` <> (ElectronicAddress.interpret _, ElectronicAddress.represent _)

  /** Database insert projection */
  def forInsert = addressLocationId ~ value ~ `type` <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.electronicAddressId)

  /** Database finder-by-address-location-id */
  //val byAddressLocationId = createFinderBy(e => e.addressLocationId)

  /** Foreign key referencing table AddressLocations
    *
    * (database name FK_TBL_ELECTRONIC_ADDRESSES__ADDRESS_LOCATIONS)
    */
  def addressLocations = foreignKey("FK_TBL_ELECTRONIC_ADDRESSES__ADDRESS_LOCATIONS", addressLocationId, AddressLocations)(r => r.addressLocationId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(electronicAddress: ElectronicAddress): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning electronicAddressId
    projection.insert(electronicAddress)
  }

  private def interpret(addressLocationId: Int, value: Option[String], `type`: Option[String]): ElectronicAddress = {
    ElectronicAddress(None, addressLocationId, value, `type`)
  }

  private def represent(e: ElectronicAddress): Option[(Int, Option[String], Option[String])] = {
    Some((e.addressLocationId, e.value, e.`type`))
  }
}

object ElectronicAddresses extends ElectronicAddresses {

  def findAll(implicit session: Session): List[ElectronicAddress] = {
    val selection = Query(ElectronicAddresses)
    selection.list map (e => complete(e))
  }

  def findById(id: Int): Option[ElectronicAddress] = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddresses.byId(id)
    val selection = Query(ElectronicAddresses) where (e => e.electronicAddressId === id)
    selection.firstOption map (e => complete(e))
  }

  def findByAddressLocationId(id: Int): List[ElectronicAddress] = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddresses.byAddressLocationId(id)
    val selection = Query(ElectronicAddresses) where (e => e.addressLocationId === id)
    selection.list map (e => complete(e))
  }

  def update(id: Int, electronicAddress: ElectronicAddress): Unit = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddresses.byId(id)
    val selection = Query(ElectronicAddresses) where (e => e.electronicAddressId === id)
    val entityToUpdate = electronicAddress.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddresses.byId(id)
    val selection = Query(ElectronicAddresses) where (e => e.electronicAddressId === id)
    selection.delete
  }

  private def complete(electronicAddress: ElectronicAddress): ElectronicAddress = {
    // Assume valid electronicAddressId (not None):
    val id = electronicAddress.electronicAddressId.get

    electronicAddress.electronicAddressArgs = ElectronicAddressArgs.findByElectronicAddressId(id)
    electronicAddress
  }
}
