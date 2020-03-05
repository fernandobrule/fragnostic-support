package com.fragnostic.support

import java.io._
import java.util.Properties

import better.files.{ File => BFFile }
import org.slf4j.{ Logger, LoggerFactory }

import scala.annotation.tailrec
import scala.util.Try

trait FilesSupport {

  private[this] val loggerfs: Logger = LoggerFactory.getLogger(getClass.getName)

  @tailrec
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

  def bufferedReaderToList(br: BufferedReader, list: List[String]): List[String] =
    Option(br.readLine()).map(line => line :: bufferedReaderToList(br, list)) getOrElse { list }

  def bufferedReaderToString(br: BufferedReader, text: String): String =
    Option(br.readLine()).map(line => s"$line\n${bufferedReaderToString(br, text)}") getOrElse { text }

  def fileToBufferedReader(file: File, charsetName: String): Either[String, BufferedReader] =
    try {
      loggerfs.info(s"fileToBufferedReader() - file:$file") //aqui
      Right(new BufferedReader(new InputStreamReader(new FileInputStream(file), charsetName)))
    } catch {
      case e: Exception =>
        loggerfs.error(s"fileToBufferedReader() - $e")
        Left("files.support.file.to.buffered.reader.error")
    }

  def fileToList(path: String, charsetName: String): Either[String, List[String]] =
    fileToBufferedReader(new File(path), charsetName) fold (error => Left(error),
      bufferReader =>
        try {
          loggerfs.info(s"fileToList() - bufferedReader is OK") //aqui
          Right(bufferedReaderToList(bufferReader, List[String]()))
        } finally {
          bufferReader.close()
        })

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
      if (loggerfs.isInfoEnabled) loggerfs.info(s"loadProperties() - from filePath[$filePath]")
      Try {
        Right({
          val props = new Properties()
          props.load(new FileInputStream(filePath))
          props
        })
      } getOrElse {
        loggerfs.error(s"""
             |
             | loadProperties() - on read properties from file:\n
             | \t - $filePath
             |
         """.stripMargin)
        Left("files.support.load.properties.error")
      }
    } else Left("files.support.load.properties.error.file.path.is.empty")

  private def write(fos: FileOutputStream, bytes: Array[Byte]): Unit =
    Try(fos.write(bytes))

  //
  // https://github.com/pathikrit/better-files/
  //
  def save(bytes: Array[Byte], ruta: String): Either[String, String] =
    Try(
      BFFile(ruta) fileOutputStream (append = false) foreach (write(_, bytes))) fold (
        error => {
          loggerfs.error(s"save() - ${error.getMessage}")
          Left(s"files.support.save.error")
        },
        aaaa => {
          Right("files.support.save.success")
        })

  def writeLinesToFile(lines: List[String], fileName: String): Either[String, String] =
    try {
      val file = new File(fileName)
      val buffWrit = new BufferedWriter(new FileWriter(file))
      writeLinesToBufferedReader(lines, buffWrit)
      buffWrit.close()
      Right("files.support.write.lines.to.file.success")
    } catch {
      case e: Exception =>
        loggerfs.error(s"writeLinesToFile() - $e")
        Left("files.support.write.lines.to.file.error")
    }

}
