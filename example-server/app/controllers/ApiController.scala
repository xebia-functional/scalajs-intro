package controllers

import controllers.messages.{Conversions, ApiRandomUserResponse}
import play.api.libs.json.Json
import play.api.mvc._
import play.api.libs.ws._
import play.api.Play.current
import scala.concurrent.Future
import controllers.messages.ApiRandomUserImplicits._
import controllers.messages.CommonImplicits._

object ApiController
  extends Controller
  with Conversions {

  implicit val context = scala.concurrent.ExecutionContext.Implicits.global

  def randomUser(results: Int) = Action.async { implicit request =>

    val holder : WSRequestHolder = WS.url(s"https://randomuser.me/api/?results=$results")

    val futureResponse : Future[WSResponse] = holder.get()

    futureResponse map { response =>
      val randomUserResponse = response.json.as[ApiRandomUserResponse]
      val userSeq = randomUserResponse.results map (ur => toUser(ur.user))
      Ok(Json.toJson(userSeq))
    }
  }

}
