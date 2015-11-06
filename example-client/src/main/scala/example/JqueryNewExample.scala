package example

import org.scalajs.dom
import org.scalajs.jquery.{jQuery => $}

import scala.scalajs.js.JSConverters.JSRichGenMap
import scala.scalajs.js.annotation.JSExport

@JSExport
object JqueryNewExample  {

  @JSExport
  def main() = mates foreach createMate

  case class TeamMate(
      name: String,
      photo: String,
      twitter: Option[String] = None)

  val mates = Seq(
    TeamMate("Rafa", "http://www.47deg.com/assets/img/company/profile-rafa.jpg", Some("rafaparadela")),
    TeamMate("Paco", "http://www.47deg.com/assets/img/company/profile-paco.jpg", Some("franciscodr")),
    TeamMate("Nick", "http://www.47deg.com/assets/img/company/profile-nick.jpg"),
    TeamMate("Jorge", "http://www.47deg.com/assets/img/company/profile-jorge.jpg", Some("Jorge__Galindo")),
    TeamMate("Raul", "http://www.47deg.com/assets/img/company/profile-raul.jpg", Some("raulraja")),
    TeamMate("Fede", "http://www.47deg.com/assets/img/company/profile-fede.jpg", Some("fede_fdz")),
    TeamMate("Javi", "http://www.47deg.com/assets/img/company/profile-javi.jpg", Some("javielinux")),
    TeamMate("Fran", "http://www.47deg.com/assets/img/company/profile-fran.jpg", Some("fperezp")),
    TeamMate("Juanpe", "http://www.47deg.com/assets/img/company/profile-juanpe.jpg", Some("juanpedromoreno")),
    TeamMate("Aaron", "http://www.47deg.com/assets/img/company/profile-aaron.jpg"),
    TeamMate("Domin", "http://www.47deg.com/assets/img/company/profile-domin.jpg", Some("nebelig")),
    TeamMate("Justin", "http://www.47deg.com/assets/img/company/profile-justin.jpg"),
    TeamMate("Juan", "http://www.47deg.com/assets/img/company/profile-juan.jpg", Some("juan")),
    TeamMate("Ana", "http://www.47deg.com/assets/img/company/profile-ana.jpg", Some("anamariamarvel")),
    TeamMate("Javi", "http://www.47deg.com/assets/img/company/profile-javi2.jpg", Some("javitaiyou")),
    TeamMate("Isra", "http://www.47deg.com/assets/img/company/profile-israel.jpg")
  )


private def createMate(mate: TeamMate): Unit = {

  val footer = $("<div></div>").append($("<h2></h2>").text(mate.name))

  mate.twitter match {
    case Some(t) => footer.append($(s"<a href='http://twitter.com/$t'></a>").text(t))
    case _ => footer.append($("<p></p>").text("---"))
  }

  val element = $("<div></div>")
      .attr(Map("class" -> "team-wrapper", "style" -> s"background-image:url(${mate.photo})").toJSDictionary)
      .append(footer)
      .hide()
      .fadeIn(scala.util.Random.nextInt(10000))

  $("body").append(element)

}


}
