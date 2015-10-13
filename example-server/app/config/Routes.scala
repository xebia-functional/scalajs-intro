package config

import play.api.mvc.PathBindable.Parsing

object Routes {

  implicit object bindableChar extends Parsing[Char](
    _.charAt(0), _.toString, (key: String, e: Exception) => "Cannot parse parameter %s as Char: %s".format(key, e.getMessage)
  )

}