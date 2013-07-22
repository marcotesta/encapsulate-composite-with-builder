package it.mondogrua.p2p.encapsulate_composite_with_builder;

public class TagBuilder {

    private TagNode rootNode;
    private TagNode currentNode;

    public TagBuilder(String rootTagName) {
        this.rootNode = new TagNode(rootTagName);
        this.currentNode = this.rootNode;
    }

    public String toXml() {
        return rootNode.toString();
    }

    public void addChild(String childTagName) {
        addTo(currentNode, childTagName);
    }

    public void addSibling(String siblingTagName) {
        addTo(currentNode.getParent(), siblingTagName);
    }
    
    public void addTo(TagNode parentNode, String tagName) {
        currentNode = new TagNode(tagName);
        parentNode.add(currentNode);
    }

    public void addToParent(String parentTagName, String childTagName) {
        addTo(findParentBy(parentTagName), childTagName);
    }

    private TagNode findParentBy(String parentName) {
        TagNode parentNode = currentNode;
        while (parentNode != null) {
            if (parentName.equals(parentNode.getName())) {
                return parentNode;
            }
            parentNode = parentNode.getParent();
        }
        return null;
    }

}
