package pl.writeonly.json2yaml.core

import org.scalatest.FunSpec

class FileJson2YamlFun extends FunSpec {
  describe("A FileJson2YamlSpec") {
    var onml:FileJson2Yaml = new FileJson2YamlImpl(new Json2YamlJackson())

    describe("when left") {
      it("should produce null when consume null") {
        val yaml = onml.convertString(null)
        assert(null == yaml)
      }
//      it("should produce empty comment when consume empty string") {
//        val yaml = onml.applyTry("")
//        assert("#" == yaml)
//      }
//      it("should produce a when consume a") {
//        val yaml = onml.applyTry("a")
//        assert("#a" == yaml)
//      }
    }
  }
}