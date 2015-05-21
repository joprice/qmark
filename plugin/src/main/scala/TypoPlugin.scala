package com.github.joprice.qmark

import scala.tools.nsc.transform.{Transform, TypingTransformers}
import scala.tools.nsc
import nsc.Global
import nsc.Phase
import nsc.plugins.Plugin
import nsc.plugins.PluginComponent

//TODO: handle parsing better (can only use with spaces)
//TODO: don't desugar when question mark method exists on object (would have to wait until after typer phase and require reifying the '?' into its own tree?)
class QMarkPlugin(val global: Global) extends Plugin {

  val name = "qmark"
  val description = "enables support for the safe reference operator '?'"
  val components = List[PluginComponent](ReplaceQMarks)

  private object ReplaceQMarks extends PluginComponent with TypingTransformers with Transform {
    val phaseName = QMarkPlugin.this.name
    val global = QMarkPlugin.this.global

    import global._

    //override val checkable = false

    override val runsAfter = List("parser")
    override val runsRightAfter = Some("parser")

    override protected def newTransformer(unit: CompilationUnit): Transformer = new Transformer {
      override def transform(tree: Tree) = {
        super.transform(tree) match {
          // handle what looks like a unary functions
          case Apply(Select(exp, TermName("$qmark")), List(Select(arg, TermName(name)))) if name.startsWith("unary_") =>
            // TODO: gen unique name?
            q"""({
              val result = $exp
              if (result ne null) result.${TermName(name.stripPrefix("unary_"))}($arg) else null
            })"""

          // handle normal functions
          case Apply(Select(exp, TermName("$qmark")), List(Apply(Ident(method@TermName(_)), args))) =>
            q"""({
              val result = $exp
              if (result ne null) result.${method}(..$args) else null
            })"""

          // handle functions with no arguments
          case Apply(Select(exp, TermName("$qmark")), List(Ident(method))) =>
            q"""({
              val result = $exp
              if (result ne null) result.${method.toTermName} else null
            })"""
          case t => t
        }
      }
    }
  }

}

