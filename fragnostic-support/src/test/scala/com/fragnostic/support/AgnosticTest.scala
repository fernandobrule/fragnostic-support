package com.fragnostic.support

import org.scalatest.{ FunSpec, Matchers }

trait AgnosticTest extends FunSpec with Matchers with FilesSupport {

  protected val charsetName = "UTF-8"
  protected val basePath = "./fragnostic-support/src/test/resources"

}
