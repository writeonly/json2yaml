package pl.writeonly.son2.jack.formats.creators

import pl.writeonly.son2.jack.core.Config
import pl.writeonly.son2.jack.providers._

class CreatorFormatProvider(config: Config) extends CreatorFormat[Provider] {

  override def on: Provider = new ProviderObject(config)

  override def yaml: Provider = new ProviderYaml(config)

  override def xml: Provider = new ProviderXml(config)

  override def csv: Provider = new ProviderCsv(config)

  override def javaprops: Provider = new ProviderJavaProps(config)

}
