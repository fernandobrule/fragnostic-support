package com.fragnostic.support

class FilesSupportReadFileToStringTest extends AgnosticTest with FilesSupport {

  describe("Files Support Test") {

    it("Files Support Read File To String Test") {

      readFileToString(s"$basePath/read-file-to-string.txt", charsetName) should be(Right("""w
                                                                                            |Yep
                                                                                            |aa
                                                                                            |
                                                                                            |""".stripMargin))
    }

  }

}
