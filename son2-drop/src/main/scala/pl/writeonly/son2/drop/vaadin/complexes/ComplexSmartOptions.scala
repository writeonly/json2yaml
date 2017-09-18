package pl.writeonly.son2.drop.vaadin.complexes

import com.typesafe.scalalogging.LazyLogging
import com.vaadin.ui.Button.ClickEvent
import com.vaadin.ui.{Button, CheckBoxGroup, Component}
import pl.writeonly.son2.drop.vaadin.util.UITrait

class ComplexSmartOptions extends Complex with LazyLogging{
  private val component = ComplexSmartOptions.apply
  private val MODE_JSON_SIMPLE = button("MODE_JSON_SIMPLE", ComplexSmartOptions.MODE_JSON_SIMPLE)
  private val MODE_RFC4627 = button("MODE_RFC4627", ComplexSmartOptions.MODE_RFC4627)
  private val MODE_STRICTEST = button("MODE_STRICTEST", ComplexSmartOptions.MODE_STRICTEST)
  private val layout = ComplexSmartOptions.verticalLayout(components:_*)

  private def button(caption:String, select: Set[Symbol]) = new Button(caption, new Button.ClickListener(){
    override def buttonClick(event: ClickEvent): Unit = {
      logger.info("{}", event)
      component.clear()
      val set : Set[String] = select.map(ComplexSmartOptions.swap.get(_).get)
      component.select(set.toSeq:_*)
    }
  })
  
  override def components: List[Component] = List(component, MODE_JSON_SIMPLE, MODE_RFC4627, MODE_STRICTEST)

  override def toComponent: Component = layout

  def selectedItem = ComplexSmartOptions.selectedItem(component)
}

object ComplexSmartOptions extends UITrait {
  val MODE_JSON_SIMPLE: Set[Symbol] = Set(
    'ACCEPT_USELESS_COMMA,
    'USE_HI_PRECISION_FLOAT,
    'ACCEPT_TAILLING_DATA,
    'ACCEPT_TAILLING_SPACE,
    'REJECT_127_CHAR)
  val MODE_RFC4627: Set[Symbol] = Set(
    'USE_INTEGER_STORAGE,
    'USE_HI_PRECISION_FLOAT,
    'ACCEPT_TAILLING_SPACE)
  val MODE_STRICTEST: Set[Symbol] = Set(
    'USE_INTEGER_STORAGE,
    'USE_HI_PRECISION_FLOAT,
    'REJECT_127_CHAR)
  val MODE_PERMISSIVE = 'MODE_PERMISSIVE
  private val mapping = Map[String, Symbol](
    "ACCEPT_SIMPLE_QUOTE" -> 'ACCEPT_SIMPLE_QUOTE,
    "ACCEPT_NON_QUOTE" -> 'ACCEPT_NON_QUOTE,
    "ACCEPT_NAN" -> 'ACCEPT_NAN,
    "IGNORE_CONTROL_CHAR" -> 'IGNORE_CONTROL_CHAR,
    "USE_INTEGER_STORAGE" -> 'USE_INTEGER_STORAGE,
    "ACCEPT_LEADING_ZERO" -> 'ACCEPT_LEADING_ZERO,
    "ACCEPT_USELESS_COMMA" -> 'ACCEPT_USELESS_COMMA,
    "USE_HI_PRECISION_FLOAT" -> 'USE_HI_PRECISION_FLOAT,
    "ACCEPT_TAILLING_DATA" -> 'ACCEPT_TAILLING_DATA,
    "ACCEPT_TAILLING_SPACE" -> 'ACCEPT_TAILLING_SPACE,
    "ACCEPT_USELESS_COMMA" -> 'ACCEPT_USELESS_COMMA,
    "REJECT_127_CHAR" -> 'REJECT_127_CHAR
  )
  private val swap: Map[Symbol, String] = mapping.map(_.swap)

  private def apply = checkBoxGroup("Smart options:", ComplexSmartOptions.mapping)

  private def selectedItem(component: CheckBoxGroup[String]): Set[Symbol] = selectedItem(component, ComplexSmartOptions.mapping)



}
