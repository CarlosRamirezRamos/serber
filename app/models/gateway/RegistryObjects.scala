package models.gateway

//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.RegistryObject

/** RegistryObjects Gateway (Table Data Gateway)
  *
  * Table description of table TBL_REGISTRY_OBJECTS
  *
  * Objects of this class serve as prototypes for rows in queries.
  * NOTE: The following names collided with Scala keywords and were escaped: type
  */
class RegistryObjects extends Table[RegistryObject]("TBL_REGISTRY_OBJECTS") {

  /** Database column REGISTRY_OBJECT_KEY PrimaryKey */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.PrimaryKey, O.DBType("bigint"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_CLASS */
  def registryObjectClass: Column[Option[String]] = column[Option[String]]("REGISTRY_OBJECT_CLASS", O.DBType("varchar(512)"))

  /** Database column TYPE
    *
    * NOTE: The name was escaped because it collided with a Scala keyword.
    */
  def `type`: Column[Option[String]] = column[Option[String]]("TYPE", O.DBType("varchar(512)"))

  /** Database column ORIGINATING_SOURCE */
  def originatingSource: Column[Option[String]] = column[Option[String]]("ORIGINATING_SOURCE", O.DBType("varchar(512)"))

  /** Database column ORIGINATING_SOURCE_TYPE */
  def originatingSourceType: Column[Option[String]] = column[Option[String]]("ORIGINATING_SOURCE_TYPE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = registryObjectKey.? ~ registryObjectClass ~ `type` ~ originatingSource ~ originatingSourceType <> (RegistryObject.interpret _, RegistryObject.represent _)

  /** Database insert projection */
  def forInsert = registryObjectClass ~ `type` ~ originatingSource ~ originatingSourceType <> (interpret _, represent _)

  /** Database finder-by-key */
  //def byKey = createFinderBy(e => e.registryObjectKey)

  def insert(registryObject: RegistryObject): Long = DB withSession { implicit session: Session =>
    val projection = forInsert returning registryObjectKey
    projection.insert(registryObject)
  }

  private def interpret(registryObjectClass: Option[String], `type`: Option[String], originatingSource: Option[String], originatingSourceType: Option[String]): RegistryObject = {
    RegistryObject(None, registryObjectClass, `type`, originatingSource, originatingSourceType)
  }

  private def represent(e: RegistryObject): Option[(Option[String], Option[String], Option[String], Option[String])] = {
    Some((e.registryObjectClass, e.`type`, e.originatingSource, e.originatingSourceType))
  }
}

object RegistryObjects extends RegistryObjects {

  def findAll(implicit session: Session): List[RegistryObject] = {
    val selection = Query(RegistryObjects)
    selection.list map (e => complete(e))
  }

  def findByKey(key: Long): Option[RegistryObject] = DB withSession { implicit session: Session =>
    //val selection = RegistryObjects.byKey(key)
    val selection = Query(RegistryObjects) where (e => e.registryObjectKey === key)
    selection.firstOption map (e => complete(e))
  }

  def findByNamePartValue(text: String, page: String): List[RegistryObject] = DB withSession { implicit session: Session =>
    val selection = for {
      nameParts <- NameParts if nameParts.value like "%" + text + "%"
      complexNames <- nameParts.complexNames
      registryObjects <- complexNames.registryObjects
    } yield registryObjects
    selection.list map (e => complete(e))
  }

  def update(key: Long, registryObject: RegistryObject): Unit = DB withSession { implicit session: Session =>
    //val selection = RegistryObjects.byKey(key)
    val selection = Query(RegistryObjects) where (e => e.registryObjectKey === key)
    val entityToUpdate = registryObject.copy(Some(key))
    selection.update(entityToUpdate)
  }

  def delete(key: Long): Unit = DB withSession { implicit session: Session =>
    //val selection = RegistryObjects.byKey(key)
    val selection = Query(RegistryObjects) where (e => e.registryObjectKey === key)
    selection.delete
  }

  private def complete(registryObject: RegistryObject): RegistryObject = {
    // Assume valid registryObjectKey (not None):
    val key = registryObject.registryObjectKey.get

    registryObject.identifiers = Identifiers.findByRegistryObjectKey(key)
    registryObject.subjects = Subjects.findByRegistryObjectKey(key)
    registryObject.descriptions = Descriptions.findByRegistryObjectKey(key)
    registryObject.relatedInfos = RelatedInfos.findByRegistryObjectKey(key)
    registryObject.accessPolicies = AccessPolicies.findByRegistryObjectKey(key)
    registryObject.complexNames = ComplexNames.findByRegistryObjectKey(key)
    registryObject.locations = Locations.findByRegistryObjectKey(key)
    registryObject.relations = Relations.findByRegistryObjectKey(key)
    registryObject
  }
}
