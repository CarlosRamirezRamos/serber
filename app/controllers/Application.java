/*
 * Application.java
 */


package controllers;

import play.*;
import play.mvc.*;

import views.html.*;


/**
 * Application
 */
public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Welcome to SERBER|pilot"));
    }
}
