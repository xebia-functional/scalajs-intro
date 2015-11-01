package example

import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import org.scalajs.jquery.{jQuery => $, JQueryEventObject}

object ExampleJS extends js.JSApp {

  def main(): Unit = {
    goHome()
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

  }

  val home = Step(0, "home", "", "home.html")

  def goHome() = selectStep(home)

  val steps = Seq(
    Step(1, "step1", "one", "step00.html"),
    Step(2, "step2", "one", "step02.html"),
    Step(3, "step3", "one", "step03.html"),
    Step(4, "step4", "double", "step04.html", Option("step04_exp.html")),
    Step(5, "step5", "double", "step05.html", Option("step05_exp.html")),
    Step(6, "step6", "double", "step06.html", Option("step06_exp.html")),
    Step(7, "step7", "one", "step07.html"))

  case class Step(
      id: Int,
      name: String,
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
