package services

/**
 * Yoocos Sàrl
 * User: David
 * Date: 31.05.2014
 * Time: 17:14
 */
import play.api._
import models.CompanyRepository

object Global extends GlobalSettings {

  override def onStart(app: Application) {
    CompanyRepository.init
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }

}
