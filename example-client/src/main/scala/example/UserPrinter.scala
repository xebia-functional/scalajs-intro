package example

import common.messages.User
import org.scalajs.dom.document
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
    val userNode = $("<div></div>").text(name(user))
    $("body").append(userNode)
  }

}