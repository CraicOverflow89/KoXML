KoXML Project
=============

[![HitCount](http://hits.dwyl.io/CraicOverflow89/KoXML.svg)](http://hits.dwyl.io/CraicOverflow89/KoXML)

[![Known Vulnerabilities](https://snyk.io//test/github/CraicOverflow89/KoXML/badge.svg?targetFile=build.gradle)](https://snyk.io//test/github/CraicOverflow89/KoXML?targetFile=build.gradle)

Lightweight XML parser written in Kotlin.

### Usage

You can parse a `File` using the `KoXML` class (or simply a `String`, using the `parseText` method).

```
import java.io.File

fun main(args: Array<String>) {

    // Parse an XML file
    val document = KoXML.parseFile(File("/path/to/file.xml"))

    // Examine the output
    document.apply {
        println(getAttributes())
        println(getRoot().getAttributes())
        println(getRoot().getChildren())
    }
}
```
