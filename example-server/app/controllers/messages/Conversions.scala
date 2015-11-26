package controllers.messages

import commons.messages.{Weather, User}

trait Conversions {

  def toUser(apiUser: ApiUser): User =
    User(
      firstName = apiUser.name.first,
      lastName = apiUser.name.last,
      postalCode = apiUser.location.zip.toString)

  def toWeather(apiWeather: ApiWeather): Weather =
    Weather(
      apiWeather.id,
      apiWeather.main,
      apiWeather.description)

}
