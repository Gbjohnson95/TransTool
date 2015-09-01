/*
 * Ravenclaw Rules!!!
 */
package transtool.xmlTools;

import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NamedNodeMap;

import org.xml.sax.SAXException;

/**
 *
 * @author hallm8
 *
 * XML PARSER
 *
 * XML Parser will take in a String path and load in a file. It will parse
 * through an XML Document, and store certain information into an ArrayList
 */
public final class XMLParser {

    private String nameOfXML;

    public XMLParser() {

        System.out.println("Please enter the name of the XML document that you wish to parse through: ");
        Scanner scanner = new Scanner(System.in);
        nameOfXML = scanner.findInLine("Enter Something: ");
    }

    public XMLParser(String nameOfXML) {
        this.nameOfXML = nameOfXML;
        System.out.println("non default constructor: " + nameOfXML);
        parseXMLDom();
    }

    public void parseXMLDom() {
        File fXmlFile = new File(nameOfXML);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse("brainhoneymanifest.xml");

            // Normalize the document.  
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("question");

            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node nNode = nodeList.item(temp);
                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                        //Element value = (Element)eElement.getElementsByTagName("value");
                    //NodeList test = eElement.getAttributeNode("interaction").getName();
                    //Element value = (Element) test;

                    NodeList subList = eElement.getChildNodes();

                    if (!eElement.getAttribute("questionid").isEmpty()) {
                        
                        NamedNodeMap test = eElement.getElementsByTagName("interaction").item(0).getAttributes();
                        //System.out.println("Group: " + eElement.getAttributeNode("value").hasChildNodes() );
                        System.out.println(eElement.getElementsByTagName("body").item(0).getTextContent());
                        //System.out.println("Test: " + eElement.getElementsByTagName("interaction").item(0).getAttributes());
                        System.out.println("Type: " + eElement.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());

                        if (!eElement.getElementsByTagName("value").item(0).getTextContent().isEmpty()) {
                            System.out.println("Value: " + eElement.getElementsByTagName("value").item(0).getTextContent());
                        }

                        System.out.println("First Name : " + eElement.getAttribute("questionid"));
                        //System.out.println("GlibbetyGloop : " + childNode.getFirstChild());

			//System.out.println("Last Name : " + eElement.getElementsByTagName("lastname").item(0).getTextContent());
                        //System.out.println("Nick Name : " + eElement.getElementsByTagName("nickname").item(0).getTextContent());
                        //System.out.println("Salary : " + eElement.getElementsByTagName("salary").item(0).getTextContent());
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: Cannot read file.  Exception thrown!");
        }

    }

    public void xMLParseSax() {

    }

}
