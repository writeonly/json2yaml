package pl.writeonly.son2.drop.vaadin.composites
import com.vaadin.ui.Component
import pl.writeonly.son2.drop.vaadin.util.{UITrait}
import com.jayway.jsonpath.{Option => jOption}

class ComplexGsonOptions extends Complex {
  private val component = ComplexGsonOptions.apply
  override def components: List[Component] = List(component)
  def selectedItem = ComplexPathOptions.selectedItem(component, ComplexGsonOptions.mapping)
}

object ComplexGsonOptions extends UITrait {
  private val mapping = Map[String, Symbol](
    "disableHtmlEscaping" -> 'disableHtmlEscaping,
    "disableInnerClassSerialization" -> 'disableInnerClassSerialization,
    "enableComplexMapKeySerialization" -> 'enableComplexMapKeySerialization,
    "excludeFieldsWithoutExposeAnnotation" -> 'excludeFieldsWithoutExposeAnnotation,
    "generateNonExecutableJson" -> 'generateNonExecutableJson,
    "serializeNulls" -> 'serializeNulls,
    "serializeSpecialFloatingPointValues" -> 'serializeSpecialFloatingPointValues,
    "setLenient" -> 'setLenient
  )


  private def apply = checkBoxGroup("Gson options:", ComplexGsonOptions.mapping)
}
