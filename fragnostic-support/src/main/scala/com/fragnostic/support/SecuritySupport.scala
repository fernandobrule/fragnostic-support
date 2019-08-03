package com.fragnostic.support

import org.slf4j.LoggerFactory

trait SecuritySupport {

  private def logger = LoggerFactory.getLogger(getClass.getName)

  def encrypt(text: String): String = {
    logger.warn(s"encrypt() - NOT YET!")
    text
  }

}
