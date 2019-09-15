package craicoverflow89.koxml.language.constructs

class KoXMLAttribute(private val key: String, private val value: String)
{

    fun getKey() = key

    fun getValue() = value

    override fun toString() = "$key: $value"

}

class KoXMLAttributeList(private val list: ArrayList<KoXMLAttribute>)
{

    fun toMap() = HashMap<String, String>().apply {
        list.forEach {this[it.getKey()] = it.getValue()}
    }

    override fun toString() = "[${list.joinToString(", ")}]"

}

class KoXMLDocument(private val root: KoXMLNode, private val attributes: KoXMLAttributeList)
{

    fun debug() = println(this.let {document -> ArrayList<String>().apply {
        add(document.toString())
        addAll(root.debug(0))
    }.joinToString("\n")})

    fun getAttributes() = attributes.toMap()

    fun getRoot() = root

    override fun toString() = "Document $attributes"

}

class KoXMLGroup(tag: String, attributes: KoXMLAttributeList, children: ArrayList<KoXMLNode>): KoXMLNode("Group", tag, attributes, children)
// NOTE: would it be best to convert multiple KoXMLValue objects with shared tag into a list of values?

abstract class KoXMLNode(private val type: String, protected val tag: String, private val attributes: KoXMLAttributeList? = null, private val children: ArrayList<KoXMLNode>? = null)
{

    fun debug(indent: Int, lastItem: Boolean = true, parentLastItem: Boolean = true): List<String>
    {
        // Define Reuslt
        val result = ArrayList<String>()

        // Node Data
        result.add(this.let {node -> ArrayList<String>().apply {
            if(indent > 0) add("   ")
            if(indent > 1) add((if(parentLastItem) "   " else " | ").repeat(indent - 1))
            add(if(lastItem) " └ " else " ├ ")
            add(node.toString())
        }}.joinToString(""))
        // NOTE: checking parentLastItem doesn't give us any information about their parent (and so on)
        //       we actually need to use something like List<Boolean> for each level of nodes

        // Iterate Children
        if(children != null) for(child in children.withIndex())
        {
            result.addAll(child.value.debug(indent + 1, child.index >= children.size - 1, lastItem))
        }

        // Return Result
        return result
    }

    fun getChildren() = children

    fun getAttributes(): HashMap<String, String> = attributes?.toMap() ?: hashMapOf()

    override fun toString() = ArrayList<String>().apply {
        add(type)
        add("<$tag>")
        if(children != null) add("(${children.size})")
        if(attributes != null) add(attributes.toString())
    }.joinToString(" ")

}

class KoXMLSingle(tag: String, attributes: KoXMLAttributeList): KoXMLNode("Single", tag, attributes)

class KoXMLValue(tag: String, private val value: String): KoXMLNode("Value", tag)
{

    fun getValue() = value

    override fun toString() = ArrayList<String>().apply {
        add("Value")
        add("<$tag>")
        add(value)
    }.joinToString(" ")

}