package example

import org.scalajs.dom
import org.scalajs.dom.document
import org.scalajs.dom.ext.Ajax
import scala.concurrent.ExecutionContext
import scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js

import scala.scalajs.js.annotation.JSExport

@JSExport
object WebServices  {

  @JSExport
  def main(): Unit = {

    val url ="https://randomuser.me/api/"

    Ajax.get(url).onSuccess{ case xhr =>

      addParagraph(document.body, js.JSON.stringify(
        js.JSON.parse(xhr.responseText),
        space=4
      ))
    }

  }

  def addParagraph(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("pre")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }



}
