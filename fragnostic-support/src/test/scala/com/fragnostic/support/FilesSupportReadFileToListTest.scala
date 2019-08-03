package com.fragnostic.support

class FilesSupportReadFileToListTest extends AgnosticTest with FilesSupport {

  describe("Files Support Read File To List Test") {

    it("Can Read File To List") {

      val list = readFileToList(s"$basePath/wl.txt", charsetName) fold (error => throw new IllegalStateException(error),
        list => list)

      assertResult(false)(list.isEmpty)
      assertResult(11)(list.size)
      assertResult("um")(list.head)

    }

  }

}
