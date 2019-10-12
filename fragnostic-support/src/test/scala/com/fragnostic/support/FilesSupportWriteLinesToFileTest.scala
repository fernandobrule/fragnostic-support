package com.fragnostic.support

class FilesSupportWriteLinesToFileTest extends AgnosticTest with FilesSupport {

  describe("Files Support Write Lines To File Test") {

    it("Can't Write Lines") {
      val ans = writeLinesToFile("" :: "" :: Nil, "C:/fileName") fold (
        error => error,
        success => success)

      ans should be("files.support.write.lines.to.file.error")
    }

    it("Can Write Lines") {

      val fileName: String = "/Users/fernandobrule/Clones/fragnostic/fragnostic-code/fragnostic-support/target/lines.txt"
      val lines: List[String] = "Uno" :: "Dos" :: "Tres" :: "Cuatro" :: "Cinco" :: Nil

      val ans = writeLinesToFile(lines, fileName) fold (
        error => error,
        success => success)
      ans should be("files.support.write.lines.to.file.success")

    }

  }

}
