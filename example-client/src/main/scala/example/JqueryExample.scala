package example

import org.scalajs.jquery.jQuery
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

@JSExport
object JqueryExample  {

  @JSExport
  def main() = {
    jQuery("button").click((e: js.Any) => jQuery("body").append("<p>jQuery is working!</p>"))
  }

}
