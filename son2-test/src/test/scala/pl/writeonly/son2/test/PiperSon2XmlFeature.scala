package pl.writeonly.son2.test

import java.io.FileNotFoundException

import org.scalatest.{FeatureSpec, GivenWhenThen}
import pl.writeonly.son2.core.providers.ProviderXml
import pl.writeonly.son2.main._

class PiperSon2XmlFeature extends FeatureSpec with GivenWhenThen {

  info("MainJson2XmlImpl with Json2XmlJackson")

  val given = () => new Piper(new ProviderXml)

  val outName = (name: String) =>  Features.outputPathname("main", name, "xml")

  feature("MainJson2Xml with two pathname") {
    scenario("Apply with null pathname") {
      Given("converter FileJson2Xml")
      val main = given()

      When("should produce null when consume null")
      val name: String = null
      val caught = intercept[NullPointerException] {
        main.convertFile(name, name)
      }

      Then("null == messag")
      val message = caught.getMessage
      assert(null == message)
    }

    scenario("Apply with empty pathname") {
      Given("converter FileJson2Xml")
      val main = given()

      When("should produce empty when consume empty")
      assertThrows[FileNotFoundException] {
        main.convertFile("", "")
      }
    }

    scenario("Apply with pathname") {
      Given("converter FileJson2Xml")
      val main = given()
      val in = Features.inputPathname
      val out = outName("pathname")

      When("should produce null when consume null")
      main.convertFile(in, out)
    }
  }

  feature("MainJson2Xml with one pathname") {
    scenario("Apply with null pathname") {
      Given("converter FileJson2Xml")
      val main = given()
      val name: String = null

      When("should produce null when consume null")
      val caught = intercept[NullPointerException] {
        main.convertFile(name)
      }

      Then("null == messag")
      val message = caught.getMessage
      assert(null == message)
    }

    scenario("Apply with empty pathname") {
      Given("converter FileJson2Xml")
      val main = given()

      When("should produce null empty consume empty")
      assertThrows[FileNotFoundException] {
        main.convertFile("")
      }
    }

    scenario("Apply with pathname") {
      Given("converter FileJson2Xml")
      val main = given()
      val in = Features.inputPathname

      When("should produce null when consume null")
      main.convertFile(in)
    }
  }
}
