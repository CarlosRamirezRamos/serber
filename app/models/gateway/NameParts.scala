package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.NamePart

/** NameParts Gateway (Table Data Gateway)
  *
  * Table description of table TBL_NAME_PARTS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class NameParts extends Table[NamePart]("TBL_NAME_PARTS") {

  /** Database column NAME_PART_ID PrimaryKey */
  def namePartId: Column[Int] = column[Int]("NAME_PART_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column COMPLEX_NAME_ID */
  def complexNameId: Column[Int] = column[Int]("COMPLEX_NAME_ID", O.DBType("integer"))

  /** Database column VALUE */
  def value: Column[Option[String]] = column[Option[String]]("VALUE", O.DBType("varchar(512)"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = namePartId.? ~ complexNameId ~ value ~ `type` <> (NamePart.apply _, NamePart.unapply _)

  /** Database insert projection */
  def forInsert = complexNameId ~ value ~ `type` <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.namePartId)

  /** Database finder-by-complex-name-id */
  //val byComplexNameId = createFinderBy(e => e.complexNameId)

  /** Foreign key referencing table ComplexNames
    *
    * (database name FK_TBL_NAME_PARTS__COMPLEX_NAMES)
    */
  def complexNames = foreignKey("FK_TBL_NAME_PARTS__COMPLEX_NAMES", complexNameId, ComplexNames)(r => r.complexNameId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(namePart: NamePart): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning namePartId
    projection.insert(namePart)
  }

  private def interpret(complexNameId: Int, value: Option[String], `type`: Option[String]): NamePart = {
    NamePart(None, complexNameId, value, `type`)
  }

  private def represent(e: NamePart): Option[(Int, Option[String], Option[String])] = {
    Some((e.complexNameId, e.value, e.`type`))
  }
}

object NameParts extends NameParts {

  def findAll(implicit session: Session): List[NamePart] = {
    val selection = Query(NameParts)
    selection.list
  }

  def findById(id: Int): Option[NamePart] = DB withSession { implicit session: Session =>
    //val selection = NameParts.byId(id)
    val selection = Query(NameParts) where (e => e.namePartId === id)
    selection.firstOption
  }

  def findByComplexNameId(id: Int): List[NamePart] = DB withSession { implicit session: Session =>
    //val selection = NameParts.byComplexNameId(id)
    val selection = Query(NameParts) where (e => e.complexNameId === id)
    selection.list
  }

  def update(id: Int, namePart: NamePart): Unit = DB withSession { implicit session: Session =>
    //val selection = NameParts.byId(id)
    val selection = Query(NameParts) where (e => e.namePartId === id)
    val entityToUpdate = namePart.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = NameParts.byId(id)
    val selection = Query(NameParts) where (e => e.namePartId === id)
    selection.delete
  }
}
