package com.fragnostic.support

import org.scalatest.{ FunSpec, Matchers }

class FilesSupportTest extends FunSpec with Matchers with FilesSupport {

  private val charsetName = "UTF-8"
  private val basePath = "/Users/fernandobrule/Clones/fragnostic/fragnostic-code/fragnostic-support/fragnostic-support/src/test/resources"

  describe("Files Support Test") {

    it("Can Read File To String") {
      readFileToString(s"$basePath/read-file-to-string.txt", charsetName) should be(Right("""w
                                                                                            |Yep
                                                                                            |aa
                                                                                            |
                                                                                            |"""
        .stripMargin))
    }

    it("Can Read File To List") {

      val list = readFileToList(s"$basePath/wl.txt", charsetName) fold (
        error => throw new IllegalStateException(error),
        list => list)

      assertResult(false)(list.isEmpty)
      assertResult(11)(list.size)
      assertResult("um")(list.head)

    }

  }

}

