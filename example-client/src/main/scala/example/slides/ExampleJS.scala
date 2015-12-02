package example.slides

import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport

object ExampleJS extends js.JSApp {

  def main(): Unit = {
//    goHome()
//    $.ajaxSetup(Map("cache" -> false))

    $("body").keydown((event: dom.KeyboardEvent) => {
      event.keyCode match{
        case 37 => move(-1)
        case 33 => move(-1)
        case 39 => move(1)
        case 34 => move(1)
        case 38 => move(5)
        case 40=> move(-5)
        case 48=> move(steps.size * -1)
        case 72=> goHome()
        case x => println(s"Pressed invalid key $x")
      }
    })

    move(17)

  }

  val home = Step(LayoutMaster, "home")

  def goHome() = selectStep(home)

  val steps = Seq(
    Step("about_us"),
    Step("surprising_behaviour_01"),
    Step("surprising_behaviour_02"),
    Step("surprising_behaviour_03"),
    Step("surprising_behaviour_04"),
    Step("surprising_behaviour_05"),
    Step("surprising_behaviour_06"),
    Step("js_ugly_face"),
    Step("web_platform_goodies"),
    Step("what_is_scalajs"),
    Step("functional_programming"),
    Step("server_client_sharing_code"),
    Step("start_using_scalajs"),
    Step("start_using_scalajs_2"),
    Step("start_using_scalajs_2", "hello_world"),
    Step("case_classes"),
    Step("dom_interaction", "dom_interaction_code"),
    Step("implicits"),
    Step("implicits", "implicits_code"),
    Step("webservice_ajax"),
    Step("webservice_ajax", "webservice_ajax_code"),
    Step("webservice_enriched"),
    Step("webservice_enriched", "webservice_enriched_code"),
    Step("webservice_enriched", "user_interaction_code"),
    Step("step15"),
    Step("step15_b"),
    Step("step15_a"),
    Step("step15_a", "step15_exp"),
    Step("step16"),


    Step("step17"),
    Step("step17", "step17_exp"),
    Step("step18"),
    Step("step18", "step18_exp"),
    Step("step19"),
    Step("step19", "step19_exp"),
    Step("step20"),
    Step("step21"),
    Step("step22"),
    Step("step23"))


  def selectStep(step: Step): Unit = {
    setLayout(step.layout)
    fillLeft(step.leftPage)
    step.rightPage match {
      case Some(url) => fillRight(url)
      case _ =>
    }
  }

  @JSExport
  def move(sum: Int): Unit = {
    val next =  $("#current").text().toInt + sum
    val max = steps.size-1
    next match {
      case m if m <= 0 =>
        $("#prev").attr("disabled", "disabled")
        $("#current").text(0.toString)
        selectStep(steps.head)
      case m if m >= max =>
        $("#next").attr("disabled", "disabled")
        $("#current").text(max.toString)
        selectStep(steps.last)
      case _ =>
        $("#next").add("#prev").removeAttr("disabled")
        $("#current").text(next.toString)
        selectStep(steps(next))
    }
  }

  def setLayout(layoutType: LayoutType): Unit = {
    val jquery = $("body").removeClass()
    layoutType match {
      case LayoutSingle => jquery.addClass("one")
      case LayoutDouble => jquery.addClass("double")
      case _ =>
    }
  }

  def fillLeft(pageName: String): Unit = {
    $("#left_wrapper").fadeOut(500, "linear", callback = { (data: js.Any) =>
      $.get(url = s"assets/subpages/$pageName.html", success = { (data: js.Any) =>
        $("#left_wrapper").html(data.toString).fadeIn(500)
      })
    })
  }

  def fillRight(pageName: String): Unit = {
    $("#right_wrapper").fadeOut(100, "linear", callback = { (data: js.Any) =>
      $("#right_wrapper").attr("src", s"assets/subpages/$pageName.html").fadeIn(900)
    })
  }

}
