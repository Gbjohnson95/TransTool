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
import org.xml.sax.SAXException;

/**
 *
 * @author hallm8
 * 
 * XML PARSER
 * 
 * XML Parser will take in a String path and load in a file. 
 * It will parse through an XML Document, and store certain information
 * into an ArrayList
 */
public class XMLParser {
    private String nameOfXML;

    public XMLParser() {
        
        System.out.println("Please enter the name of the XML document that you wish to parse through: ");
        Scanner scanner = new Scanner(System.in);
        nameOfXML = scanner.findInLine("Enter Something: ");
   }

    public XMLParser(String nameOfXML) {
        this.nameOfXML = nameOfXML;
    }
    
    public void parse(){
        File fXmlFile = new File("/Users/mkyong/staff.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(nameOfXML);
            // Normalize the document.  
            doc.getDocumentElement().normalize();
            
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: Cannot read file.  Exception thrown!");
        }

			

    }
    
    
    
}
