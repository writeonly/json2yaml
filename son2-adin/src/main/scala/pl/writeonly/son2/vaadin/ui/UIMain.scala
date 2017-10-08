package pl.writeonly.son2.vaadin.ui

import com.vaadin.annotations.{Theme, Title}
import com.vaadin.ui._
import pl.writeonly.son2.vaadin.util.{TopMenu, UITrait}

@Title("Main UI")
@Theme("valo")
class UIMain extends UITrait {
  override def components: List[Component] = {
    return List[Component](new TopMenu().linkPanel)
  }
}