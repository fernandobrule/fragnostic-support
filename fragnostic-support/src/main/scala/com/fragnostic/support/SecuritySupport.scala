package com.fragnostic.support

import org.slf4j.{ Logger, LoggerFactory }

trait SecuritySupport {

  private[this] val logger: Logger = LoggerFactory.getLogger(getClass.getName)

  def encrypt(text: String): String = {
    logger.warn(s"encrypt() - NOT YET!")
    text
  }

}
