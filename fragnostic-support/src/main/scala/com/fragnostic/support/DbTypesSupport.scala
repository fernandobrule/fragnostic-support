package com.fragnostic.support

import java.sql.Timestamp
import java.text.SimpleDateFormat

import org.slf4j.LoggerFactory

import scala.util.Try

trait DbTypesSupport {

  private def logger = LoggerFactory.getLogger(getClass.getName)

  val ptrnStrTst = "dd-MM-yyyy HH:mm:ss"
  val ptrnSqlTst = "dd-MM-yyyy HH:mm:ss"

  def str2sqlTst(timestampString: String): Either[String, Timestamp] =
    Try {
      Right(new Timestamp(new SimpleDateFormat(ptrnStrTst).parse(timestampString).getTime))
    } getOrElse {
      logger.error(s"str2sqlTst() - it is not possible parse: $timestampString")
      Left("str.2.sql.tst.error")
    }

  def sqlTst2str(sqlTst: Timestamp): Either[String, String] =
    Try {
      Right(new SimpleDateFormat(ptrnSqlTst).format(sqlTst))
    } getOrElse {
      logger.error(s"sqlTst2str() - it is not possible format: $sqlTst")
      Left("sql.tst.2.str.error")
    }

}
