package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.Relation

/** Relations Gateway (Table Data Gateway)
  *
  * Table description of table TBL_RELATIONS
  *
  * Objects of this class serve as prototypes for rows in queries.
  */
class Relations extends Table[Relation]("TBL_RELATIONS") {

  /** Database column RELATION_ID PrimaryKey */
  def relationId: Column[Int] = column[Int]("RELATION_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_KEY */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.DBType("bigint"))

  /** Database column RELATED_REGISTRY_OBJECT_KEY */
  def relatedRegistryObjectKey: Column[Long] = column[Long]("RELATED_REGISTRY_OBJECT_KEY", O.DBType("bigint"))

  /** Database default projection */
  def * = relationId.? ~ registryObjectKey ~ relatedRegistryObjectKey <> (Relation.interpret _, Relation.represent _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ relatedRegistryObjectKey <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.relationId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Database finder-by-related-registry-object-key */
  //val byRelatedRegistryObjectKey = createFinderBy(e => e.relatedRegistryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_RELATIONS__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_RELATIONS__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_RELATIONS__RELATEDREGISTRYOBJECTS)
    */
  def relatedRegistryObjects = foreignKey("FK_TBL_RELATIONS__RELATED_REGISTRY_OBJECTS", relatedRegistryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(relation: Relation): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning relationId
    projection.insert(relation)
  }

  private def interpret(registryObjectKey: Long, relatedRegistryObjectKey: Long): Relation = {
    Relation(None, registryObjectKey, relatedRegistryObjectKey)
  }

  private def represent(e: Relation): Option[(Long, Long)] = {
    Some((e.registryObjectKey, e.relatedRegistryObjectKey))
  }
}

object Relations extends Relations {

  def findAll(implicit session: Session): List[Relation] = {
    val selection = Query(Relations)
    selection.list map (e => complete(e))
  }

  def findById(id: Int): Option[Relation] = DB withSession { implicit session: Session =>
    //val selection = Relations.byId(id)
    val selection = Query(Relations) where (e => e.relationId === id)
    selection.firstOption map (e => complete(e))
  }

  def findByRegistryObjectKey(key: Long): List[Relation] = DB withSession { implicit session: Session =>
    //val selection = Relations.byRegistryObjectKey(key)
    val selection = Query(Relations) where (e => e.registryObjectKey === key)
    selection.list map (e => complete(e))
  }

  def findByRelatedRegistryObjectKey(key: Long): List[Relation] = DB withSession { implicit session: Session =>
    //val selection = Relations.byRelatedRegistryObjectKey(key)
    val selection = Query(Relations) where (e => e.relatedRegistryObjectKey === key)
    selection.list map (e => complete(e))
  }

  def update(id: Int, relation: Relation): Unit = DB withSession { implicit session: Session =>
    //val selection = Relations.byId(id)
    val selection = Query(Relations) where (e => e.relationId === id)
    val entityToUpdate = relation.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = Relations.byId(id)
    val selection = Query(Relations) where (e => e.relationId === id)
    selection.delete
  }

  private def complete(relation: Relation): Relation = {
    // Assume valid relationId (not None):
    val id = relation.relationId.get

    relation.relationDescriptions = RelationDescriptions.findByRelationId(id)
    relation
  }
}
