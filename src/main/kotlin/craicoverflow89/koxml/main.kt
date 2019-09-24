package craicoverflow89.koxml

import craicoverflow89.koxml.language.KoXMLLexer
import craicoverflow89.koxml.language.KoXMLParser
import craicoverflow89.koxml.language.constructs.KoXMLDocument
import java.io.File
import org.antlr.v4.runtime.ANTLRInputStream
import org.antlr.v4.runtime.CommonTokenStream

class KoXML {

    companion object {

        fun parseFile(input: File) = parseText(input.readText())

        fun parseText(input: String): KoXMLDocument {
            val lexer = KoXMLLexer(ANTLRInputStream(input))
            val parser = KoXMLParser(CommonTokenStream(lexer))
            return parser.document().result
        }

    }

}