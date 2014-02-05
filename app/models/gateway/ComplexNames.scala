package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.ComplexName

/** ComplexNames Gateway (Table Data Gateway)
  *
  * Table description of table TBL_COMPLEX_NAMES
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class ComplexNames extends Table[ComplexName]("TBL_COMPLEX_NAMES") {

  /** Database column COMPLEX_NAME_ID PrimaryKey */
  def complexNameId: Column[Int] = column[Int]("COMPLEX_NAME_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

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
  def * = complexNameId.? ~ registryObjectKey ~ value ~ `type` ~ lang <> (ComplexName.interpret _, ComplexName.represent _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ value ~ `type` ~ lang <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.complexNameId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_COMPLEX_NAMES__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_COMPLEX_NAMES__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(complexName: ComplexName): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning complexNameId
    projection.insert(complexName)
  }

  private def interpret(registryObjectKey: Long, value: Option[String], `type`: Option[String], lang: Option[String]): ComplexName = {
    ComplexName(None, registryObjectKey, value, `type`, lang)
  }

  private def represent(e: ComplexName): Option[(Long, Option[String], Option[String], Option[String])] = {
    Some((e.registryObjectKey, e.value, e.`type`, e.lang))
  }
}

object ComplexNames extends ComplexNames {

  def findAll(implicit session: Session): List[ComplexName] = {
    val selection = Query(ComplexNames)
    selection.list map (e => complete(e))
  }

  def findById(id: Int): Option[ComplexName] = DB withSession { implicit session: Session =>
    //val selection = ComplexNames.byId(id)
    val selection = Query(ComplexNames) where (e => e.complexNameId === id)
    selection.firstOption map (e => complete(e))
  }

  def findByRegistryObjectKey(key: Long): List[ComplexName] = DB withSession { implicit session: Session =>
    //val selection = ComplexNames.byRegistryObjectKey(key)
    val selection = Query(ComplexNames) where (e => e.registryObjectKey === key)
    selection.list map (e => complete(e))
  }

  def update(id: Int, complexName: ComplexName): Unit = DB withSession { implicit session: Session =>
    //val selection = ComplexNames.byId(id)
    val selection = Query(ComplexNames) where (e => e.complexNameId === id)
    val entityToUpdate = complexName.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = ComplexNames.byId(id)
    val selection = Query(ComplexNames) where (e => e.complexNameId === id)
    selection.delete
  }

  private def complete(complexName: ComplexName): ComplexName = {
    // Assume valid complexNameId (not None):
    val id = complexName.complexNameId.get

    complexName.nameParts = NameParts.findByComplexNameId(id)
    complexName
  }
}
