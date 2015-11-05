package example

import java.lang.Math._

import org.scalajs.dom
import org.scalajs.dom.{CanvasRenderingContext2D, html}

import scala.scalajs.js.annotation.JSExport

@JSExport
object Osciloscope06  {
  @JSExport
  def main(canvas: html.Canvas): Unit = {
    implicit val ctx = canvas.getContext("2d").asInstanceOf[dom.CanvasRenderingContext2D]
    dom.window.addEventListener("resize", { e: dom.Event => resizeCanvas }, false)
    resizeCanvas
  }

  case class Color (name: String)
  case class Graph (id: Int, color: Color, func: Double => Double)

  val redColor = Color("red")
  val zeroGraph = Graph(0, redColor, x => 0)
  val sinGraph = Graph(1, Color("green"), x => sin(x))

  def stepFunc(x: Double): Double = x % 10 > 5 match {
    case true => 0
    case _ => 2
  }

  val stepGraph = Graph(2, Color("blue"), stepFunc)

  val graphs = List(zeroGraph, sinGraph, stepGraph)

def drawGraph(graph: Graph, x: Double)(implicit ctx: CanvasRenderingContext2D): Unit = {
  val (h, w) = (ctx.canvas.height, ctx.canvas.width)
  val y = graph.func(x/w * 75) * h/40 + h/4 * (graph.id+0.2)
  ctx.fillStyle = graph.color.name
  ctx.fillRect(x, y, 3, 3)
}

  def drawStuff(x: Double)(implicit ctx: CanvasRenderingContext2D) {
    val (h, w) = (ctx.canvas.height, ctx.canvas.width)

    (x + 1) % w match{
      case 0 => {
        ctx.clearRect(0, 0, w, h)
        drawStuff(0)
      }
      case _ => dom.setTimeout(() => {
        graphs.foreach(drawGraph(_, x))
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
