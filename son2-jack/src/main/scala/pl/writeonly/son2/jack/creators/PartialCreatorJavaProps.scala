package pl.writeonly.son2.jack.creators

import com.fasterxml.jackson.dataformat.javaprop.JavaPropsMapper
import pl.writeonly.son2.jack.core.Formats
import pl.writeonly.son2.jack.notation.{NotationReaderJavaProps, NotationWriterJack}

class PartialCreatorJavaProps(pretty: Boolean) extends PartialCreatorJack {
  def format = Formats.JAVA_PROPS

  def r = new NotationReaderJavaProps

  def w = new NotationWriterJack(pretty, new JavaPropsMapper, "#", "")
}
