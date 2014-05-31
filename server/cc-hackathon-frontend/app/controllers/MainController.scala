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
    CompanyRepository.companies.foreach(entry => builder.append(header.map(entry._2(_)).reduceLeft(_ + ";" + _)).append("\n"))

    Ok(builder.toString())
  }}

  def companiesJson = Action { implicit request => {
    Ok(Json.toJson(CompanyRepository.companies.map(e => Json.toJson(e._2))))
  }}

  def kmeansPage(company:String) = Action(implicit request => {
    Ok(views.html.kmeans(company))
  })

  def kmeans = Action{implicit request => {
    val company = request.body.asFormUrlEncoded.get("company").head
    val neighbors = CompanyRepository.companyTechnologies.get(company).map(technologies => {
      CompanyRepository.companyTechnologies.map(e => e._1 -> e._2.intersect(technologies).length).toSeq.sortBy(-_._2).map(_._1).take(20)
    })
    println(neighbors)
    Ok(Json.toJson(neighbors))
  }}
}
