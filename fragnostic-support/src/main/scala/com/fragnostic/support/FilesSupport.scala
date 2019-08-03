package com.fragnostic.support

import java.io._
import java.util.Properties

import org.slf4j.LoggerFactory

import scala.util.Try

trait FilesSupport {

  private def logger = LoggerFactory.getLogger(getClass.getName)

  private def getBufferedReader(file: File, charsetName: String): Either[String, BufferedReader] =
    try {
      Right(
        new BufferedReader(
          new InputStreamReader(
            new FileInputStream(
              file),
            charsetName)))
    } catch {
      case e: Exception =>
        logger.error(s"getBufferedReader() - $e")
        Left("get.buffered.reader.error")
      case e: Throwable =>
        logger.error(s"getBufferedReader() - $e")
        Left("get.buffered.reader.error")
    }

  private def readFileToList(br: BufferedReader, list: List[String]): List[String] =
    Option(br.readLine()).map(line =>
      line :: readFileToList(br, list)) getOrElse list

  def readFileToList(path: String, charsetName: String): Either[String, List[String]] =
    getBufferedReader(new File(path), charsetName) fold (
      error => Left(error),
      bufferReader => {
        val list = readFileToList(bufferReader, List[String]())
        bufferReader.close()
        Right(list)
      })

  private def readFileToString(br: BufferedReader, text: String): String =
    Option(br.readLine()).map(line =>
      s"$line\n${readFileToString(br, text)}") getOrElse text

  def readFileToString(path: String, charsetName: String): Either[String, String] =
    getBufferedReader(new File(path), charsetName) fold (
      error => Left(error),
      bufferReader => {
        val text: String = readFileToString(bufferReader, "")
        if (logger.isInfoEnabled) logger.info(s"readFileToString() - text : $text")
        bufferReader.close()
        Right(text)
      })

  def loadProperties(filePath: String): Either[String, Properties] =
    if (filePath != null && !"".equals(filePath.trim())) {
      if (logger.isInfoEnabled) logger.info(s"loadProperties() - from filePath[$filePath]")
      Try {
        Right({
          val props = new Properties()
          props.load(new FileInputStream(filePath))
          props
        })
      } getOrElse {
        logger.error(
          s"""
             |
             | loadProperties() - on read properties from file:\n
             | \t - $filePath
             |
         """.stripMargin)
        Left("load.properties.error")
      }
    } else Left("load.properties.error.file.path.is.empty")

}
