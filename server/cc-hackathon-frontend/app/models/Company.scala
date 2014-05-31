package models

/**
 * Yoocos Sàrl
 * User: David
 * Date: 31.05.2014
 * Time: 14:28
 */

object CompanyRepository {

}

case class Company(
name:String,
domain:String,
funds:Seq[VC]
)

case class VC(name:String, amount:BigDecimal)