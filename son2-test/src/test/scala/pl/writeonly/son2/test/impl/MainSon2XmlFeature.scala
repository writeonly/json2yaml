package pl.writeonly.son2.test.impl

import java.io.FileNotFoundException

import org.scalatest.{FeatureSpec, GivenWhenThen}
import pl.writeonly.son2.core.Formats
import pl.writeonly.son2.impl.main.Main

class MainSon2XmlFeature extends FeatureSpec with GivenWhenThen {

  val outName = (name: String) =>  Features.outputPathname(Types.MAIN, name, Formats.XML)

  feature(classOf[MainSon2XmlFeature].getSimpleName) {
    scenario("Apply with null pathname") {
      Given("converter FileJson2Xml")
      val name: String = null

      When("should produce null when consume null")
      val caught = intercept[NullPointerException] {
        Main.main(Array(Formats.XML, name, name))
      }

      Then("null == messag")
      val message = caught.getMessage
      assert(null == message)
    }

    scenario("Apply with empty pathname") {
      Given("converter FileJson2Xml")

      When("should produce empty when consume empty")
      assertThrows[FileNotFoundException] {
        Main.main(Array(Formats.XML, "", ""))
      }
    }

    scenario("Apply with pathname") {
      Given("converter FileJson2Xml")
      val in = Features.inputPathname
      val out = outName("pathname")

      When("should produce null when consume null")
      Main.main(Array(Formats.XML, in, out))
    }
  }
}
