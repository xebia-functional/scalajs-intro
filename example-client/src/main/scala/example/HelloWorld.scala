package example

import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.Dynamic.global

@JSExport
object HelloWorld  {

  @JSExport
  def main(): Unit = global.alert("Hello world!")

}
