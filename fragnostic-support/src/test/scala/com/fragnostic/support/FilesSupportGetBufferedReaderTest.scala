package com.fragnostic.support

import java.io.{ BufferedReader, File }

class FilesSupportGetBufferedReaderTest extends AgnosticTest with FilesSupport {

  describe("Files Support Get Buffered Reader Test") {

    it("Can Get Buffered Reader from File") {

      val path: String = s"$basePath/wl.txt"
      val file: File = new File(path)
      val charsetName: String = "UTF-8"

      val opt: Option[BufferedReader] = getBufferedReader(file, charsetName) fold (error => throw new IllegalStateException(error),
        br => Option(br))

      opt should not be None

    }

  }

}
