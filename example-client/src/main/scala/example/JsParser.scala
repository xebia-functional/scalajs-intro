package example

import common.messages.{User, Media}
import upickle.Js

object JsParser {

  def readMandatoryField(js: Map[String, Js.Value], key: String): String =
    readField(js, key) getOrElse (throw new RuntimeException(s"Field $key not found"))

  def readField(js: Map[String, Js.Value], key: String): Option[String] = js.get(key) map (_.value.toString)

  def toMedia(jsValue: Js.Obj): Media = {
    val map = jsValue.value.toMap
    Media(
      large = readMandatoryField(map, "large"),
      medium = readMandatoryField(map, "medium"),
      thumbnail = readMandatoryField(map, "thumbnail"))
  }

  def createUserReader = upickle.Reader[User]{
    case obj: Js.Obj =>
      val map = obj.value.toMap
      User(
        firstName = readMandatoryField(map, "firstName"),
        lastName = readField(map, "lastName"),
        postalCode = readMandatoryField(map, "postalCode"),
        pictures = map.get("pictures") map {
          case jsOb: Js.Obj => toMedia(jsOb)
        } getOrElse (throw new RuntimeException(s"Media not found"))
      )
  }
}
