package pl.writeonly.son2.core.chain

import pl.writeonly.son2.core.config.Config
import pl.writeonly.son2.core.notation.{NotationPair, NotationReader, NotationWriter}
import pl.writeonly.son2.core.providers.Provider2

class ChainNotationCreator(chain: PartialFunction[String, NotationPair]) extends ConfigLift {

  def configOpt(s: String) : Option[Config] = chain
    .lift(s)
    .map(t => t.c)
    .map(f => f(s))

  def lift(s: String): Option[Provider2] = configOpt(s).map(provider)

  def provider(c: Config) = new Provider2(c, input(c), output(c))

  private def input(c: Config): NotationReader = chain.lift(c.i).map(f => f.r).get

  private def output(c: Config): NotationWriter = chain.lift(c.o).map(f => f.w).get
}
