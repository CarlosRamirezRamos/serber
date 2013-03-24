/*
 * RegistryObjectApi.java
 */


package controllers;

import java.util.List;

import play.*;
import play.mvc.*;

import play.libs.Json;
import play.libs.Jsonp;

import views.html.*;

import models.*;


/**
 * RegistryObjectApi
 */
public class RegistryObjectApi extends Controller {

    public static Result list(String search, String page, String callback) {
        List<RegistryObject> registryObjects = RegistryObject.list(search, page);
        Jsonp jsonp = Jsonp.jsonp(callback, Json.toJson(registryObjects));
        return ok(jsonp);
    }

    public static Result create(Long key) {
        return TODO;
    }

    public static Result read(Long key, String callback) {
        RegistryObject registryObject = RegistryObject.retrive(key);
        Jsonp jsonp = Jsonp.jsonp(callback, Json.toJson(registryObject));
        return ok(jsonp);
    }

    public static Result update() {
        return TODO;
    }

    public static Result delete(Long key) {
        return TODO;
    }
}
