package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.Identifier

/** Identifiers Gateway (Table Data Gateway)
  *
  * Table description of table TBL_IDENTIFIERS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class Identifiers extends Table[Identifier]("TBL_IDENTIFIERS") {

  /** Database column IDENTIFIER_ID PrimaryKey */
  def identifierId: Column[Int] = column[Int]("IDENTIFIER_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_KEY */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.DBType("bigint"))

  /** Database column VALUE */
  def value: Column[Option[String]] = column[Option[String]]("VALUE", O.DBType("varchar(512)"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = identifierId.? ~ registryObjectKey ~ value ~ `type` <> (Identifier.apply _, Identifier.unapply _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ value ~ `type` <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.identifierId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_IDENTIFIERS__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_IDENTIFIERS__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(identifier: Identifier): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning identifierId
    projection.insert(identifier)
  }

  private def interpret(registryObjectKey: Long, value: Option[String], `type`: Option[String]): Identifier = {
    Identifier(None, registryObjectKey, value, `type`)
  }

  private def represent(e: Identifier): Option[(Long, Option[String], Option[String])] = {
    Some((e.registryObjectKey, e.value, e.`type`))
  }
}

object Identifiers extends Identifiers {

  def findAll(implicit session: Session): List[Identifier] = {
    val selection = Query(Identifiers)
    selection.list
  }

  def findById(id: Int): Option[Identifier] = DB withSession { implicit session: Session =>
    //val selection = Identifiers.byId(id)
    val selection = Query(Identifiers) where (e => e.identifierId === id)
    selection.firstOption
  }

  def findByRegistryObjectKey(key: Long): List[Identifier] = DB withSession { implicit session: Session =>
    //val selection = Identifiers.byRegistryObjectKey(key)
    val selection = Query(Identifiers) where (e => e.registryObjectKey === key)
    selection.list
  }

  def update(id: Int, identifier: Identifier): Unit = DB withSession { implicit session: Session =>
    //val selection = Identifiers.byId(id)
    val selection = Query(Identifiers) where (e => e.identifierId === id)
    val entityToUpdate = identifier.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = Identifiers.byId(id)
    val selection = Query(Identifiers) where (e => e.identifierId === id)
    selection.delete
  }
}
