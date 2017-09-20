package pl.writeonly.son2.drop.vaadin.complexes

import com.vaadin.ui.Component
import pl.writeonly.son2.drop.vaadin.util.{ItemSymbol, UIUtil}

import scala.collection.JavaConverters._

object ComplexRW extends UIUtil {
  val nativesItems = List("Print", "String")

  val readItems = Set[ItemSymbol](
    ItemSymbol('all, "All line"),
    ItemSymbol('stream, "Line-by-line")
  )

  val writeItems = Set[ItemSymbol](
    ItemSymbol('pretty, "Pretty"),
    ItemSymbol('raw, "Raw")
  )

  def readGroupApply = radioButtonGroup("Read:", ComplexRW.readItems)

  def writeGroupApply = radioButtonGroup("Write:", ComplexRW.writeItems)

  def nativeGroupApply = checkBoxGroupNative("Native:", ComplexRW.nativesItems)
}

abstract class ComplexRW extends Complex {
  protected val components: List[Component] = List(readGroup, writeGroup, nativeGroup)
  private val readGroup = ComplexRW.readGroupApply
  private val writeGroup = ComplexRW.writeGroupApply
  private val nativeGroup = ComplexRW.nativeGroupApply

  def readStream = readSelectedItem.equals('stream)

  def readSelectedItem = ComplexRW.selectedItem(readGroup).value

  def writePretty = writeSelectedItem.equals('pretty)

  def writeSelectedItem = ComplexRW.selectedItem(writeGroup).value

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

