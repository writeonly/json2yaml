package pl.writeonly.son2.jack.chain

import com.fasterxml.jackson.databind.JsonNode
import pl.writeonly.son2.core.chain.ChainImpl
import pl.writeonly.son2.core.config._
import pl.writeonly.son2.jack.core.ConfigJack
import pl.writeonly.son2.jack.notation._

class ChainReaderJack
    extends ChainImpl[Any](
      new NotationReaderObject
        orElse
          new NotationReaderXml
        orElse
          new NotationReaderYaml
        orElse
          new NotationReaderCsv
        orElse
          new NotationReaderJavaProps)
    with HasConfigOpt {

  def configOpt(s: String): Option[Config] =
    get.lift(s).map(a => ChainReaderJack.config(a.asInstanceOf[JsonNode]))

  def parse(s: String): JsonNode = get.lift(s).get.asInstanceOf[JsonNode]

}

object ChainReaderJack {
  def config(n: JsonNode): Config = config(n, ConfigJack.apply())

  def config(n: JsonNode, c: Config): Config = new Config(
    read = rConfig(n, c.read),
    write = wConfig(n, c.write)
  )

  def rConfig(n: JsonNode, c: RConfig) = new RConfig(
    provider = asText(n, ConfigPath.I).getOrElse(c.provider),
    stream = asBoolean(n, ConfigPath.S).getOrElse(c.stream)
  )

  def wConfig(n: JsonNode, c: WConfig) = new WConfig(
    provider = asText(n, ConfigPath.O).getOrElse(c.provider),
    style = asBoolean(n, ConfigPath.P).getOrElse(c.style)
  )

  private def asText(n: JsonNode, s: Symbol) =
    get(n, s).map(_.asText).map(Symbol.apply)

  private def asBoolean(n: JsonNode, s: Symbol) = get(n, s).map(_.asBoolean)

  private def get(n: JsonNode, s: Symbol) = Option(n.get(s.name))
}
