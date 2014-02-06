package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.AddressLocation

/** AddressLocations Gateway (Table Data Gateway)
  *
  * Table description of table TBL_ADDRESS_LOCATIONS
  *
  * Objects of this class serve as prototypes for rows in queries.
  */
class AddressLocations extends Table[AddressLocation]("TBL_ADDRESS_LOCATIONS") {

  /** Database column ADDRESS_LOCATION_ID PrimaryKey */
  def addressLocationId: Column[Int] = column[Int]("ADDRESS_LOCATION_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column LOCATION_ID */
  def locationId: Column[Int] = column[Int]("LOCATION_ID", O.DBType("integer"))

  /** Database default projection */
  def * = addressLocationId.? ~ locationId <> (AddressLocation.interpret _, AddressLocation.represent _)

  /** Database insert projection */
  //def forInsert = locationId <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.addressLocationId)

  /** Database finder-by-location-id */
  //val byLocationId = createFinderBy(e => e.locationId)

  /** Foreign key referencing table Locations
    *
    * (database name FK_TBL_ADDRESS_LOCATIONS__LOCATIONS)
    */
  def locations = foreignKey("FK_TBL_ADDRESS_LOCATIONS__LOCATIONS", locationId, Locations)(r => r.locationId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(addressLocation: AddressLocation): Int = DB withSession { implicit session: Session =>
    //val projection = forInsert returning addressLocationId
    val projection = * returning addressLocationId
    projection.insert(addressLocation)
  }

  private def interpret(locationId: Int): AddressLocation = {
    AddressLocation(None, locationId)
  }

  private def represent(e: AddressLocation): Option[Int] = {
    Some(e.locationId)
  }
}

object AddressLocations extends AddressLocations {

  def findAll(implicit session: Session): List[AddressLocation] = {
    val selection = Query(AddressLocations)
    selection.list map (e => complete(e))
  }

  def findById(id: Int): Option[AddressLocation] = DB withSession { implicit session: Session =>
    //val selection = AddressLocations.byId(id)
    val selection = Query(AddressLocations) where (e => e.addressLocationId === id)
    selection.firstOption map (e => complete(e))
  }

  def findByLocationId(id: Int): List[AddressLocation] = DB withSession { implicit session: Session =>
    //val selection = AddressLocations.byLocationId(id)
    val selection = Query(AddressLocations) where (e => e.locationId === id)
    selection.list map (e => complete(e))
  }

  def update(id: Int, addressLocation: AddressLocation): Unit = DB withSession { implicit session: Session =>
    //val selection = AddressLocations.byId(id)
    val selection = Query(AddressLocations) where (e => e.addressLocationId === id)
    val entityToUpdate = addressLocation.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = AddressLocations.byId(id)
    val selection = Query(AddressLocations) where (e => e.addressLocationId === id)
    selection.delete
  }

  private def complete(addressLocation: AddressLocation): AddressLocation = {
    // Assume valid addressLocationId (not None):
    val id = addressLocation.addressLocationId.get

    addressLocation.physicalAddresses = PhysicalAddresses.findByAddressLocationId(id)
    addressLocation.electronicAddresses = ElectronicAddresses.findByAddressLocationId(id)
    addressLocation
  }
}
