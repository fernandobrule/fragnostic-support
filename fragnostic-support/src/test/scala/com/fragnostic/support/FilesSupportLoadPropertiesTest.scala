package com.fragnostic.support

import java.util.Properties

class FilesSupportLoadPropertiesTest extends AgnosticTest {

  describe("Files Support Load Properties Test") {

    it("Can Load Properties") {
      val filePath: String = "./fragnostic-support/src/test/resources/some.properties"
      val props: Properties = loadProperties(filePath) fold (
        error => throw new IllegalStateException(error),
        props => props)
      props.getProperty("one") should be("uno")
    }

  }

}
