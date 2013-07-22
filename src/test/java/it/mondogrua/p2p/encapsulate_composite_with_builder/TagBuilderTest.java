package it.mondogrua.p2p.encapsulate_composite_with_builder;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.junit.Assert.*;

import org.junit.Test;

public class TagBuilderTest {

    @Test
    public void testBuildOneNode() throws Exception {
        String expectedXml = "<flavors/>";
        String actualXml = new TagBuilder("flavors").toXml();
        assertXMLEqual("",expectedXml, actualXml);
    }
    
    @Test
    public void testBuildOneChild() throws Exception {
        String expected = "<flavors><flavor/></flavors>";
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        String actualXml = builder.toXml();
        
        assertXMLEqual(expected, actualXml);
    }

    @Test
    public void testChildrenOfChildren() throws Exception {
        String expectedXml = 
          "<flavors>" +
            "<flavor>" +
              "<requirements>" +
                "<requirement/>" +
              "</requirements>" +
            "</flavor>" +
          "</flavors>";
        
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor");
        builder.addChild("requirements");
        builder.addChild("requirement");
        
        String actualXml = builder.toXml();
        
        assertXMLEqual(expectedXml, actualXml);
    }
    
    @Test
    public void testBuildSibling() throws Exception {
        String expectedXml = 
                "<flavors>" +
                  "<flavor1/>" +
                  "<flavor2/>" +
                "</flavors>";
        
        TagBuilder builder = new TagBuilder("flavors");
        builder.addChild("flavor1");
        builder.addSibling("flavor2");
        
        String actualXml = builder.toXml();
        
        assertXMLEqual(expectedXml, actualXml);
    }
    
    @Test
    public void testParent() throws Exception {
        TagNode root = new TagNode("root");
        assertNull(root.getParent());
        
        TagNode child = new TagNode("child");
        root.add(child);
        
        assertEquals(root, child.getParent());
        assertEquals("root", child.getParent().getName());
    }
    
    @Test
    public void testRepeatingChildrenAndGrandchildren() throws Exception {
        
        String expectedXml = 
                "<flavors>" +
                  "<flavor>" +
                    "<requirements>" +
                      "<requirement/>" +
                    "</requirements>" +
                  "</flavor>" +
                  "<flavor>" +
                    "<requirements>" +
                      "<requirement/>" +
                    "</requirements>" +
                  "</flavor>" +
                "</flavors>";
        
        TagBuilder builder = new TagBuilder("flavors");

        for (int i = 0; i<2; ++i) {
            builder.addToParent("flavors", "flavor");
            builder.addChild("requirements");
            builder.addChild("requirement");
        }
        
        String actualXml = builder.toXml();
        assertXMLEqual(expectedXml, actualXml);
       
    }
}
