package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.SpatialLocation

/** SpatialLocations Gateway (Table Data Gateway)
  *
  * Table description of table TBL_SPATIAL_LOCATIONS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class SpatialLocations extends Table[SpatialLocation]("TBL_SPATIAL_LOCATIONS") {

  /** Database column SPATIAL_LOCATION_ID PrimaryKey */
  def spatialLocationId: Column[Int] = column[Int]("SPATIAL_LOCATION_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column LOCATION_ID */
  def locationId: Column[Int] = column[Int]("LOCATION_ID", O.DBType("integer"))

  /** Database column VALUE */
  def value: Column[Option[String]] = column[Option[String]]("VALUE", O.DBType("varchar(512)"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database column LANG */
  def lang: Column[Option[String]] = column[Option[String]]("LANG", O.DBType("varchar(64)"))

  /** Database default projection */
  def * = spatialLocationId.? ~ locationId ~ value ~ `type` ~ lang <> (SpatialLocation.apply _, SpatialLocation.unapply _)

  /** Database insert projection */
  def forInsert = locationId ~ value ~ `type` ~ lang <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.spatialLocationId)

  /** Database finder-by-location-id */
  //val byLocationId = createFinderBy(e => e.locationId)

  /** Foreign key referencing table Locations
    *
    * (database name FK_TBL_SPATIAL_LOCATIONS__LOCATIONS)
    */
  def locations = foreignKey("FK_TBL_SPATIAL_LOCATIONS__LOCATIONS", locationId, Locations)(r => r.locationId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(spatialLocation: SpatialLocation): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning spatialLocationId
    projection.insert(spatialLocation)
  }

  private def interpret(locationId: Int, value: Option[String], `type`: Option[String], lang: Option[String]): SpatialLocation = {
    SpatialLocation(None, locationId, value, `type`, lang)
  }

  private def represent(e: SpatialLocation): Option[(Int, Option[String], Option[String], Option[String])] = {
    Some((e.locationId, e.value, e.`type`, e.lang))
  }
}

object SpatialLocations extends SpatialLocations {

  def findAll(implicit session: Session): List[SpatialLocation] = {
    val selection = Query(SpatialLocations)
    selection.list
  }

  def findById(id: Int): Option[SpatialLocation] = DB withSession { implicit session: Session =>
    //val selection = SpatialLocations.byId(id)
    val selection = Query(SpatialLocations) where (e => e.spatialLocationId === id)
    selection.firstOption
  }

  def findByLocationId(id: Int): List[SpatialLocation] = DB withSession { implicit session: Session =>
    //val selection = SpatialLocations.byLocationId(id)
    val selection = Query(SpatialLocations) where (e => e.locationId === id)
    selection.list
  }

  def update(id: Int, spatialLocation: SpatialLocation): Unit = DB withSession { implicit session: Session =>
    //val selection = SpatialLocations.byId(id)
    val selection = Query(SpatialLocations) where (e => e.spatialLocationId === id)
    val entityToUpdate = spatialLocation.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = SpatialLocations.byId(id)
    val selection = Query(SpatialLocations) where (e => e.spatialLocationId === id)
    selection.delete
  }
}
