package pl.writeonly.son2.impl.main

import java.io.{FileInputStream, InputStream}

import pl.writeonly.son2.core.liners.{Liner, LinerOpt}
import pl.writeonly.son2.core.providers.Provider
import pl.writeonly.son2.core.streamers.{StreamerImplForeach, StreamerSource}

class Piper(val liner: Liner) {

  val impl = new StreamerImplForeach(liner)
  val source = new StreamerSource(liner)

  def this(provider: Provider) = this(new LinerOpt(provider))

  def convertStream() = impl.convertStream(System.in, System.out)

  def convertFile(in: String, out: String) = impl.convertFile(in, out)

  def convertFile(in: String) = convertStream(new FileInputStream(in))

  def convertStream(in: InputStream) = source.convertStream(in, System.out)

  def convertResource(name: String) = convertStream(resourceAsStream(name))

  def resourceAsStream(name: String) = getClass().getClassLoader().getResourceAsStream(name)

}
