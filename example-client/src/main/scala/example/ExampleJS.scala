package example

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import org.scalajs.jquery.{jQuery => $, JQueryEventObject}

object ExampleJS extends js.JSApp {

  def main(): Unit = {
//    goHome()
//    $.ajaxSetup(Map("cache" -> false))

    $("body").keydown((event: dom.KeyboardEvent) => {
      event.keyCode match{
        case 37 => move(-1)
        case 39 => move(1)
        case 38 => move(5)
        case 40=> move(-5)
        case 48=> move(steps.size * -1)
        case 72=> goHome()
        case x => println(s"Pressed invalid key $x")
      }
    })

    move(16)

  }

  val home = Step("", "home.html")

  def goHome() = selectStep(home)

  val steps = Seq(
    Step("one", "step00.html"),
    Step("one", "step01.html"),
    Step("one", "step01_a.html"),
    Step("one", "step02.html"),
    Step("one", "step03.html"),
    Step("one", "step04.html"),
    Step("one", "step05.html"),
    Step("one", "step06.html"),
    Step("one", "step07.html"),
    Step("one", "step08.html"),
    Step("one", "step09.html"),
    Step("one", "step10.html"),
    Step("double", "step10.html", Option("step10_exp.html")),
    Step("one", "step11.html"),
    Step("double", "step11.html", Option("step11_exp.html")),
    Step("one", "step12.html"),
    Step("double", "step12.html", Option("step12_exp.html")),
    Step("one", "step13.html"),
    Step("one", "step14.html"),
    Step("double", "step14.html", Option("step13_exp.html")),
    Step("one", "step15.html"),
    Step("double", "step15.html", Option("step15_exp.html")),
    Step("one", "step00.html"))

  case class Step(
      layout: String,
      content_left: String,
      content_right: Option[String] = None)


  def selectStep(step: Step): Unit = {
    setLayout(step.layout)
    fillLeft(step.content_left)
    step.content_right match {
      case Some(url) => fillRight(url)
      case _ => Unit
    }
  }

  @JSExport
  def move(sum: Int): Unit = {
    val next =  $("#current").text().toInt + sum
    val max = steps.size-1
    next match {
      case m if m <= 0 => {
        $("#prev").attr("disabled", "disabled")
        $("#current").text(0.toString)
        selectStep(steps.head)
      }
      case m if m >= max => {
        $("#next").attr("disabled", "disabled")
        $("#current").text(max.toString)
        selectStep(steps.last)
      }
      case _ =>{
        $("#next").add("#prev").removeAttr("disabled")
        $("#current").text(next.toString)
        selectStep(steps(next))
      }
    }
  }


  def setLayout(layOut: String): Unit = $("body").removeClass().addClass(layOut)

  def fillLeft(path: String): Unit = {
    $("#left_wrapper").fadeOut(500, "linear", callback = { (data: js.Any) =>
      $.get(url = s"assets/subpages/$path", success = { (data: js.Any) =>
        $("#left_wrapper").html(data.toString).fadeIn(500)
      })
    })
  }

  def fillRight(path: String): Unit = {
    $("#right_wrapper").fadeOut(100, "linear", callback = { (data: js.Any) =>
      $("#right_wrapper").attr("src", "assets/subpages/" + path).fadeIn(900)
    })
  }

}
