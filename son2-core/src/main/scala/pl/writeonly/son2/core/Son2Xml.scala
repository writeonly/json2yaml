package pl.writeonly.son2.core

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.xml.XmlMapper

class Son2Xml extends Son2 {
  override def convert(jsonString: String): String = {
    val jsonNodeTree = new ObjectMapper().readTree(jsonString)
    new XmlMapper().writeValueAsString(jsonNodeTree)
  }

  override def comment(jsonString: String) = "<!-- " + jsonString + " -->\n"
}
