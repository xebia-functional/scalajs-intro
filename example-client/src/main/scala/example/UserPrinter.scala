package example

import common.messages.User
import org.scalajs.dom.document

trait UserPrinter {
  def print(user: User)
}

class BasicUserPrinter extends UserPrinter {

  override def print(user: User) = {
    val userNode = document.createElement("div")
    val textNode = document.createTextNode(name(user))
    userNode.appendChild(textNode)
    document.body.appendChild(userNode)
  }

  def name(user: User): String = user.lastName match {
    case Some(lastName) => s"${user.firstName} $lastName"
    case None => user.firstName
  }

}
