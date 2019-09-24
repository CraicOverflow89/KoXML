KoXML Project
=============

Lightweight XML parser written in Kotlin.

### Usage

You can parse a `File` or simply a `String` using the `KoXML` class.

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