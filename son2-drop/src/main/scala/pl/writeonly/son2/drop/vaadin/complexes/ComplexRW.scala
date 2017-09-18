package pl.writeonly.son2.drop.vaadin.complexes

import com.vaadin.ui.Component
import pl.writeonly.son2.drop.vaadin.util.{Mappings, UIUtil}

import scala.collection.JavaConverters._

object ComplexRW extends UIUtil {
  val natives = List("Print", "String")

  def readGroupApply = radioButtonGroup("Read:", Mappings.readMapping)

  def writeGroupApply = radioButtonGroup("Write:", Mappings.writeMapping)

  def nativeGroupApply = checkBoxGroup("Native:", ComplexRW.natives)
}

abstract class ComplexRW extends Complex {
  private val readGroup = ComplexRW.readGroupApply
  private val writeGroup = ComplexRW.writeGroupApply
  private val nativeGroup = ComplexRW.nativeGroupApply
  protected val components: List[Component] = List(readGroup, writeGroup, nativeGroup)

  def readStream = readSelectedItem.equals('stream)

  def readSelectedItem = ComplexRW.selectedItem(readGroup, Mappings.readMapping)

  def writePretty = writeSelectedItem.equals('pretty)

  def writeSelectedItem = ComplexRW.selectedItem(writeGroup, Mappings.writeMapping)

  def set = nativeGroup.getValue.asScala.toSet
}

class ComplexRWHorizontal extends ComplexRW {
  private val component: Component = ComplexJackFormats.horizontalLayout(components: _*)

  override def toComponent: Component = component
}

class ComplexRWVertical extends ComplexRW {
  private val component: Component = ComplexJackFormats.verticalLayout(components: _*)

  override def toComponent: Component = component
}

