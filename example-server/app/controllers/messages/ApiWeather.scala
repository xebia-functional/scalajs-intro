package controllers.messages

case class ApiWeatherResponse(
  id: Long,
  dt: Long,
  cod: Int,
  name: String,
  coord: ApiCoord,
  sys: ApiSys,
  weather: Seq[ApiWeather],
  base: String,
  main: ApiMain,
  wind: ApiWind,
  clouds: ApiClouds)

case class ApiClouds(all: Int)

case class ApiCoord(lon: Double, lat: Double)

case class ApiMain(
  temp: Double,
  pressure: Double,
  humidity: Int,
  temp_min: Double,
  temp_max: Double,
  sea_level: Double,
  grnd_level: Double)


case class ApiSys(
  message: Double,
  country: String,
  sunrise: Int,
  sunset: Int)

case class ApiWeather(
  id: Int,
  main: String,
  description: String,
  icon: String)

case class ApiWind(
  speed: Double,
  deg: Double)