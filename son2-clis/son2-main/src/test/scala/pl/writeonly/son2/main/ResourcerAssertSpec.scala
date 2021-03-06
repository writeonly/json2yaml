package pl.writeonly.son2.main

import pl.writeonly.scalaops.specs.WhiteAssertSpec

class ResourcerAssertSpec extends WhiteAssertSpec {
  val resourcer: Resourcer = new Resourcer(null)

  "A Resourcer" when {
    "valid empty string" should {
      "return README" in {
        assert(resourcer.valid("") == Resources.README)
      }
    }
    "validOpt empty string" should {
      "return README" in {
        assert(resourcer.validOpt("") == Resources.README)
      }
    }
    "validOpt empty option" should {
      "return README" in {
        assert(resourcer.validOpt(null) == Resources.README)
      }
    }
  }

}
