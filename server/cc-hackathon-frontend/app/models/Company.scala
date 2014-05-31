package models

import scala.io.Source
import java.io.File
import java.net.URI

/**
 * Yoocos Sàrl
 * User: David
 * Date: 31.05.2014
 * Time: 14:28
 */

object CompanyRepository {

  var companies : Map[String,Map[String,String]] = Map()
  var companyTechnologies : Map[String,Seq[String]] = Map()

  def init{
    val data = Source.fromFile(new File("../../data/more_crunchbase.csv"))("UTF-8").getLines().map(_.split("\t").map(_.trim))

    val result = (for(
      header <- data.take(1)
    ) yield {
      val companies = data.map(toEntry(header,_)).map(m => m("name") -> m).toMap
      companies.keys.map(key => key -> createCompanyElement(companies,key)).toMap
    })
    companies = result.next()
  }

  private def toEntry(header:Array[String], entry:Array[String]) : Map[String,String] =0 until Math.min(header.length,entry.length) map (i => header(i) -> entry(i)) toMap

  private def cleanDomain(url:String) = Option((new URI(url)).getHost).map(_.replace("www.","")).getOrElse("")

  private def createCompanyElement(companies:Map[String,Map[String,String]],company:String): Map[String,String] = {
    val companyData = companies.get(company).getOrElse(Map())
    Map(
      "name" -> company,
      "domain" -> cleanDomain(companyData.getOrElse("url","***NOT FOUND***")),
      "category" -> companyData.getOrElse("category","***UNKNOWN***"),
      "country" -> companyData.getOrElse("country","***UNKNOWN***"),
      "city" -> companyData.getOrElse("city","***UNKNOWN***")
    )
  }
}