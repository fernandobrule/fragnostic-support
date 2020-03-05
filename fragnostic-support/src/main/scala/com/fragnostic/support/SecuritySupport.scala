package com.fragnostic.support

import org.slf4j.{ Logger, LoggerFactory }

trait SecuritySupport {

  private[this] val loggerSecSup: Logger = LoggerFactory.getLogger(getClass.getName)

  def encrypt(text: String): String = {
    loggerSecSup.warn(s"encrypt() - NOT YET!")
    text
  }

}
