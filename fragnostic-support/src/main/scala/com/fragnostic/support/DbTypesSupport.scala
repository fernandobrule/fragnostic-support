package com.fragnostic.support

import java.sql.{ Date, Timestamp }
import java.text.SimpleDateFormat

import org.slf4j.{ Logger, LoggerFactory }

trait DbTypesSupport {

  private[this] val loggerDbTypesSup: Logger = LoggerFactory.getLogger(getClass.getName)

  val ptrnStrTst = "dd-MM-yyyy HH:mm:ss"
  val ptrnSqlTst = "dd-MM-yyyy HH:mm:ss"

  def str2sqlTst(timestampString: String): Either[String, Timestamp] =
    try {
      Right(new Timestamp(new SimpleDateFormat(ptrnStrTst).parse(timestampString).getTime))
    } catch {
      case e: Exception =>
        loggerDbTypesSup.error(s"str2sqltst() - $e")
        Left("str2sqltst.error")
    }

  def str2sqlDate(timestampString: String): Either[String, Date] =
    try {
      Right(new Date(new SimpleDateFormat(ptrnStrTst).parse(timestampString).getTime))
    } catch {
      case e: Exception =>
        loggerDbTypesSup.error(s"str2sqlDate() - $e")
        Left("str2sqldate.error")
    }

  def sqlTst2str(sqlTst: Timestamp): Either[String, String] =
    try {
      Right(new SimpleDateFormat(ptrnSqlTst).format(sqlTst))
    } catch {
      case e: Exception =>
        loggerDbTypesSup.error(s"sqlTst2str() - $e")
        Left("sqltst2str.error")
    }

  def sqlDate2str(sqlDate: Date): Either[String, String] =
    try {
      Right(new SimpleDateFormat(ptrnSqlTst).format(sqlDate))
    } catch {
      case e: Exception =>
        loggerDbTypesSup.error(s"sqlDate2str() - $e")
        Left("sqldate2str.error")
    }

}
