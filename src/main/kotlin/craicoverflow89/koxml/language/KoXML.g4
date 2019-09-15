grammar KoXML;

@header
{
    import craicoverflow89.koxml.language.constructs.*;
    import java.util.ArrayList;
}

document returns [KoXMLDocument result]
    :   '<?xml' attributeList '?>'
        root = nodeGroup
        EOF
        {$result = new KoXMLDocument($root.result, $attributeList.result);}
    ;

nodeGroup returns [KoXMLGroup result]
    :   '<' tag1 = string attributeList '>'
        {ArrayList<KoXMLNode> children = new ArrayList();}
        (
            (
                nodeGroup {children.add($nodeGroup.result);}
            |
                nodeSingle {children.add($nodeSingle.result);}
            |
                nodeValue {children.add($nodeValue.result);}
            |
                comment
            )
        )*
        '<' '/' tag2 = string '>'
        {$result = new KoXMLGroup($tag1.text, $attributeList.result, children);}
    ;

nodeSingle returns [KoXMLSingle result]
    :   '<' tag = string attributeList '/' '>'
        {$result = new KoXMLSingle($tag.text, $attributeList.result);}
    ;

nodeValue returns [KoXMLValue result]
    :   '<' tag1 = string attributeList '>'
        value = stringValue
        '<' '/' tag2 = string '>'
        {$result = new KoXMLValue($tag1.text, $attributeList.result, $value.text);}
    ;

attributeList returns [KoXMLAttributeList result]
    @init {ArrayList<KoXMLAttribute> list = new ArrayList();}
    :   (
            ' ' attribute {list.add($attribute.result);}
        )*
        {$result = new KoXMLAttributeList(list);}
    ;

attribute returns [KoXMLAttribute result]
    :   key = string '=' '"' value = stringValue '"'
        {$result = new KoXMLAttribute($key.text, $value.text);}
    ;

string
    :   CHAR+
    ;

stringValue
    :   (string | ' ' | '-' | '!' | '|')*
    ;

comment
    :   '<!--' (string | ' ' | '<' | '>' | '/' | '=' | '"' | '|')+ '-->'
    ;

// Lexer Rules

CHAR: [A-Za-z0-9.]+;
WHITESPACE: [ \t\r\n]+ -> skip;