package controllers

import commons.messages.Weather
import controllers.messages.{ApiWeatherResponse, Conversions, ApiRandomUserResponse}
import play.api.Play
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.ws._
import play.api.Play.current
import scala.concurrent.Future
import controllers.messages.ApiRandomUserImplicits._
import controllers.messages.ApiWeatherImplicits._
import controllers.messages.CommonImplicits._
import scala.util.control.NonFatal

object ApiController
  extends Controller
  with Conversions {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  def randomUser(results: Int) = Action.async { implicit request =>

    val holder : WSRequestHolder = WS.url(s"https://randomuser.me/api/?nat=es&results=$results")

    val futureResponse : Future[WSResponse] = holder.get()

    futureResponse map { response =>
      val randomUserResponse = response.json.as[ApiRandomUserResponse]
      val userSeq = randomUserResponse.results map (ur => toUser(ur.user))
      Ok(Json.toJson(userSeq))
    }
  }

  def weather(countryCode: String, postalCode: String) = Action.async { implicit request =>
    Play.current.configuration.getString("openweather.appid") match {
      case Some(appId) =>
        weatherRequest(appId, countryCode, postalCode) map { seq =>
          Ok(Json.toJson(seq))
        }
      case _ =>
        Future.successful(Unauthorized("Property 'openweather.appid' not found"))
    }
  }

  private[this] def weatherRequest(
    appId: String,
    countryCode: String,
    postalCode: String): Future[Seq[Weather]] = {

    val holder : WSRequestHolder = WS.url(s"http://api.openweathermap.org/data/2.5/weather?zip=$postalCode,$countryCode&appid=$appId")

    holder.get().map { response =>
      val weatherResponse = response.json.as[ApiWeatherResponse]
      weatherResponse.weather map toWeather
    } recover {
      case NonFatal(t) => Seq(randomWeather)
    }
  }


}
