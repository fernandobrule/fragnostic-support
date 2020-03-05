package com.fragnostic.support

import java.io.{ BufferedReader, File }

class FilesSupportFileToBufferedReaderTest extends AgnosticTest {

  describe("Files Support File To Buffered Reader Test") {

    it("Can't Get Buffered Reader from File") {
      val path: String = s"$basePath/asdasd.txt"
      val file: File = new File(path)
      val ans = fileToBufferedReader(file, charsetName) fold (
        error => error,
        br => throw new IllegalStateException("this can't be happen"))

      ans should be("files.support.file.to.buffered.reader.error")
    }

    it("Can Get Buffered Reader from File") {
      val path: String = s"$basePath/wl.txt"
      val file: File = new File(path)
      val opt: Option[BufferedReader] = fileToBufferedReader(file, charsetName) fold (error => throw new IllegalStateException(error),
        br => Option(br))

      opt should not be None
    }

  }

}
