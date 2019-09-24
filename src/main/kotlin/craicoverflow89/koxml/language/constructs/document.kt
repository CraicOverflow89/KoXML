package craicoverflow89.koxml.language.constructs

/**
 * A key/value pair of attributes defined in the XML document
 *
 * @property key the name of the attribute
 * @property value the value of the attribute
 */
class KoXMLAttribute(private val key: String, private val value: String) {

    /**
     * Gets the key of this attribute
     *
     * @return the key of this attribute
     */
    fun getKey() = key

    /**
     * Gets the value of this attribute
     *
     * @return the value of this attribute
     */
    fun getValue() = value

    override fun toString() = "$key: $value"

}

/**
 * A list of key/value pairs defined in the XML document
 *
 * @property list a list of KoXMLAttribute instances
 */
class KoXMLAttributeList(private val list: ArrayList<KoXMLAttribute>) {

    /**
     * Builds a map of attributes for this attribute list
     *
     * @return a HashMap<String, String> representation of data from the XML document
     */
    fun toMap() = HashMap<String, String>().apply {
        list.forEach {this[it.getKey()] = it.getValue()}
    }

    override fun toString() = "[${list.joinToString(", ")}]"

}

/**
 * The entire parsed XML document
 *
 * @property root the root element of the XML document
 * @property attributes a list of attributes defined at the top of the XML document
 */
class KoXMLDocument(private val root: KoXMLNode, private val attributes: KoXMLAttributeList) {

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

abstract class KoXMLNode(private val type: String, protected val tag: String, protected val attributes: KoXMLAttributeList? = null, private val children: ArrayList<KoXMLNode>? = null) {

    fun debug(indent: Int, lastItem: Boolean = true, parentLastItem: Boolean = true): List<String> {

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
        if(children != null) for(child in children.withIndex()) {
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

class KoXMLValue(tag: String, attributes: KoXMLAttributeList, private val value: String): KoXMLNode("Value", tag, attributes) {

    fun getValue() = value

    override fun toString() = ArrayList<String>().apply {
        add("Value")
        add("<$tag>")
        add(value)
        if(attributes != null) add(attributes.toString())
    }.joinToString(" ")

}