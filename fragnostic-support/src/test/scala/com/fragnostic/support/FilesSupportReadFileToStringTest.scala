package com.fragnostic.support

import java.io.{ BufferedReader, File }

class FilesSupportReadFileToStringTest extends AgnosticTest with FilesSupport {

  val path: String = s"$basePath/read-file-to-string.txt"
  val text: String = """w
                        |Yep
                        |aa
                        |
                        |Z
                        |""".stripMargin

  describe("Files Support Test") {

    it("Files Support Read File To String from Path Test") {
      readFileToString(path, charsetName) should be(Right(text))
    }

    it("Files Support Read File To String from BufferedReader Test") {
      val file: File = new File(path)
      val opt: Option[BufferedReader] = getBufferedReader(file, charsetName) fold (
        error => throw new IllegalStateException(error),
        br => Option(br))
      opt should not be None
      opt map (br => readFileToString(br, "") should be(text))
    }

  }

}
