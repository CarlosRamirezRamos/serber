package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.ElectronicAddressArg

/** ElectronicAddressArgs Gateway (Table Data Gateway)
  *
  * Table description of table TBL_ELECTRONIC_ADDRESS_ARGS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class ElectronicAddressArgs extends Table[ElectronicAddressArg]("TBL_ELECTRONIC_ADDRESS_ARGS") {

  /** Database column ELECTRONIC_ADDRESS_ARG_ID PrimaryKey */
  def electronicAddressArgId: Column[Int] = column[Int]("ELECTRONIC_ADDRESS_ARG_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column ELECTRONIC_ADDRESS_ID */
  def electronicAddressId: Column[Int] = column[Int]("ELECTRONIC_ADDRESS_ID", O.DBType("integer"))

  /** Database column NAME */
  def name: Column[Option[String]] = column[Option[String]]("NAME", O.DBType("varchar(512)"))

  /** Database column REQUIRED */
  def required: Column[Option[Boolean]] = column[Option[Boolean]]("REQUIRED", O.DBType("boolean"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database column USE */
  def use: Column[Option[String]] = column[Option[String]]("USE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = electronicAddressArgId.? ~ electronicAddressId ~ name ~ required ~ `type` ~ use <> (ElectronicAddressArg.apply _, ElectronicAddressArg.unapply _)

  /** Database insert projection */
  def forInsert = electronicAddressId ~ name ~ required ~ `type` ~ use <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.electronicAddressArgId)

  /** Database finder-by-electronic-address-id */
  //val byElectronicAddressId = createFinderBy(e => e.electronicAddressId)

  /** Foreign key referencing table ElectronicAddresses
    *
    * (database name FK_TBL_ELECTRONIC_ADDRESS_ARGS__ELECTRONIC_ADDRESSES)
    */
  def electronicAddresses = foreignKey("FK_TBL_ELECTRONIC_ADDRESS_ARGS__ELECTRONIC_ADDRESSES", electronicAddressId, ElectronicAddresses)(r => r.electronicAddressId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(electronicAddressArg: ElectronicAddressArg): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning electronicAddressArgId
    projection.insert(electronicAddressArg)
  }

  private def interpret(electronicAddressId: Int, name: Option[String], required: Option[Boolean], `type`: Option[String], use: Option[String]): ElectronicAddressArg = {
    ElectronicAddressArg(None, electronicAddressId, name, required, `type`, use)
  }

  private def represent(e: ElectronicAddressArg): Option[(Int, Option[String], Option[Boolean], Option[String], Option[String])] = {
    Some((e.electronicAddressId, e.name, e.required, e.`type`, e.use))
  }
}

object ElectronicAddressArgs extends ElectronicAddressArgs {

  def findAll(implicit session: Session): List[ElectronicAddressArg] = {
    val selection = Query(ElectronicAddressArgs)
    selection.list
  }

  def findById(id: Int): Option[ElectronicAddressArg] = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddressArgs.byId(id)
    val selection = Query(ElectronicAddressArgs) where (e => e.electronicAddressArgId === id)
    selection.firstOption
  }

  def findByElectronicAddressId(id: Int): List[ElectronicAddressArg] = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddressArgs.byElectronicAddressId(id)
    val selection = Query(ElectronicAddressArgs) where (e => e.electronicAddressId === id)
    selection.list
  }

  def update(id: Int, electronicAddressArg: ElectronicAddressArg): Unit = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddressArgs.byId(id)
    val selection = Query(ElectronicAddressArgs) where (e => e.electronicAddressArgId === id)
    val entityToUpdate = electronicAddressArg.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = ElectronicAddressArgs.byId(id)
    val selection = Query(ElectronicAddressArgs) where (e => e.electronicAddressArgId === id)
    selection.delete
  }
}
