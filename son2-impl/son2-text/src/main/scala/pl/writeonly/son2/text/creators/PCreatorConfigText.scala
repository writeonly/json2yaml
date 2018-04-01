package pl.writeonly.son2.text.creators

import pl.writeonly.son2.apis.config.{Format, RWTConfig, TConfig}
import pl.writeonly.son2.apis.pcreators.PCreatorConfig
import pl.writeonly.son2.text.core.{Actions, FormatsText}
import pl.writeonly.sons.utils.ops.Pipe._

class PCreatorConfigText extends PCreatorConfig {

  private val matcher = new MatcherStringEscape()

  override def isDefinedAt(s: String): Boolean =
    s &&
      symbolOptionPairOption(s)
        .map(p => p._1 && p._2)
        .getOrElse(false)

  private def symbolOptionPairOption(
    s: String
  ): Option[(Option[Symbol], Option[Format])] =
    "^(\\w+)_(\\w+)$".r
      .findFirstMatchIn(s)
      .map(
        p =>
          Pair(
            find1(p.group(1), Actions.ALL),
            find2(p.group(2), FormatsText.ALL_TEXT)
        )
      )

  private def find1(s: String, l: List[Symbol]) =
    l.find(it => it.name.toLowerCase.startsWith(s))

  private def find2(s: String, l: List[Format]) =
    l.find(it => it.s.name.toLowerCase.startsWith(s))

  override def apply(s: String) = RWTConfig(translate = translateConfig(s))

  def translateConfig(s: String): TConfig =
    symbolOptionPairOption(s)
      .map(p => TConfig(p._1.get, p._2.get))
      .get

}
