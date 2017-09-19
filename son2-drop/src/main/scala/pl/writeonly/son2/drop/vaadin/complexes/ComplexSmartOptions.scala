package pl.writeonly.son2.drop.vaadin.complexes

import com.typesafe.scalalogging.LazyLogging
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.{Button, CheckBoxGroup, Component}
import pl.writeonly.son2.drop.vaadin.util.{ItemSymbol, UITrait}

class ComplexSmartOptions extends Complex with LazyLogging{
  private val options = ComplexSmartOptions.apply
  private val MODE_PERMISSIVE = button("Permissive", ComplexSmartOptions.MODE_PERMISSIVE)
  private val MODE_JSON_SIMPLE = button("Json simple", ComplexSmartOptions.MODE_JSON_SIMPLE)
  private val MODE_RFC4627 = button("RFC4627", ComplexSmartOptions.MODE_RFC4627)
  private val MODE_STRICTEST = button("Stric Test", ComplexSmartOptions.MODE_STRICTEST)
  private val modes = List(MODE_PERMISSIVE, MODE_JSON_SIMPLE, MODE_RFC4627, MODE_STRICTEST)
  private val vertical = ComplexSmartOptions.verticalPanel("Smart modes:", modes: _*)
  private val horizontal = ComplexSmartOptions.horizontalLayout(vertical, options)

  private def button(caption:String, select: Set[ItemSymbol]) = new Button(caption, new Button.ClickListener(){
    override def buttonClick(event: ClickEvent): Unit = {
      logger.info("{}", event)
      options.clear()
      options.select(select.toSeq:_*)
    }
  })

  override def toComponent: Component = horizontal

  def selectedItem = ComplexSmartOptions.selectedItem(options)
}

object ComplexSmartOptions extends UITrait {
  private val ACCEPT_USELESS_COMMA =  ItemSymbol('ACCEPT_USELESS_COMMA)
  private val USE_HI_PRECISION_FLOAT = ItemSymbol('USE_HI_PRECISION_FLOAT)
  private val ACCEPT_TAILLING_DATA = ItemSymbol('ACCEPT_TAILLING_DATA)
  private val ACCEPT_TAILLING_SPACE = ItemSymbol('ACCEPT_TAILLING_SPACE)
  private val REJECT_127_CHAR = ItemSymbol('REJECT_127_CHAR)
  private val USE_INTEGER_STORAGE = ItemSymbol('USE_INTEGER_STORAGE)

  private val items = Set(
    ItemSymbol('ACCEPT_SIMPLE_QUOTE),
    ItemSymbol('ACCEPT_NON_QUOTE),
    ItemSymbol('ACCEPT_NAN),
    ItemSymbol('IGNORE_CONTROL_CHAR),
    ItemSymbol('ACCEPT_LEADING_ZERO),
    USE_INTEGER_STORAGE,
    ACCEPT_USELESS_COMMA,
    USE_HI_PRECISION_FLOAT,
    ACCEPT_TAILLING_DATA,
    ACCEPT_TAILLING_SPACE,
    REJECT_127_CHAR
  )

  val MODE_PERMISSIVE: Set[ItemSymbol] = items
  val MODE_JSON_SIMPLE: Set[ItemSymbol] = Set(
    ACCEPT_USELESS_COMMA,
    USE_HI_PRECISION_FLOAT,
    ACCEPT_TAILLING_DATA,
    ACCEPT_TAILLING_SPACE,
    REJECT_127_CHAR)
  val MODE_RFC4627: Set[ItemSymbol] = Set(
    USE_INTEGER_STORAGE,
    USE_HI_PRECISION_FLOAT,
    ACCEPT_TAILLING_SPACE)
  val MODE_STRICTEST: Set[ItemSymbol] = Set(
    USE_INTEGER_STORAGE,
    USE_HI_PRECISION_FLOAT,
    REJECT_127_CHAR)


  private def apply = checkBoxGroup2("Smart options:", items)

  private def selectedItem(component: CheckBoxGroup[ItemSymbol]): Set[Symbol] = selectedItem2(component).map(_.value)



}