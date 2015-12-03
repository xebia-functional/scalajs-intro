package example

import common.messages.User
import org.scalajs.dom.document
import scala.scalajs.js.JSConverters.JSRichGenMap
import org.scalajs.jquery.{jQuery => $}

trait UserPrinter {

  def print(user: User)

  def name(user: User): String = user.lastName match {
    case Some(lastName) => s"${user.firstName} $lastName"
    case None => user.firstName
  }
}

class BasicUserPrinter extends UserPrinter {

  override def print(user: User) = {
    val userNode = document.createElement("div")
    val textNode = document.createTextNode(name(user))
    userNode.appendChild(textNode)
    document.body.appendChild(userNode)
  }

}

class JQueryUserPrinter extends UserPrinter {

  override def print(user: User): Unit = {

val footer = $("<div></div>").append($("<h2></h2>").text(name(user))).append($("<p></p>").text(s"Postal code: ${user.postalCode}"))

val element = $("<div></div>")
    .attr(Map("class" -> "userWrapper", "style" -> s"background-image:url(${user.pictures.medium})").toJSDictionary)
    .append(footer)
    .hide()
    .fadeIn(scala.util.Random.nextInt(10000))

$("body").append(element)
}

}