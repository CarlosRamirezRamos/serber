package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.Subject

/** Subjects Gateway (Table Data Gateway)
  *
  * Table description of table TBL_SUBJECTS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class Subjects extends Table[Subject]("TBL_SUBJECTS") {

  /** Database column SUBJECT_ID PrimaryKey */
  def subjectId: Column[Int] = column[Int]("SUBJECT_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

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
  def * = subjectId.? ~ registryObjectKey ~ value ~ `type` ~ lang <> (Subject.apply _, Subject.unapply _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ value ~ `type` ~ lang <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.subjectId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_SUBJECTS__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_SUBJECTS__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(subject: Subject): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning subjectId
    projection.insert(subject)
  }

  private def interpret(registryObjectKey: Long, value: Option[String], `type`: Option[String], lang: Option[String]): Subject = {
    Subject(None, registryObjectKey, value, `type`, lang)
  }

  private def represent(e: Subject): Option[(Long, Option[String], Option[String], Option[String])] = {
    Some((e.registryObjectKey, e.value, e.`type`, e.lang))
  }
}

object Subjects extends Subjects {

  def findAll(implicit session: Session): List[Subject] =  {
    val selection = Query(Subjects)
    selection.list
  }

  def findById(id: Int): Option[Subject] = DB withSession { implicit session: Session =>
    //val selection = Subjects.byId(id)
    val selection = Query(Subjects) where (e => e.subjectId === id)
    selection.firstOption
  }

  def findByRegistryObjectKey(key: Long): List[Subject] = DB withSession { implicit session: Session =>
    //val selection = Subjects.byRegistryObjectKey(key)
    val selection = Query(Subjects) where (e => e.registryObjectKey === key)
    selection.list
  }

  def update(id: Int, subject: Subject): Unit = DB withSession { implicit session: Session =>
    //val selection = Subjects.byId(id)
    val selection = Query(Subjects) where (e => e.subjectId === id)
    val entityToUpdate = subject.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = Subjects.byId(id)
    val selection = Query(Subjects) where (e => e.subjectId === id)
    selection.delete
  }
}
