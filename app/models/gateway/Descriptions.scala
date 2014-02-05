package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.Description

/** Descriptions Gateway (Table Data Gateway)
  *
  * Table description of table TBL_DESCRIPTIONS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class Descriptions extends Table[Description]("TBL_DESCRIPTIONS") {

  /** Database column DESCRIPTION_ID PrimaryKey */
  def descriptionId: Column[Int] = column[Int]("DESCRIPTION_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_KEY */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.DBType("bigint"))

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
  def * = descriptionId.? ~ registryObjectKey ~ value ~ `type` ~ lang <> (Description.apply _, Description.unapply _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ value ~ `type` ~ lang <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.descriptionId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_DESCRIPTIONS__REGISTRY_OBJECTS)
    */
  val registryObjects = foreignKey("FK_TBL_DESCRIPTIONS__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(description: Description): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning descriptionId
    projection.insert(description)
  }

  private def interpret(registryObjectKey: Long, value: Option[String], `type`: Option[String], lang: Option[String]): Description = {
    Description(None, registryObjectKey, value, `type`, lang)
  }

  private def represent(e: Description): Option[(Long, Option[String], Option[String], Option[String])] = {
    Some((e.registryObjectKey, e.value, e.`type`, e.lang))
  }
}

object Descriptions extends Descriptions {

  def findAll(implicit session: Session): List[Description] = {
    val selection = Query(Descriptions)
    selection.list
  }

  def findById(id: Int): Option[Description] = DB withSession { implicit session: Session =>
    //val selection = Descriptions.byId(id)
    val selection = Query(Descriptions) where (e => e.descriptionId === id)
    selection.firstOption
  }

  def findByRegistryObjectKey(key: Long): List[Description] = DB withSession { implicit session: Session =>
    //val selection = Descriptions.byRegistryObjectKey(key)
    val selection = Query(Descriptions) where (e => e.registryObjectKey === key)
    selection.list
  }

  def update(id: Int, description: Description): Unit = DB withSession { implicit session: Session =>
    //val selection = Descriptions.byId(id)
    val selection = Query(Descriptions) where (e => e.descriptionId === id)
    val entityToUpdate = description.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = Descriptions.byId(id)
    val selection = Query(Descriptions) where (e => e.descriptionId === id)
    selection.delete
  }
}
