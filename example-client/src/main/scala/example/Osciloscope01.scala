package example

import org.scalajs.dom.raw.HTMLCanvasElement
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}

@JSExport
object Osciloscope01  {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    implicit val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    dom.window.addEventListener("resize", { e: dom.Event => resizeCanvas }, false)
    resizeCanvas
  }

  def drawStuff(implicit ctx: CanvasRenderingContext2D) {
    ctx.fillStyle = "#FF9900"
    ctx.fillRect(0, 0, 30, 30)
  }

  def resizeCanvas(implicit ctx: CanvasRenderingContext2D) = {
    ctx.canvas.width = dom.window.innerWidth
    ctx.canvas.height = dom.window.innerHeight
    drawStuff
  }

}
