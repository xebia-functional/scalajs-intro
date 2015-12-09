package example

import common.messages.{WeatherResponse, Weather, User, UserResponse}
import example.JsParser._
import org.scalajs.dom.ext.Ajax
import org.scalajs.jquery.{jQuery => $}
import upickle._

import scala.concurrent.Future
import scala.scalajs.concurrent.JSExecutionContext.Implicits.runNow
import scala.scalajs.js.annotation.JSExport

@JSExport
object ForComprehension {

  @JSExport
  def main(): Unit = {

    implicit val userReader = createUserReader
    implicit val userPrinter = new BasicUserPrinter

    getUsers map (_.foreach(user => {
      getWeather(user.postalCode).map(w => especial(user.firstName, w.description))
    }))

  }

  def getUsers(implicit reader: Reader[UserResponse]): Future[Seq[User]] =
    Ajax.get("/api/user/20") map { request =>
      read[UserResponse](request.responseText)
    } map (_.users)

  def getWeather(postalCode: String): Future[Weather] =
    Ajax.get("/api/weather/es/"+postalCode) map { request =>
      read[WeatherResponse](request.responseText)
    } map (_.weathers.head)

  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

  def printWeather(weather: Weather) = {
    val weatherNode = $("<div></div>").text(weather.description)
    $("body").append(weatherNode)
  }

  def especial(name: String, weather: String) = {
    val weatherNode = $("<div></div>").text(name + " - " + weather)
    $("body").append(weatherNode)
  }



}
