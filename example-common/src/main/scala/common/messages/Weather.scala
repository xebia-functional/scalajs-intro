package common.messages

case class WeatherResponse(weathers: Seq[Weather])

case class Weather(
  id: Int,
  title: String,
  description: String,
  icon: String)
