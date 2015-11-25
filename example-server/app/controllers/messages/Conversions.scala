package controllers.messages

import commons.messages.User

trait Conversions {

  def toUser(apiUser: ApiUser): User =
    User(
      firstName = apiUser.name.first,
      lastName = apiUser.name.last)

}
