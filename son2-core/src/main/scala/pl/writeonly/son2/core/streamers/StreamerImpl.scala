package pl.writeonly.son2.core.streamers

import java.io._
import java.net.URI
import java.util.stream.Stream

import pl.writeonly.son2.core.liners.{Liner, LinerOpt}
import pl.writeonly.son2.core.providers.Provider
import pl.writeonly.son2.core.util.Control
import pl.writeonly.son2.core.util.Control.using


abstract class StreamerImpl(liner: Liner) extends Streamer(liner) {

  def this(provider: Provider) = this(new LinerOpt(provider))

  override def convertFile(in: URI, out: URI): Unit = convertFile(new File(in), new File(out))

  override def convertFile(in: File, out: File): Unit = convertStream(new FileInputStream(in), new FileOutputStream(out))

  override def convertStream(in: InputStream, out: OutputStream): Unit = {
    convertNative(new InputStreamReader(in, Control.UTF_8), new OutputStreamWriter(out, Control.UTF_8))
  }

  override def convertStringNative(in: String): String = {
    val out = new StringWriter()
    convertNative(new StringReader(in), out)
    out.toString
  }

  def convertNative(in: Reader, out: Writer): Unit = {
    using(new BufferedWriter(out)) { bw =>
      using(new BufferedReader(in)) { br =>
        convertBuffered(br, bw)
      }
    }
  }

  def convertBuffered(in: BufferedReader, out: BufferedWriter): Unit = {
    stream2(in.lines(), out)
  }

  override def convertBytes(in: Array[Byte]): Array[Byte] = {
    val out = new ByteArrayOutputStream()
    convertStream(new ByteArrayInputStream(in), out)
    out.toByteArray
  }

  def stream2(stream: Stream[String], out: Writer): Unit

}
