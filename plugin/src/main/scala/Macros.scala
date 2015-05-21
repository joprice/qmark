package com.github.joprice

import scala.language.experimental.macros
import scala.language.dynamics
import scala.reflect.macros.blackbox.Context

trait Typo extends Dynamic {

  def applyDynamic(name: String)(args: Any*) = macro Typo.contextImpl

  def selectDynamic(name: String) = macro Typo.contextImplSelect

}

object Typo {

  def contextImpl(c: Context)(name: c.Expr[String])(args: c.Expr[Any]*) = dud(c)

  def contextImplSelect(c: Context)(name: c.Expr[String]) = dud(c)

  def dud(c: Context) = {
    import c.universe._
    reify((???): Nothing with Typo)
  }

}

