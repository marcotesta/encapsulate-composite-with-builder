package it.mondogrua.p2p.encapsulate_composite_with_builder;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TagNode {

    private final String name;
    private String value = "";
    private StringBuffer attributes;
    private List<TagNode> children;
    private TagNode parent;

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
    
    public void add(TagNode child) {
        child.setParent(this);
        children().add(child);
    }

    private void setParent(TagNode parent) {
        this.parent = parent;
    }

    public TagNode getParent() {
        return this.parent;
    }

    @Override
    public String toString() {
        StringBuffer result = new StringBuffer();
        result.append("<").append(name).append(attributes).append(">");
        for (TagNode node : children()) {
            result.append(node.toString());
        }
        result.append(value);
        result.append("</").append(name).append(">");
        return result.toString();
    }

    private List<TagNode> children() {
        if (this.children == null) {
            this.children = new ArrayList<TagNode>();
        }
        return this.children;
    }

}
