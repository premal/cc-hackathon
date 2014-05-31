package models

import scala.io.Source
import java.io.File
import java.net.URI
import scala.collection.mutable
import play.api.libs.json.Json

/**
 * Yoocos Sàrl
 * User: David
 * Date: 31.05.2014
 * Time: 14:28
 */

object CompanyRepository {

  var companies : Map[String,Map[String,String]] = Map()
  var companyTechnologies : Map[String,Seq[String]] = Map()
  var domainsToCompany : Map[String,String] = Map()

  def init{
    val data = Source.fromFile(new File("../../data/more_crunchbase.csv"))("UTF-8").getLines().map(_.split("\t").map(_.trim))
    val technologies = Source.fromFile(new File("../../data/filtered_domain_to_tech.out.tsv"))("UTF-8").getLines().map(_.split("\t").map(_.trim))

    companies = (for(
      header <- data.take(1)
    ) yield {
      val companies = data.map(toEntry(header,_)).map(m => m("name") -> m).toMap
      companies.keys.map(key => key.toLowerCase() -> createCompanyElement(companies,key)).toMap
    }).next()
    domainsToCompany = companies.map(e => e._2("domain") -> e._1)

    companyTechnologies = (for(
      header <- technologies.take(1)
    ) yield {
      technologies.foldLeft(mutable.Map[String,Seq[String]]())((map,entry) => {
        val techs = map.getOrElse(entry(0),Seq())
        (Json.parse(entry(3)) \ "name").asOpt[String].foreach(tech => map.put(entry(0),techs :+ tech))

        map
      })
    }).next().toMap
  }



  private def toEntry(header:Array[String], entry:Array[String]) : Map[String,String] =0 until Math.min(header.length,entry.length) map (i => header(i) -> entry(i)) toMap

  private def cleanDomain(url:String) = Option((new URI(url)).getHost).map(_.replace("www.","")).getOrElse("")

  private def createCompanyElement(companies:Map[String,Map[String,String]],company:String): Map[String,String] = {
    val companyData = companies.get(company).getOrElse(Map())
    Map(
      "name" -> company.toLowerCase,
      "domain" -> cleanDomain(companyData.getOrElse("url","***NOT FOUND***")).toLowerCase,
      "category" -> companyData.getOrElse("category","***UNKNOWN***").toLowerCase,
      "country" -> companyData.getOrElse("country","***UNKNOWN***"),
      "city" -> companyData.getOrElse("city","***UNKNOWN***")
    )
  }
}