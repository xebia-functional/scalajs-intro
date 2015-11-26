package example.slides

sealed trait LayoutType

case object LayoutMaster extends LayoutType

case object LayoutSingle extends LayoutType

case object LayoutDouble extends LayoutType

case class Step(
  layout: LayoutType,
  leftPage: String,
  rightPage: Option[String] = None)

object Step {

  def apply(leftPage: String): Step =
    Step(LayoutSingle, leftPage, None)

  def apply(contentLeft: String, rightPage: String): Step =
    Step(LayoutDouble, contentLeft, Some(rightPage))

}