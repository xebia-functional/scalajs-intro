package example

import common.messages.{Media, User}

import scala.scalajs.js.annotation.JSExport

@JSExport
object Implicits {

  @JSExport
  def main() = {

    implicit val userPrinter = new BasicUserPrinter

    val rafaUser = User("Rafa", Some("Paradela"), "41130", Media("pic400.jpg", "pic200.jpg", "pic50.jpg"))
    val fedeUser = User("Fede", None, "41130", Media("pic400.jpg", "pic200.jpg", "pic50.jpg"))

    printUser(rafaUser)
    printUser(fedeUser)
  }

  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

}
