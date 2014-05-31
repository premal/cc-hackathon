package controllers

import play.api.mvc._
import scala.io.Source
import java.net.URI
import java.io.File

object MainController extends Controller {

  /**
   * The index page.  This is the main entry point, seeing as this is a single page app.
   */
  def index() = Action {
    Ok(views.html.index())
  }

  def cleanCSV = Action(parse.multipartFormData) {implicit request =>
    val f = request.body.file("file").get
    val tmpFile = File.createTempFile("crunchbase","csv")

    f.ref.moveTo(tmpFile,true)
    val result = Source.fromFile(tmpFile).getLines().filter(!_.isEmpty).map(uri => new URI(uri)).filter(uri => Option(uri.getHost).isDefined).map(_.getHost.replace("www.","").toLowerCase + "\n").foldLeft(new StringBuilder())((builder,s) => builder.append(s)).toString()

    Ok(result)
  }
}
