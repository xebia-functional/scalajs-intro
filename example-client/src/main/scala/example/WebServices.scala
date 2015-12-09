package example

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js

import scala.scalajs.js.annotation.JSExport

@JSExport
object WebServices  {

  @JSExport
  def main(): Unit = {

    val url = "/api/user/20"

    Ajax.get(url).onSuccess{ case xhr =>

      addPre(document.body, js.JSON.stringify(
        js.JSON.parse(xhr.responseText),
        space=4
      ))
    }

  }

  def addPre(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("pre")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }



}
