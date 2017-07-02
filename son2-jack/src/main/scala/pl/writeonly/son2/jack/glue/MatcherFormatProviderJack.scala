package pl.writeonly.son2.jack.glue

import pl.writeonly.son2.core.glue.{Config, MatcherFormatProvider}
import pl.writeonly.son2.core.providers.Provider
import pl.writeonly.son2.jack.core.ConfigJack
import pl.writeonly.son2.jack.formats.creators.{CreatorFormat, CreatorFormatReader, CreatorFormatWriter}
import pl.writeonly.son2.jack.formats.matchers.{MatcherFormatJack, MatcherFormatTuple}
import pl.writeonly.son2.jack.formats.predicates.PredicateFormatStartsWith
import pl.writeonly.son2.jack.notation.{NotationReaderJack, NotationWriterJack}

class MatcherFormatProviderJack(c: Config) extends MatcherFormatProvider(c) {
  def t = new MatcherFormatTuple(new PredicateFormatStartsWith, (
    new CreatorFormatReader().asInstanceOf[CreatorFormat[Object]],
    new CreatorFormatWriter(c).asInstanceOf[CreatorFormat[Object]]))
  def r = new MatcherFormatJack(new PredicateFormatStartsWith, new CreatorFormatReader)
  def w = new MatcherFormatJack(new PredicateFormatStartsWith, new CreatorFormatWriter(c))

}

object MatcherFormatProviderJack {
  def apply(o: Symbol): Provider = apply(ConfigJack(o = o.name))

  def apply(o: String): Provider = apply(ConfigJack(o = o))

  def apply(config: Config): Provider = either(config)
    .right
    .get

  //  def opt(config: Config): Option[Provider] = new FormatProvider(config).apply()

  def either(config: Config): Either[Option[String], Provider] = new MatcherFormatProviderJack(config)
    .apply()



  //either
}
