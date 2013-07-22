package it.mondogrua.p2p.encapsulate_composite_with_builder;

public class TagNode {

    private final String name;
    private String value = "";
    private StringBuffer attributes;

    public TagNode(String name) {
        this.name = name;
        this.attributes = new StringBuffer("");
    }

    public String getName() {
        return name;
    }

    public void addValue(String value) {
        this.value  = value;
    }

    public void addAttribute(String attribute, String value) {
        attributes.append(" ");
        attributes.append(attribute);
        attributes.append("='");
        attributes.append(value);
        attributes.append("'");
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("<").append(name).append(attributes).append(">");
        result.append(value);
        result.append("</").append(name).append(">");
        return result.toString();
    }

}
