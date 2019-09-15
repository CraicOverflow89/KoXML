package craicoverflow89.koxml.language.constructs

class KoXMLAttribute(private val key: String, private val value: String)
{

    override fun toString() = "$key: $value"
}

class KoXMLAttributeList(private val list: ArrayList<KoXMLAttribute>)
{

    override fun toString() = "[${list.joinToString(", ")}]"

}

class KoXMLDocument(private val root: KoXMLNode, private val attributes: KoXMLAttributeList)
{

    fun getRoot() = root

    override fun toString() = "KoXMLDocument $attributes"

}

class KoXMLGroup(tag: String, attributes: KoXMLAttributeList, children: ArrayList<KoXMLNode>): KoXMLNode("Group", tag, attributes, children)

abstract class KoXMLNode(private val type: String, private val tag: String, private val attributes: KoXMLAttributeList? = null, private val children: ArrayList<KoXMLNode>? = null)
{

    fun getChildren() = children

    override fun toString() = ArrayList<String>().apply {
        add("KoXMLNode")
        add("<$tag>")
        if(children != null) add("(${children.size})")
        if(attributes != null) add(attributes.toString())
    }.joinToString(" ")

}

class KoXMLSingle(tag: String, attributes: KoXMLAttributeList): KoXMLNode("Single", tag, attributes)

class KoXMLValue(tag: String, value: String): KoXMLNode("Value", tag)