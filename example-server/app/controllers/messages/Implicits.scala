package controllers.messages

import common.messages._
import play.api.libs.json.{Writes, Json, Reads}

object ApiRandomUserImplicits {

  implicit val nameReads: Reads[ApiName] = Json.reads[ApiName]
  implicit val LocationReads: Reads[ApiLocation] = Json.reads[ApiLocation]
  implicit val PictureReads: Reads[ApiPicture] = Json.reads[ApiPicture]
  implicit val userReads: Reads[ApiUser] = Json.reads[ApiUser]
  implicit val userResponseReads: Reads[ApiUserResponse] = Json.reads[ApiUserResponse]
  implicit val randomUserResponseReads: Reads[ApiRandomUserResponse] = Json.reads[ApiRandomUserResponse]

}

object ApiWeatherImplicits {

  implicit val windReads: Reads[ApiWind] = Json.reads[ApiWind]
  implicit val weatherReads: Reads[ApiWeather] = Json.reads[ApiWeather]
  implicit val sysReads: Reads[ApiSys] = Json.reads[ApiSys]
  implicit val mainReads: Reads[ApiMain] = Json.reads[ApiMain]
  implicit val coordReads: Reads[ApiCoord] = Json.reads[ApiCoord]
  implicit val clodsReads: Reads[ApiClouds] = Json.reads[ApiClouds]
  implicit val weatherResponseReads: Reads[ApiWeatherResponse] = Json.reads[ApiWeatherResponse]

}

object CommonImplicits {

  implicit val mediaWrites: Writes[Media] = Json.writes[Media]
  implicit val userWrites: Writes[User] = Json.writes[User]
  implicit val userResponseWrites: Writes[UserResponse] = Json.writes[UserResponse]
  implicit val weatherWrites: Writes[Weather] = Json.writes[Weather]

}