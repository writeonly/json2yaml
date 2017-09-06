package pl.writeonly.son2.drop.vaadin.ui

import com.vaadin.annotations.{Theme, Title}
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui._
import pl.writeonly.son2.drop.vaadin.util.{ComponentsIO, Mappings, TopMenu, UITrait}
import pl.writeonly.son2.path.core.ConfigPath
import pl.writeonly.son2.path.glue.CreatorConverterPath

import scala.collection.JavaConverters._

@Title("json patch")
@Theme("valo")
class UIPatch extends UITrait {
  override def components: List[Component] = {
    val checkBoxes = nativeGroup
    val configLabel = outputLabel
    val io = new ComponentsIO

    val providerGroup = radioButtonGroup("Providers", Mappings.pathProvidersMapping, "Smart");
    val outputFormats = jacksonOutputFormat("JSON")
    val components: List[Component] = List(providerGroup, outputFormats, checkBoxes, configLabel)

    val inputPatch = inputTextArea("json-patch")

    val convert = convertButton(new Button.ClickListener() {
      override def buttonClick(clickEvent: ClickEvent): Unit = {
        val path = inputPatch.getValue
        val config = ConfigPath(i = Symbol(path))

        val set = checkBoxes.getValue.asScala.toSet
        debug(configLabel, config, set)
        convert2(CreatorConverterPath(config), io.input, io.output, set)
      }
    })

    return List(new TopMenu().linkPanel, optionsPanel(components), inputPatch, io.input, convert, io.output)
  }
}