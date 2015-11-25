package controllers.messages

case class ApiRandomUserResponse(
  results: Seq[ApiUserResponse],
  nationality: String,
  seed: String,
  version: String)

case class ApiUserResponse(user: ApiUser)

case class ApiUser(
  gender: String,
  name: ApiName,
  location: ApiLocation,
  email: String,
  username: String,
  password: String,
  salt: String,
  md5: String,
  sha1: String,
  sha256: String,
  registered: Int,
  dob: Int,
  phone: String,
  cell: String,
  picture: ApiPicture)

case class ApiName(
  title: String,
  first: String,
  last: String)

case class ApiLocation(
  street: String,
  city: String,
  state: String,
  zip: Int)

case class ApiPicture(
  large: String,
  medium: String,
  thumbnail: String)