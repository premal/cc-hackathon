package controllers

import play.api.mvc._
import play.api.libs.json.Json
import models.CompanyRepository

object MainController extends Controller {

  /**
   * The index page.  This is the main entry point, seeing as this is a single page app.
   */
  def index() = Action {
    Ok(views.html.index())
  }

  def companiesCSV = Action { implicit request => {
    val builder = new StringBuilder
    val header = Seq("name","domain","category","country","city")
    builder.append(header.reduce(_ + ";" + _))
    builder.append("\n")
    CompanyRepository.companies.foreach(entry => builder.append(entry.reduceLeft(_ + ";" + _)).append("\n"))

    Ok(builder.toString())
  }}

  def companiesJson = Action { implicit request => {
    Ok(Json.toJson(CompanyRepository.companies.map(e => Json.obj(
      "name" -> e(0),
      "domain" -> e(1),
      "category" -> e(2),
      "country" -> e(3),
      "city" -> e(4)
    ))))
  }}
}
