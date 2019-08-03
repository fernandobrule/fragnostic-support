package com.fragnostic.support

trait MapSupport {

  private def getMap(
    list: List[String],
    map: Map[String, String],
    valida: String => Boolean,
    getKV: String => (String, String)): Map[String, String] =
    if (list.isEmpty) {
      map
    } else if (valida(list.head)) {
      val kv = getKV(list.head)
      getMap(list.tail, map + (kv._1.trim -> kv._2.trim), valida, getKV)
    } else {
      getMap(list.tail, map, valida, getKV)
    }

  def getMap(
    list: List[String],
    valida: String => Boolean,
    getKV: String => (String, String)): Map[String, String] =
    getMap(list, Map[String, String](), valida, getKV)

}
