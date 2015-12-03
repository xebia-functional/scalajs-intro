package common.messages

case class UserResponse(users: Seq[User])

case class User(
    firstName: String,
    lastName: Option[String] = None,
    postalCode: String,
    pictures: Media)

case class Media(
    large: String,
    medium: String,
    thumbnail: String)