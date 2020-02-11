package com.fragnostic.support

import java.io._
import java.util.Properties

import org.slf4j.{ Logger, LoggerFactory }

import scala.util.Try

trait FilesSupport {

  private[this] val loggerFilesSup: Logger = LoggerFactory.getLogger(getClass.getName)

  def fileToBufferedReader(file: File, charsetName: String): Either[String, BufferedReader] =
    try {
      loggerFilesSup.info(s"fileToBufferedReader() - file:$file") //aqui
      Right(new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName)))
    } catch {
      case e: Exception =>
        loggerFilesSup.error(s"fileToBufferedReader() - $e")
        Left("files.support.file.to.buffered.reader.error")
    }

  def bufferedReaderToList(br: BufferedReader, list: List[String]): List[String] =
    Option(br.readLine()).map(line => line :: bufferedReaderToList(br, list)) getOrElse { list }

  def fileToList(path: String, charsetName: String): Either[String, List[String]] =
    fileToBufferedReader(new File(path), charsetName) fold (
      error => Left(error),
      bufferReader =>
        try {
          loggerFilesSup.info(s"fileToList() - bufferedReader is OK") //aqui
          Right(bufferedReaderToList(bufferReader, List[String]()))
        } finally {
          bufferReader.close()
        })

  def bufferedReaderToString(br: BufferedReader, text: String): String =
    Option(br.readLine()).map(line => s"$line\n${bufferedReaderToString(br, text)}") getOrElse { text }

  def fileToString(path: String, charsetName: String): Either[String, String] =
    fileToBufferedReader(new File(path), charsetName) fold (error => Left(error),
      bufferReader =>
        try {
          Right(bufferedReaderToString(bufferReader, ""))
        } finally {
          bufferReader.close()
        })

  def loadProperties(filePath: String): Either[String, Properties] =
    if (filePath != null && !"".equals(filePath.trim())) {
      if (loggerFilesSup.isInfoEnabled) loggerFilesSup.info(s"loadProperties() - from filePath[$filePath]")
      Try {
        Right({
          val props = new Properties()
          props.load(new FileInputStream(filePath))
          props
        })
      } getOrElse {
        loggerFilesSup.error(s"""
             |
             | loadProperties() - on read properties from file:\n
             | \t - $filePath
             |
         """.stripMargin)
        Left("load.properties.error")
      }
    } else Left("load.properties.error.file.path.is.empty")

  @scala.annotation.tailrec
  private def writeLinesToBufferedReader(lines: List[String], buffWrit: BufferedWriter): Unit =
    lines match {
      case Nil =>
        buffWrit.newLine()
      case head :: Nil =>
        buffWrit.write(head)
        buffWrit.newLine()
      case head :: tail =>
        buffWrit.write(head)
        buffWrit.newLine()
        writeLinesToBufferedReader(tail, buffWrit)
    }

  def writeLinesToFile(lines: List[String], fileName: String): Either[String, String] = {
    try {
      val file = new File(fileName)
      val buffWrit = new BufferedWriter(new FileWriter(file))
      writeLinesToBufferedReader(lines, buffWrit)
      buffWrit.close()
      Right("files.support.write.lines.to.file.success")
    } catch {
      case e: Exception =>
        loggerFilesSup.error(s"writeLinesToFile() - $e")
        Left("files.support.write.lines.to.file.error")
    }
  }

}
