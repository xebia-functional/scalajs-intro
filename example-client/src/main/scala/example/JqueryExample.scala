package example

import common.messages.{User, UserResponse}
import example.JsParser._
import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}
import upickle._
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object JqueryExample {

  @JSExport
  def main(): Unit = {

    implicit val userReader = createUserReader
    implicit val userPrinter = new BasicUserPrinter

    $("body").append($("<input>").attr("id", "search").keyup((e: dom.Event) => {
      filterUsers()
    }))

    $.get(url = "http://localhost:9000/api/user/20", success = { (data: js.Any) =>
      read[UserResponse](js.JSON.stringify(data)).users.foreach(printUser)
    })

  }

  def filterUsers(): Unit = $("div").each({ (f: Any, div: dom.Element) =>
    if ($(div).text() contains $("#search").value().toString) $(div).show(200)
    else $(div).hide(200)
  })


  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

}
