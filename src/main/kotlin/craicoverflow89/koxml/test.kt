package craicoverflow89.koxml

fun main(args: Array<String>)
{
    // Parse File
    val document = KoXML.parseText(object {}.javaClass.getResource("/input.xml").readText())

    // Debug Output
    println(document.getAttributes())
    println(document.getRoot().getAttributes())
    println(document.getRoot().getChildren())
}