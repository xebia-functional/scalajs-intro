package controllers.messages

import commons.messages.User
import play.api.libs.json.{Writes, Json, Reads}

object ApiRandomUserImplicits {

  implicit val nameReads: Reads[ApiName] = Json.reads[ApiName]
  implicit val LocationReads: Reads[ApiLocation] = Json.reads[ApiLocation]
  implicit val PictureReads: Reads[ApiPicture] = Json.reads[ApiPicture]
  implicit val userReads: Reads[ApiUser] = Json.reads[ApiUser]
  implicit val userResponseReads: Reads[ApiUserResponse] = Json.reads[ApiUserResponse]
  implicit val randomUserResponseReads: Reads[ApiRandomUserResponse] = Json.reads[ApiRandomUserResponse]

}

object CommonImplicits {

  implicit val userWrites: Writes[User] = Json.writes[User]

}