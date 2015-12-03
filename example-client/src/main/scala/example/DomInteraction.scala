package example

import common.messages.{Media, User}
import org.scalajs.dom.document

import scala.scalajs.js.annotation.JSExport

@JSExport
object DomInteraction  {

  @JSExport
  def main(): Unit = {
    val rafaUser = User("Rafa", Some("Paradela"), "41130", Media("pic400.jpg", "pic200.jpg", "pic50.jpg"))
    val fedeUser = User("Fede", None, "41130", Media("pic400.jpg", "pic200.jpg", "pic50.jpg"))
    printUser(rafaUser)
    printUser(fedeUser)
  }

  def printUser(user: User): Unit = {
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
