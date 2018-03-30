package pl.writeonly.son2.apis.notation

import scala.util.control.Exception.catching

trait NotationReader extends PartialFunction[String, Any] {
  //  override def toString() = MoreObjects.toStringHelper(this).toString

  override def apply(content: String): Any

  override def isDefinedAt(content: String): Boolean =
    catching(classOf[Exception])
      .opt(apply(content))
      .isDefined
}