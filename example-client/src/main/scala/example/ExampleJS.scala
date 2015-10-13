package example

import scala.scalajs.js
import scala.scalajs.js.JSConverters.JSRichGenMap
import org.scalajs.jquery.{jQuery => $, JQueryEventObject}

object ExampleJS extends js.JSApp {

  def main(): Unit = {
    $.ajaxSetup(Map("cache" -> false))
    steps.foreach(s => drawStepButton(s))
    $("#learn-more").click { (event: JQueryEventObject) => selectStep(steps.head) }
  }

  val steps = Seq(
    Step(1, "step1", "one", "step01.html"),
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

  def drawStepButton(s: Step): Unit = {

    val button = $("<div></div>")
        .attr(Map("type" -> "button", "class" -> "btn btn-circle").toJSDictionary)
        .addClass(s.name)
        .text(s.id.toString)
        .click { (event: JQueryEventObject) => selectStep(s) }

    val item = $("<div></div>")
        .attr("class", "stepwizard-step")
        .append(button)

    $("#stepwizardWrapper").append(item)
  }

  def selectStep(step: Step): Unit = {
    markSelected(step.name)
    setLayout(step.layout)
    fillLeft(step.content_left)
    step.content_right match {
      case Some(url) => fillRight(url)
      case _ => Unit
    }
  }

  def markSelected(name: String): Unit = {
    $(".stepwizard-step>.btn").removeClass("active")
    $(".stepwizard-step>." + name).addClass("active")
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
