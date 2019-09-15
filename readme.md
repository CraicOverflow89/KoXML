KoXML Project
=============

Lightweight XML parser written in Kotlin.

**Usage**

You can parse a `File` or simply a `String` using the `KoXML` class.

```
fun main(args: Array<String>)
{
    // Parse an XML file
    val document = KoXML.parseFile(File("/path/to/file.xml"))

    // Examine the output
    println(document.getAttributes())
    println(document.getRoot().getAttributes())
    println(document.getRoot().getChildren())
}
```