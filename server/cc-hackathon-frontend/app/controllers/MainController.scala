package controllers

import play.api.mvc._
import scala.io.Source
import java.net.URI
import java.io.File
import play.api.Logger
import scala.util.{Failure, Success, Try}

object MainController extends Controller {

  /**
   * The index page.  This is the main entry point, seeing as this is a single page app.
   */
  def index() = Action {
    Ok(views.html.index())
  }


  private def toEntry(header:Array[String], entry:Array[String]) : Map[String,String] =0 until Math.min(header.length,entry.length) map (i => header(i) -> entry(i)) toMap


  private def cleanDomain(url:String) = Option((new URI(url)).getHost).map(_.replace("www.","")).getOrElse("")

  private def createCompanyElement(companies:Map[String,Map[String,String]],acquisitions:Map[String,Map[String,String]],rounds:Map[String,Map[String,String]],company:String): Seq[String] = {
    val companyData = companies.get(company).getOrElse(Map())
    val acquisitionsData = acquisitions.get(company).getOrElse(Map())
    val roundsData = rounds.get(company).getOrElse(Map())
    Seq(company,cleanDomain(companyData.get("homepage_url").getOrElse("***NOT FOUND***")),roundsData.get("company_category_code").getOrElse("***UNKNOWN***"))
  }

  def companies = Action { implicit request => {
    val companiesData = Source.fromFile(new File("../../data/crunchbase_companies.csv"))("UTF-8").getLines().map(_.split(";").map(_.trim))
    val acquisitionsData = Source.fromFile(new File("../../data/crunchbase_acquisitions.csv"))("UTF-8").getLines().map(_.split(";").map(_.trim))
    val roundsData = Source.fromFile(new File("../../data/crunchbase_rounds.csv"))("UTF-8").getLines().map(_.split(";").map(_.trim))

    val builder = new StringBuilder
    val header = Seq("name","domain","category")
    builder.append(header.reduce(_ + ";" + _))
    builder.append("\n")
    for(
      companyHeader <- companiesData.take(1);
      acquisitionHeader <- acquisitionsData.take(1);
      roundHeader <- roundsData.take(1)
    ){
      val companies = companiesData.map(toEntry(companyHeader,_)).map(m => m("name") -> m).toMap
      val acquisitions = acquisitionsData.map(toEntry(acquisitionHeader,_)).map(m => m.get("company_name").map(_ -> m)).filter(_.isDefined).map(_.get).toMap
      val rounds = roundsData.map(toEntry(roundHeader,_)).map(m => m("company_name") -> m).toMap

      companies.keys.map(createCompanyElement(companies,acquisitions,rounds,_)).foreach(entry => builder.append(entry.reduceLeft(_ + ";" + _)).append("\n"))
    }

    Ok(builder.toString())
  }}
}
