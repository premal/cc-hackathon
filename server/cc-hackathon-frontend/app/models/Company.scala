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

  var companies : Seq[Seq[String]] = Seq()

  def init{
    val companiesData = Source.fromFile(new File("../../data/crunchbase_companies.csv"))("UTF-8").getLines().map(_.split(";").map(_.trim))
    val acquisitionsData = Source.fromFile(new File("../../data/crunchbase_acquisitions.csv"))("UTF-8").getLines().map(_.split(";").map(_.trim))
    val roundsData = Source.fromFile(new File("../../data/crunchbase_rounds.csv"))("UTF-8").getLines().map(_.split(";").map(_.trim))

    val result = (for(
      companyHeader <- companiesData.take(1);
      acquisitionHeader <- acquisitionsData.take(1);
      roundHeader <- roundsData.take(1)
    ) yield {
      val companies = companiesData.map(toEntry(companyHeader,_)).map(m => m("name") -> m).toMap
      val acquisitions = acquisitionsData.map(toEntry(acquisitionHeader,_)).map(m => m.get("company_name").map(_ -> m)).filter(_.isDefined).map(_.get).toMap
      val rounds = roundsData.map(toEntry(roundHeader,_)).map(m => m("company_name") -> m).toMap

      companies.keys.map(createCompanyElement(companies,acquisitions,rounds,_))
    })
    companies = result.next().toSeq
  }

  private def toEntry(header:Array[String], entry:Array[String]) : Map[String,String] =0 until Math.min(header.length,entry.length) map (i => header(i) -> entry(i)) toMap

  private def cleanDomain(url:String) = Option((new URI(url)).getHost).map(_.replace("www.","")).getOrElse("")

  private def createCompanyElement(companies:Map[String,Map[String,String]],acquisitions:Map[String,Map[String,String]],rounds:Map[String,Map[String,String]],company:String): Seq[String] = {
    val companyData = companies.get(company).getOrElse(Map())
    val acquisitionsData = acquisitions.get(company).getOrElse(Map())
    val roundsData = rounds.get(company).getOrElse(Map())
    Seq(company,cleanDomain(companyData.get("homepage_url").getOrElse("***NOT FOUND***")),roundsData.get("company_category_code").getOrElse("***UNKNOWN***"))
  }
}

case class Company(
name:String,
domain:String,
funds:Seq[VC]
)

case class VC(name:String, amount:BigDecimal)