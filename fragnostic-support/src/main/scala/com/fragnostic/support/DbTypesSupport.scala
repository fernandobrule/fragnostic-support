package com.fragnostic.support

import java.sql.Timestamp
import java.text.SimpleDateFormat
import org.slf4j.LoggerFactory

trait DbTypesSupport {

  private def logger = LoggerFactory.getLogger(getClass.getName)

  private val ptrnUsrUtc = "dd-MM-yyyy HH:mm:ss"
  private val ptrnTs = "dd-MM-yyyy HH:mm:ss"

  def str2tst(timestampString: String): Timestamp =
    new Timestamp(new SimpleDateFormat(ptrnUsrUtc).parse(timestampString).getTime)

  def tst2str(ts: Timestamp): String =
    new SimpleDateFormat(ptrnTs).format(ts)

}
