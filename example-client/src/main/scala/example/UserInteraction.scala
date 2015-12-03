package example

import common.messages.{UserResponse, User}
import org.scalajs.dom.ext.Ajax
import org.scalajs.dom.raw.{HTMLDivElement, HTMLInputElement}
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import org.scalajs.dom.document
import upickle._
import JsParser._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object UserInteraction {

  @JSExport
  def main(): Unit = {

    implicit val userReader = createUserReader
    implicit val userPrinter = new BasicUserPrinter


    Ajax.get("http://localhost:9000/api/user/20") map { request =>
      read[UserResponse](request.responseText)
    } onSuccess{ case response => response.users.foreach(printUser) }

  }

  @JSExport
  def filterUsers(): Unit = {
    val text = document.getElementById("search").asInstanceOf[HTMLInputElement].value
    val divs = document.getElementsByTagName("div")
    0 to divs.length foreach { index =>
      val div = divs.item(index).asInstanceOf[HTMLDivElement]
      if (div.textContent contains text) div.setAttribute("class", "shown")
      else div.setAttribute("class", "hidden")
    }
  }

  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

}
