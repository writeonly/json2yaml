package pl.writeonly.son2.core

import org.scalatest.FunSpec
import pl.writeonly.son2.core.providers.ProviderYaml
import pl.writeonly.son2.core.streamers.{Streamer, StreamerImpl}

class StreamerYamlFun extends FunSpec {
  describe("A FileJson2YamlSpec") {
    var streamer: Streamer = new StreamerImpl(new ProviderYaml())

    describe("convert strings") {
      describe("when left") {
        it("should produce empty comment when consume empty string") {
          val yaml = streamer.convertString("")
          assert("" == yaml)
        }
        it("should produce a when consume a") {
          val yaml = streamer.convertString("a")
          assert("#a\n" == yaml)
        }
      }
    }
    describe("convert native strings") {
      describe("when left") {
        it("should produce empty comment when consume empty string") {
          val yaml = streamer.convertStringNative("")
          assert("" == yaml)
        }
        it("should produce a when consume a") {
          val yaml = streamer.convertStringNative("a")
          assert("#a\n" == yaml)
        }
      }
    }
  }
}