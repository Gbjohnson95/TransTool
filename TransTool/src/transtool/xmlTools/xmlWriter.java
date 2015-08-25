package transtool.xmlTools;

import java.io.File;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class xmlWriter {
    // TO-DO
    // Create virtual doc & and make root element
    //      - newXmlDoc(root element, atributes[])
    // Create new element such as quiz question
    //      - newElement(element, atributes[])
    // Write to file
    //      - writeDoc(new File("C:\\file.xml")
    
    public static void xmlWriter () throws ParserConfigurationException {
       DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
       DocumentBuilder docBuilder = docFactory.newDocumentBuilder();        
    }
    
    public DocumentBuilderFactory docFactory;
    public   DocumentBuilder docBuilder = docFactory.newDocumentBuilder(); 
    public Document doc = docBuilder.newDocument();

    public xmlWriter() {
        this.docFactory = DocumentBuilderFactory.newInstance();
    }
    
    public void writeDoc(/*xml doc*/) throws TransformerConfigurationException {
    
       TransformerFactory transformerFactory = TransformerFactory.newInstance();
       Transformer transformer = TransformerFactory.newTransformer();
       DOMSource source = new DOMSource(doc);
       StreamResult result = new StreamResult(new File("path to file"));
    
    }
   
    public static void newElement() {
       // Code   
    }
    
}
