package example

import common.messages.{UserResponse, User}
import org.scalajs.dom.ext.Ajax
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import upickle._
import JsParser._

import scala.scalajs.js.annotation.JSExport

@JSExport
object WebServicesEnriched {

  @JSExport
  def main(): Unit = {

    implicit val userReader = createUserReader
    implicit val userPrinter = new BasicUserPrinter

    Ajax.get("http://localhost:9000/api/user/20") map { request =>
      read[UserResponse](request.responseText)
    } onSuccess{ case response => response.users.foreach(printUser) }

  }

  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

}
