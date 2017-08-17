package pl.writeonly.son2.path.creators

import pl.writeonly.son2.core.config.{Config, RConfig, WConfig}
import pl.writeonly.son2.core.notation.{NotationReader, NotationWriter, PartialCreatorPair}
import pl.writeonly.son2.path.core.Formats
import pl.writeonly.son2.path.notation.{NotationReaderStrict, NotationWriterStrict}

class PartialCreatorStrict(pretty: Boolean) extends PartialCreatorPair {
  override def format: Symbol = Formats.STRICT

  override def c: (String) => Config = s => new Config(
    read = RConfig(format = Symbol(s)), write = WConfig(format = Symbol(s), style = false)
  )

  override def r(c: Config): NotationReader = new NotationReaderStrict()

  override def w(c: Config): NotationWriter = new NotationWriterStrict(c.write, pretty)


}
