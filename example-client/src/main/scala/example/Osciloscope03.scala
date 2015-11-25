package example

import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.scalajs.js.annotation.JSExport

@JSExport
object Osciloscope03  {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    implicit val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    dom.window.addEventListener("resize", { e: dom.Event => resizeCanvas }, false)
    resizeCanvas
  }

  def drawStuff(x: Double)(implicit ctx: CanvasRenderingContext2D) {
    val (h, w) = (ctx.canvas.height, ctx.canvas.width)

    (x + 1) % w match{
      case 0 => {
        ctx.clearRect(0, 0, w, h)
        drawStuff(0)
      }
      case _ => dom.setTimeout(() => {
        ctx.fillStyle = "#FF9900"
        ctx.fillRect(x, 100, 30, 30)
        drawStuff(x+1)
      }, 10)
    }
  }

  def resizeCanvas(implicit ctx: CanvasRenderingContext2D) = {
    ctx.canvas.width = dom.window.innerWidth
    ctx.canvas.height = dom.window.innerHeight
    drawStuff(0.0)
  }


}
