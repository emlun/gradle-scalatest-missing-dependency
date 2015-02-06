Missing dependency in gradle-scalatest?
===

This repo reproduces an issue I encountered when trying to get
[gradle-scalatest][grsct] to work.

[grsct]: https://github.com/maiflai/gradle-scalatest


Reproduction instructions
---

Using gradle-scalatest fails due to a ClassNotFoundException:

    $ git checkout master && ./gradlew test
    :clean
    :compileJava UP-TO-DATE
    :compileScala
    :processResources UP-TO-DATE
    :classes
    :compileTestJava UP-TO-DATE
    :compileTestScala
    [ant:scalac] Element '<redacted>/build/resources/main' does not exist.
    :processTestResources UP-TO-DATE
    :testClasses
    :testAn exception or error caused a run to abort. This may have been caused by a problematic custom reporter.
    java.lang.NoClassDefFoundError: org/pegdown/PegDownProcessor
            at org.scalatest.tools.HtmlReporter.<init>(HtmlReporter.scala:115)
            at org.scalatest.tools.ReporterFactory.createHtmlReporter(ReporterFactory.scala:182)
            at org.scalatest.tools.ReporterFactory.getReporterFromConfiguration(ReporterFactory.scala:233)
            at org.scalatest.tools.ReporterFactory$$anonfun$createReportersFromConfigurations$1.apply(ReporterFactory.scala:242)
            at org.scalatest.tools.ReporterFactory$$anonfun$createReportersFromConfigurations$1.apply(ReporterFactory.scala:241)
            at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:245)
            at scala.collection.TraversableLike$$anonfun$map$1.apply(TraversableLike.scala:245)
            at scala.collection.Iterator$class.foreach(Iterator.scala:750)
            at scala.collection.AbstractIterator.foreach(Iterator.scala:1202)
            at scala.collection.IterableLike$class.foreach(IterableLike.scala:72)
            at org.scalatest.tools.ReporterConfigurations.foreach(ReporterConfiguration.scala:43)
            at scala.collection.TraversableLike$class.map(TraversableLike.scala:245)
            at org.scalatest.tools.ReporterConfigurations.map(ReporterConfiguration.scala:43)
            at org.scalatest.tools.ReporterFactory.createReportersFromConfigurations(ReporterFactory.scala:241)
            at org.scalatest.tools.ReporterFactory.getDispatchReporter(ReporterFactory.scala:245)
            at org.scalatest.tools.Runner$.withClassLoaderAndDispatchReporter(Runner.scala:2720)
            at org.scalatest.tools.Runner$.runOptionallyWithPassFailReporter(Runner.scala:1043)
            at org.scalatest.tools.Runner$.main(Runner.scala:860)
            at org.scalatest.tools.Runner.main(Runner.scala)
    Caused by: java.lang.ClassNotFoundException: org.pegdown.PegDownProcessor
            at java.net.URLClassLoader$1.run(URLClassLoader.java:372)
            at java.net.URLClassLoader$1.run(URLClassLoader.java:361)
            at java.security.AccessController.doPrivileged(Native Method)
            at java.net.URLClassLoader.findClass(URLClassLoader.java:360)
            at java.lang.ClassLoader.loadClass(ClassLoader.java:424)
            at sun.misc.Launcher$AppClassLoader.loadClass(Launcher.java:308)
            at java.lang.ClassLoader.loadClass(ClassLoader.java:357)
            ... 19 more
     FAILED

However running the test using JUnit instead works fine:

    $ git checkout junit && ./gradlew clean test
    :clean
    :compileJava UP-TO-DATE
    :compileScala
    :processResources UP-TO-DATE
    :classes
    :compileTestJava UP-TO-DATE
    :compileTestScala
    [ant:scalac] Element '<redacted>/build/resources/main' does not exist.
    :processTestResources UP-TO-DATE
    :testClasses
    :test

    BUILD SUCCESSFUL

    Total time: 14.612 secs
