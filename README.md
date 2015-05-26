QMark
=================

### A scala compiler plugin for supporting the safe reference operator '?'

This is a toy project for learning the scala compiler plugin apis. It only supports the '?' in a restricted way, and will likely
break in interesting and unexpected ways. See [Test.scala](test/src/main/scala/Test.scala) for an example of the syntax it supports.

### Usage
* TODO

### Known Issues
* the `?` operator must be followed by a space, with no period: `x? toUpperCase`

### Resources

Some useful code and articles useful when writing compiler plugins:

* http://static.javadoc.io/org.scala-lang/scala-library/2.10.0-M5/index.html#scala.reflect.internal.Trees$TreeContextApiImpl
* https://github.com/miniboxing/miniboxing-plugin/blob/7c9de702f19d875e1c227ff9ea8e85970a6d6814/components/plugin/src/miniboxing/plugin/transform/interop/inject/InteropInjectTreeTransformer.scala
* https://github.com/kevinwright/Autoproxy-Lite
* https://github.com/jrudolph/scala-enhanced-strings
* https://code.google.com/p/avro-scala-compiler-plugin/source/browse/trunk/src/main/scala/plugin/
* http://www.scala-lang.org/old/node/140
* https://github.com/scala/scala-continuations/blob/e771745a7255e778511953d7d32a9ee5d95a0625/plugin/src/main/scala/scala/tools/selectivecps/SelectiveCPSPlugin.scala
* http://scala-miniboxing.org/ (video)
* https://github.com/gseitz/Lensed
* https://github.com/adamw/jsr305-scala-compiler-plugin/blob/master/plugin/src/main/scala/AnnotationsCheckGenPlugin.scala
* http://www.scala-lang.org/api/2.11.1/scala-compiler/index.html#scala.tools.nsc.transform.TypingTransformers$TypingTransformer
* https://github.com/luben/sctags/blob/master/src/main/scala/sctags/TagGeneration.scala
* https://gist.github.com/mdr/1679333#file-gistfile1-scala
* https://github.com/plausiblelabs/nx/blob/1db603a51a11935ee971ba5904c5fe46510f2943/src/main/scala/coop/plausible/nx/internal/CompilerPlugin.scala
