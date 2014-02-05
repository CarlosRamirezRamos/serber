package controllers

//import java.util.List

import play.api.Play.current
import play.api.mvc.{Action, Controller}
//import play.api.db.slick.Config.driver.simple._
//import play.api.db.slick._
import play.api.libs.json.Json
import play.api.libs.Jsonp

import views._
import models._

/** RegistryObjects Controller */
object RegistryObjects extends Controller {

  def create(key: Long) = Action {
    NotImplemented
  }

  def read(key: Long, callback: String) = Action {
    val registryObject = gateway.RegistryObjects.findByKey(key)
    val json = Json.toJson(registryObject getOrElse dto.RegistryObject.Nihil)
    Ok(Jsonp(callback, json))
  }

  def update(key: Long) = Action {
    NotImplemented
  }

  def delete(key: Long) = Action {
    NotImplemented
  }

  def list(text: String, page: String, callback: String) = Action {
    val registryObjects = gateway.RegistryObjects.findByNamePartValue(text, page)
    val json = Json.toJson(registryObjects)
    Ok(Jsonp(callback, json))
  }
}
