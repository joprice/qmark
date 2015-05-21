package com.github.joprice.qmark

object Test {

  case class User(name: String)

  def main(args: Array[String]): Unit = {
    val user = User("alfred")
    val result = user? name? toUpperCase? substring(0,5)? reverse? + "s"
    println("result", result)
    require(result == "ERFLAs")
  }

}


