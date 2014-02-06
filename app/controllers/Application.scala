package controllers

import play.api.mvc.{Action, Controller}

import views._

/** Application Controller */
object Application extends Controller {

  def index = Action {
    Ok(html.index("Welcome to SERBER|pilot - Home", "index"))
  }
}
