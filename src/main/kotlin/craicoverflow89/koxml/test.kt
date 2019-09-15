package craicoverflow89.koxml

import craicoverflow89.koxml.language.KoXMLLexer
import craicoverflow89.koxml.language.KoXMLParser
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

fun main (args: Array<String>)
{
    // Input File
    val input = object {}.javaClass.getResource("/input.xml").readText()

    // Parse File
    val lexer = KoXMLLexer(ANTLRInputStream(input))
    val parser = KoXMLParser(CommonTokenStream(lexer))
    val document = parser.document().result

    // Debug Output
    println(document.getAttributes())
    println(document.getRoot().getAttributes())
    println(document.getRoot().getChildren())
}