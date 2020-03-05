package com.fragnostic.support

import java.io.{ BufferedReader, File }

class FilesSupportFileToListTest extends AgnosticTest {

  describe("Files Support Read File To List Test") {

    it("Can Read File To List from Path") {

      val list = fileToList(s"$basePath/wl.txt", charsetName) fold (
        error => throw new IllegalStateException(error),
        list => list)

      assertResult(false)(list.isEmpty)
      assertResult(11)(list.size)
      assertResult("um")(list.head)

    }

    it("Can Read File To List from BufferedReader") {

      val path: String = s"$basePath/wl.txt"
      val file: File = new File(path)
      val charsetName: String = "UTF-8"

      val opt: Option[BufferedReader] = fileToBufferedReader(file, charsetName) fold (error => throw new IllegalStateException(error),
        br => Option(br))

      opt should not be None

      val list: List[String] = opt map (
        br => bufferedReaderToList(br, List[String]())) getOrElse { throw new IllegalStateException("ooops") }

      list.size should be(11)

    }

  }

}
