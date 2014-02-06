package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.AddressPart

/** AddressParts Gateway (Table Data Gateway)
  *
  * Table description of table TBL_ADDRESS_PARTS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class AddressParts extends Table[AddressPart]("TBL_ADDRESS_PARTS") {

  /** Database column ADDRESS_PART_ID PrimaryKey */
  def addressPartId: Column[Int] = column[Int]("ADDRESS_PART_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column PHYSICAL_ADDRESS_ID */
  def physicalAddressId: Column[Int] = column[Int]("PHYSICAL_ADDRESS_ID", O.DBType("integer"))

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
  def * = addressPartId.? ~ physicalAddressId ~ value ~ `type` ~ lang <> (AddressPart.apply _, AddressPart.unapply _)

  /** Database insert projection */
  def forInsert = physicalAddressId ~ value ~ `type` ~ lang <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.addressPartId)

  /** Database finder-by-physical-address-id */
  //val byPhysicalAddressId = createFinderBy(e => e.physicalAddressId)

  /** Foreign key referencing table PhysicalAddresses
    *
    * (database name FK_TBL_IDENTIFIERS__REGISTRY_OBJECTS)
    */
  def physicalAddresses = foreignKey("FK_TBL_ADDRESS_PARTS__PHYSICAL_ADDRESSES", physicalAddressId, PhysicalAddresses)(r => r.physicalAddressId, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(addressPart: AddressPart): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning addressPartId
    projection.insert(addressPart)
  }

  private def interpret(physicalAddressId: Int, value: Option[String], `type`: Option[String], lang: Option[String]): AddressPart = {
    AddressPart(None, physicalAddressId, value, `type`, lang)
  }

  private def represent(e: AddressPart): Option[(Int, Option[String], Option[String], Option[String])] = {
    Some((e.physicalAddressId, e.value, e.`type`, e.lang))
  }
}

object AddressParts extends AddressParts {

  def findAll(implicit session: Session): List[AddressPart] = {
    val selection = Query(AddressParts)
    selection.list
  }

  def findById(id: Int): Option[AddressPart] = DB withSession { implicit session: Session =>
    //val selection = AddressParts.byId(id)
    val selection = Query(AddressParts) where (e => e.addressPartId === id)
    selection.firstOption
  }

  def findByPhysicalAddressId(id: Int): List[AddressPart] = DB withSession { implicit session: Session =>
    //val selection = AddressParts.byPhysicalAddressId(id)
    val selection = Query(AddressParts) where (e => e.physicalAddressId === id)
    selection.list
  }

  def update(id: Int, addressPart: AddressPart): Unit = DB withSession { implicit session: Session =>
    //val selection = AddressParts.byId(id)
    val selection = Query(AddressParts) where (e => e.addressPartId === id)
    val entityToUpdate = addressPart.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = AddressParts.byId(id)
    val selection = Query(AddressParts) where (e => e.addressPartId === id)
    selection.delete
  }
}
