package example

import common.messages.{User, UserResponse}
import example.JsParser._
import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}
import upickle._
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object JqueryImplicits {

  @JSExport
  def main(): Unit = {

    implicit val userReader = createUserReader
    implicit val userPrinter = new JQueryUserPrinter

    $.get(url = "/api/user/20", success = { (data: js.Any) =>
      read[UserResponse](js.JSON.stringify(data)).users.foreach(printUser)
    })

  }

  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

}
