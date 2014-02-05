package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.RelatedInfo

/** RelatedInfos Gateway (Table Data Gateway)
  *
  * Table description of table TBL_RELATED_INFOS
  *
  * Objects of this class serve as prototypes for rows in queries.
  */
class RelatedInfos extends Table[RelatedInfo]("TBL_RELATED_INFOS") {

  /** Database column RELATED_INFO_ID PrimaryKey */
  def relatedInfoId: Column[Int] = column[Int]("RELATED_INFO_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_KEY */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.DBType("bigint"))

  /** Database column VALUE */
  def value: Column[Option[String]] = column[Option[String]]("VALUE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = relatedInfoId.? ~ registryObjectKey ~ value <> (RelatedInfo.apply _, RelatedInfo.unapply _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ value <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.relatedInfoId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_RELATED_INFOS__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_RELATED_INFOS__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(relatedInfo: RelatedInfo): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning relatedInfoId
    projection.insert(relatedInfo)
  }

  private def interpret(registryObjectKey: Long, value: Option[String]): RelatedInfo = {
    RelatedInfo(None, registryObjectKey, value)
  }

  private def represent(e: RelatedInfo): Option[(Long, Option[String])] = {
    Some((e.registryObjectKey, e.value))
  }
}

object RelatedInfos extends RelatedInfos {

  def findAll(implicit session: Session): List[RelatedInfo] = {
    val selection = Query(RelatedInfos)
    selection.list
  }

  def findById(id: Int): Option[RelatedInfo] = DB withSession { implicit session: Session =>
    //val selection = RelatedInfos.byId(id)
    val selection = Query(RelatedInfos) where (e => e.relatedInfoId === id)
    selection.firstOption
  }

  def findByRegistryObjectKey(key: Long): List[RelatedInfo] = DB withSession { implicit session: Session =>
    //val selection = RelatedInfos.byRegistryObjectKey(key)
    val selection = Query(RelatedInfos) where (e => e.registryObjectKey === key)
    selection.list
  }

  def update(id: Int, relatedInfo: RelatedInfo): Unit = DB withSession { implicit session: Session =>
    //val selection = RelatedInfos.byId(id)
    val selection = Query(RelatedInfos) where (e => e.relatedInfoId === id)
    val entityToUpdate = relatedInfo.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = RelatedInfos.byId(id)
    val selection = Query(RelatedInfos) where (e => e.relatedInfoId === id)
    selection.delete
  }
}
