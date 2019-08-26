package com.fragnostic.support

import java.sql.{ Date, Timestamp }
import java.text.SimpleDateFormat

trait DbTypesSupport {

  val ptrnStrTst = "dd-MM-yyyy HH:mm:ss"
  val ptrnSqlTst = "dd-MM-yyyy HH:mm:ss"

  def str2sqlTst(timestampString: String): Either[String, Timestamp] =
    try {
      Right(new Timestamp(new SimpleDateFormat(ptrnStrTst).parse(timestampString).getTime))
    } catch {
      case e: Exception => Left(e.getMessage)
    }

  def str2sqlDate(timestampString: String): Either[String, Date] =
    try {
      Right(new Date(new SimpleDateFormat(ptrnStrTst).parse(timestampString).getTime))
    } catch {
      case e: Exception => Left(e.getMessage)
    }

  def sqlTst2str(sqlTst: Timestamp): Either[String, String] =
    try {
      Right(new SimpleDateFormat(ptrnSqlTst).format(sqlTst))
    } catch {
      case e: Exception => Left(e.getMessage)
    }

  def sqlDate2str(sqlDate: Date): Either[String, String] =
    try {
      Right(new SimpleDateFormat(ptrnSqlTst).format(sqlDate))
    } catch {
      case e: Exception => Left(e.getMessage)
    }

}
