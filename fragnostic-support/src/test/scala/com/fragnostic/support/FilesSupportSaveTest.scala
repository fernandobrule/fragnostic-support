package com.fragnostic.support

class FilesSupportSaveTest extends AgnosticTest {

  describe("Files Support Save Test") {

    it("Can Save Bytes") {

      val bytes: Array[Byte] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789\n".getBytes()

      val ruta: String = "/Users/fernandobrule/Tmp/texto.txt"

      val message: String = save(bytes, ruta) fold (
        error => error,
        success => success)

      message should be("files.support.save.success")

    }

  }

}
