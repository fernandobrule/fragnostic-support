package com.fragnostic.support

import java.io.{ BufferedReader, File }

class FilesSupportGetBufferedReaderTest extends AgnosticTest with FilesSupport {

  describe("Files Support Get Buffered Reader Test") {

    it("Can Read File To List") {

      val file: File = new File("/Users/fernandobrule/Clones/fragnostic/fragnostic-code/fragnostic-support/fragnostic-support/src/test/resources/wl.txt")
      val charsetName: String = "UTF-8"

      val opt: Option[BufferedReader] = getBufferedReader(file, charsetName) fold (error => throw new IllegalStateException(error),
        br => Option(br))

      opt should not be None

      val list: List[String] = opt map (
        br => readFileToList(br, List[String]())) getOrElse { throw new IllegalStateException("ooops") }

      list.size should be(11)

    }

  }

}
