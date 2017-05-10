package pl.writeonly.son2.jack.formats

import com.fasterxml.jackson.databind.ObjectMapper
import pl.writeonly.son2.jack.core.Config
import pl.writeonly.son2.jack.formats.creators.CreatorFormatMapper
import pl.writeonly.son2.jack.formats.matchers.MatcherFormatStartsWith

class MatcherFormatMapper extends MatcherFormatStartsWith[ObjectMapper](new CreatorFormatMapper) {

}

object MatcherFormatMapper {
  def apply(config: Config): ObjectMapper = apply(config.i)

  def apply(i: String): ObjectMapper = new MatcherFormatMapper()
    .apply(i)
    .right
    .get
}