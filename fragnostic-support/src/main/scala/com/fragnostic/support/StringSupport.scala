package com.fragnostic.support

trait StringSupport {

  def format(texto: String, args: String): String =
    format(texto, Seq(args))

  def format(texto: String, args: Seq[String]): String =
    args.zipWithIndex.foldLeft(texto) {
      case (res, (value, index)) =>
        res.replace(s"""{$index}""", value.toString)
    }

}
