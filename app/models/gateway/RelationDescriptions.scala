package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.RelationDescription

/** RelationDescriptions Gateway (Table Data Gateway)
  *
  * Table description of table TBL_RELATION_DESCRIPTIONS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class RelationDescriptions extends Table[RelationDescription]("TBL_RELATION_DESCRIPTIONS") {

  /** Database column RELATION_DESCRIPTION_ID PrimaryKey */
  def relationDescriptionId: Column[Int] = column[Int]("RELATION_DESCRIPTION_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column RELATION_ID */
  def relationId: Column[Int] = column[Int]("RELATION_ID", O.DBType("integer"))

  /** Database column DESCRIPTION */
  def description: Column[Option[String]] = column[Option[String]]("DESCRIPTION", O.DBType("varchar(512)"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database column LANG */
  def lang: Column[Option[String]] = column[Option[String]]("LANG", O.DBType("varchar(64)"))

  /** Database column URL */
  def url: Column[Option[String]] = column[Option[String]]("URL", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = relationDescriptionId.? ~ relationId ~ description ~ `type` ~ lang ~ url <> (RelationDescription.apply _, RelationDescription.unapply _)

  /** Database insert projection */
  def forInsert = relationId ~ description ~ `type` ~ lang ~ url <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.relationDescriptionId)

  /** Database finder-by-relation-id */
  //val byRelationId = createFinderBy(e => e.relationId)

  /** Foreign key referencing table Relations
    *
    * (database name FK_TBL_RELATION_DESCRIPTIONS__RELATIONS)
    */
  def relations = foreignKey("FK_TBL_RELATION_DESCRIPTIONS__RELATIONS", relationId, Relations)(r => r.relationId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(relationDescription: RelationDescription): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning relationDescriptionId
    projection.insert(relationDescription)
  }

  private def interpret(relationId: Int, description: Option[String], `type`: Option[String], lang: Option[String], url: Option[String]): RelationDescription = {
    RelationDescription(None, relationId, description, `type`, lang, url)
  }

  private def represent(e: RelationDescription): Option[(Int, Option[String], Option[String], Option[String], Option[String])] = {
    Some((e.relationId, e.description, e.`type`, e.lang, e.url))
  }
}

object RelationDescriptions extends RelationDescriptions {

  def findAll(implicit session: Session): List[RelationDescription] = {
    val selection = Query(RelationDescriptions)
    selection.list
  }

  def findById(id: Int): Option[RelationDescription] = DB withSession { implicit session: Session =>
    //val selection = RelationDescriptions.byId(id)
    val selection = Query(RelationDescriptions) where (e => e.relationDescriptionId === id)
    selection.firstOption
  }

  def findByRelationId(id: Int): List[RelationDescription] = DB withSession { implicit session: Session =>
    //val selection = RelationDescriptions.byRelationId(id)
    val selection = Query(RelationDescriptions) where (e => e.relationId === id)
    selection.list
  }

  def update(id: Int, relationDescription: RelationDescription): Unit = DB withSession { implicit session: Session =>
    //val selection = RelationDescriptions.byId(id)
    val selection = Query(RelationDescriptions) where (e => e.relationDescriptionId === id)
    val entityToUpdate = relationDescription.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = RelationDescriptions.byId(id)
    val selection = Query(RelationDescriptions) where (e => e.relationDescriptionId === id)
    selection.delete
  }
}
