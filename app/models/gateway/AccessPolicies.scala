package models.gateway

import scala.slick.lifted.ForeignKeyAction
//import scala.slick.session.Session

import play.api.Play.current
import play.api.db.slick.DB
import play.api.db.slick.Config.driver.simple._

import models.dto.AccessPolicy

/** AccessPolicies Gateway (Table Data Gateway)
  *
  * Table description of table TBL_ACCESS_POLICIES
  *
  * Objects of this class serve as prototypes for rows in queries.
  */
class AccessPolicies extends Table[AccessPolicy]("TBL_ACCESS_POLICIES") {

  /** Database column ACCESS_POLICY_ID PrimaryKey */
  def accessPolicyId: Column[Int] = column[Int]("ACCESS_POLICY_ID", O.PrimaryKey, O.DBType("integer"), O.AutoInc)

  /** Database column REGISTRY_OBJECT_KEY */
  def registryObjectKey: Column[Long] = column[Long]("REGISTRY_OBJECT_KEY", O.DBType("bigint"))

  /** Database column VALUE */
  def value: Column[Option[String]] = column[Option[String]]("VALUE", O.DBType("varchar(512)"))

  /** Database default projection */
  def * = accessPolicyId.? ~ registryObjectKey ~ value <> (AccessPolicy.apply _, AccessPolicy.unapply _)

  /** Database insert projection */
  def forInsert = registryObjectKey ~ value <> (interpret _, represent _)

  /** Database finder-by-id */
  //val byId = createFinderBy(e => e.accessPolicyId)

  /** Database finder-by-registry-object-key */
  //val byRegistryObjectKey = createFinderBy(e => e.registryObjectKey)

  /** Foreign key referencing table RegistryObjects
    *
    * (database name FK_TBL_ACCESS_POLICIES__REGISTRY_OBJECTS)
    */
  def registryObjects = foreignKey("FK_TBL_ACCESS_POLICIES__REGISTRY_OBJECTS", registryObjectKey, RegistryObjects)(r => r.registryObjectKey, onUpdate = ForeignKeyAction.Restrict, onDelete = ForeignKeyAction.Restrict)

  def insert(accessPolicy: AccessPolicy): Int = DB withSession { implicit session: Session =>
    val projection = forInsert returning accessPolicyId
    projection.insert(accessPolicy)
  }

  private def interpret(registryObjectKey: Long, value: Option[String]): AccessPolicy = {
    AccessPolicy(None, registryObjectKey, value)
  }

  private def represent(e: AccessPolicy): Option[(Long, Option[String])] = {
    Some((e.registryObjectKey, e.value))
  }
}

object AccessPolicies extends AccessPolicies {

  def findAll(implicit session: Session): List[AccessPolicy] = {
    val selection = Query(AccessPolicies)
    selection.list
  }

  def findById(id: Int): Option[AccessPolicy] = DB withSession { implicit session: Session =>
    //val selection = AccessPolicies.byId(id)
    val selection = Query(AccessPolicies) where (e => e.accessPolicyId === id)
    selection.firstOption
  }

  def findByRegistryObjectKey(key: Long): List[AccessPolicy] = DB withSession { implicit session: Session =>
    //val selection = AccessPolicies.byRegistryObjectKey(key)
    val selection = Query(AccessPolicies) where (e => e.registryObjectKey === key)
    selection.list
  }

  def update(id: Int, accessPolicy: AccessPolicy): Unit = DB withSession { implicit session: Session =>
    //val selection = AccessPolicies.byId(id)
    val selection = Query(AccessPolicies) where (e => e.accessPolicyId === id)
    val entityToUpdate = accessPolicy.copy(Some(id))
    selection.update(entityToUpdate)
  }

  def delete(id: Int): Unit = DB withSession { implicit session: Session =>
    //val selection = AccessPolicies.byId(id)
    val selection = Query(AccessPolicies) where (e => e.accessPolicyId === id)
    selection.delete
  }
}
