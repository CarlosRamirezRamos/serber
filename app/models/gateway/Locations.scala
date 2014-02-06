package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.Location

/** Locations Gateway (Table Data Gateway)
  *
  * Table description of table TBL_LOCATIONS
  *
  * Objects of this class serve as prototypes for rows in queries.
  */
class Locations extends Table[Location]("TBL_LOCATIONS") {

  /** Database column LOCATION_ID PrimaryKey */
  def locationId: Column[Int] = column[Int]("LOCATION_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_KEY */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.DBType("bigint"))

  /** Database default projection */
  def * = locationId.? ~ registryObjectKey <> (Location.interpret _, Location.represent _)

  /** Database insert projection */
  //def forInsert = registryObjectKey <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.locationId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_LOCATIONS__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_LOCATIONS__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(location: Location): Int = DB withSession { implicit session: Session =>
    //val projection = forInsert returning locationId
    val projection = * returning locationId
    projection.insert(location)
  }

  private def interpret(registryObjectKey: Long): Location = {
    Location(None, registryObjectKey)
  }

  private def represent(e: Location): Option[Long] = {
    Some(e.registryObjectKey)
  }
}

object Locations extends Locations {

  def findAll(implicit session: Session): List[Location] = {
    val selection = Query(Locations)
    selection.list map (e => complete(e))
  }

  def findById(id: Int): Option[Location] = DB withSession { implicit session: Session =>
    //val selection = Locations.byId(id)
    val selection = Query(Locations) where (e => e.locationId === id)
    selection.firstOption map (e => complete(e))
  }

  def findByRegistryObjectKey(key: Long): List[Location] = DB withSession { implicit session: Session =>
    //val selection = Locations.byRegistryObjectKey(key)
    val selection = Query(Locations) where (e => e.registryObjectKey === key)
    selection.list map (e => complete(e))
  }

  def update(id: Int, location: Location): Unit = DB withSession { implicit session: Session =>
    //val selection = Locations.byId(id)
    val selection = Query(Locations) where (e => e.locationId === id)
    val entityToUpdate = location.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = Locations.byId(id)
    val selection = Query(Locations) where (e => e.locationId === id)
    selection.delete
  }

  private def complete(location: Location): Location = {
    // Assume valid locationId (not None):
    val id = location.locationId.get

    location.spatialLocations = SpatialLocations.findByLocationId(id)
    location.addressLocations = AddressLocations.findByLocationId(id)
    location
  }
}
