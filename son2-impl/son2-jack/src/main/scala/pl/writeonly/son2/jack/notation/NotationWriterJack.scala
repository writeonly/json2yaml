package pl.writeonly.son2.jack.notation

import pl.writeonly.son2.apis.config.WConfig
import pl.writeonly.son2.apis.notation.{NotationWriter, Value}
import pl.writeonly.son2.jack.core.Jack

class NotationWriterJack(jack: Jack, c: WConfig)
    extends NotationWriter(jack.meta, c) {

  override def writePretty(value: Value): String =
    jack.mapper.writerWithDefaultPrettyPrinter().writeValueAsString(value)

  override def writeRaw(value: Value): String =
    jack.mapper.writeValueAsString(value)

  override def comment(s: String) = jack.s1 + s + jack.s2

}
