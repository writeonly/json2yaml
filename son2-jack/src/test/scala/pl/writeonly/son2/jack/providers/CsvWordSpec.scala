package pl.writeonly.son2.jack.providers

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.databind.JsonMappingException
import pl.writeonly.son2.core.liners.{Liner, LinerOpt}
import pl.writeonly.son2.core.providers.{Provider, Provider2}
import pl.writeonly.son2.jack.chain.ChainNotationPairJack
import pl.writeonly.son2.jack.core.Formats
import pl.writeonly.son2.spec.WhiteResultSpec

class CsvWordSpec extends WhiteResultSpec {

  val provider: Provider = ChainNotationPairJack(Formats.CSV)
  "A Provider" should {
    "produce JsonParseException when convert a" in {
      assertThrows[JsonParseException] {
        provider.convert("a")
      }
    }
    "produce JsonMappingException when convert empty string" in {
      assertThrows[JsonMappingException] {
        provider.convert("")
      }
    }
  }

  val liner: Liner = new LinerOpt(provider)
  "A Liner" should {
    "return empty comment" in {
      assertResult(provider.comment("") + "\n")(liner.apply(""))
    }
  }
}
