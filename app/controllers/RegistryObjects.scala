package controllers

//import java.util.List

import play.api.mvc.{Action, Controller}
//import play.api.libs.json.Json
//import play.api.libs.Jsonp

import views._
import models._

/** RegistryObjects Controller */
object RegistryObjects extends Controller {

  def create(key: Long) = Action {
    NotImplemented
  }

  /**
    public static Result read(Long key, String callback) {
        RegistryObject registryObject = RegistryObject.retrive(key);
        Jsonp jsonp = Jsonp.jsonp(callback, Json.toJson(registryObject));
        return ok(jsonp);
    }
  //*/
  /**
  def read(key: Long, callback: String) = Action {
    val registryObject = RegistryObject.retrive(key)
    val json = Json.toJson(registryObject)
    Ok(Jsonp(callback, json))
  }
  //*/

  def read(key: Long, callback: String) = Action {
    NotImplemented
  }

  def update(key: Long) = Action {
    NotImplemented
  }

  def delete(key: Long) = Action {
    NotImplemented
  }

  /**
    public static Result list(String search, String page, String callback) {
        List<RegistryObject> registryObjects = RegistryObject.list(search, page);
        Jsonp jsonp = Jsonp.jsonp(callback, Json.toJson(registryObjects));
        return ok(jsonp);
    }
  //*/
  /**
  def list(search: String, page: String, callback: String) = Action {
    val registryObjects = RegistryObject.list(search, page)
    val json = Json.toJson(registryObjects)
    Ok(Jsonp(callback, json))
  }
  //*/

  def list(search: String, page: String, callback: String) = Action {
    NotImplemented
  }
}
