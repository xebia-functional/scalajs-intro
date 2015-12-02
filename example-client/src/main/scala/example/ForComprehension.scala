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

    for {
      userResponse <- getUsers
      weatherResponse <- getWeather
    } yield {
      weatherResponse.weathers foreach printWeather
      userResponse.users foreach printUser
    }

  }

  def getUsers(implicit reader: Reader[UserResponse]): Future[UserResponse] =
    Ajax.get("http://localhost:9000/api/user/20") map { request =>
      read[UserResponse](request.responseText)
    }

  def getWeather: Future[WeatherResponse] =
    Ajax.get("http://localhost:9000/api/weather/es/41000") map { request =>
      read[WeatherResponse](request.responseText)
    }

  def printUser(user: User)(implicit printer: UserPrinter) = printer.print(user)

  def printWeather(weather: Weather) = {
    val weatherNode = $("<div></div>").text(weather.description)
    $("body").append(weatherNode)
  }

}
