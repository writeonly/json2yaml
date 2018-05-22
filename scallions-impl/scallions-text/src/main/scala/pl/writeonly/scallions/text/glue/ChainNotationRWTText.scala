package pl.writeonly.scallions.text.glue

import pl.writeonly.scallions.apis.chain.{
  ChainNotationRWT,
  PCreatorReaderFake,
  PCreatorWriterFake
}
import pl.writeonly.scallions.text.creators.PCreatorTranslatorText

class ChainNotationRWTText
    extends ChainNotationRWT(
      new PCreatorReaderFake(),
      new PCreatorWriterFake(),
      new PCreatorTranslatorText()
    )