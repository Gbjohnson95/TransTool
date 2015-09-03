/*
 * Ravenclaw Rules!!!
 */
package transtool.xmlTools;

import Questions.BrainhoneyContents;
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
public class XMLParser {

    // After everything has been parsed, brainhoney will hold all the variables.
    private ArrayList<BrainhoneyContents> brainhoney;
    
    // nanme of XML document to be parsed.
    private String nameOfXML;

    /*
     * default constructor; takes a String from the user and parses the XML doc.
    */
    public XMLParser() {
        brainhoney = new ArrayList<>();

        System.out.println("Please enter the name of the XML document that you wish to parse through: ");
        Scanner scanner = new Scanner(System.in);
        nameOfXML = scanner.findInLine("Enter Something: ");
    }

    /*
     * Takes a string in and parses the XML document.
    */
    public XMLParser(String nameOfXML) {
        
        brainhoney = new ArrayList<>();
        this.nameOfXML = nameOfXML;
        System.out.println("non default constructor: " + nameOfXML);
        parseXMLDom();
    }

    /*
     * uses the DOM parser to sort through the XML document and separate
     * the variables.
    */
    public void parseXMLDom() {
        
        //Standard opening procedures for DOM.
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        
        // Surrounded with a try-catch statement is the main bulk of the 
        // code; the XML parser is basically the point of this entire class.
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(nameOfXML);

            // Normalize the document.  
            doc.getDocumentElement().normalize();

            // Now we hone in on everything that has a tag called question.
            // However, there are other question tags as well, so we have to 
            // also make sure that we are pulling the right information off.
            NodeList nodeList = doc.getElementsByTagName("question");

            // And now we sort through each question individually.
            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                Node nNode = nodeList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    // If the question does not have an ID, it is an invalid
                    // question.
                    if (!eElement.getAttribute("questionid").isEmpty()) {
                        // Create all the objects that will eventually be pushed
                        // onto the Brainhoney array.
                        BrainhoneyContents brain = new BrainhoneyContents();
                        ArrayList<String> qChoice = new ArrayList<>();
                        ArrayList<String> rightAnswer = new ArrayList<>();

                        // The easiest of questions are in the attributes.
                        // One liner.  awwwhh yeahhh.
                        brain.setPartial(eElement.getAttribute("partial"));
                        brain.setScore(eElement.getAttribute("score"));

                        // Keep this for now.  May go in the final product.  
                        System.out.println("Score: " + eElement.getAttribute("score"));
                        System.out.println("Is partial? " + eElement.getAttribute("partial"));

                        // Next, we have to pull the interaction type off.
                        // Very long code. 
                        brain.setInteractionType(eElement.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());
                        System.out.println("Interaction: " + brain.getInteractionType());

                        // Now we pull off the correct answers, if there are any.
                        for (int i = 0; i < eElement.getElementsByTagName("value").getLength(); i++) {
                            rightAnswer.add(eElement.getElementsByTagName("value").item(i).getTextContent());
                            System.out.println("Right Answer Number: " + eElement.getElementsByTagName("value").item(i).getTextContent());
                        }
                        System.out.println("Length of possible answers: " + eElement.getElementsByTagName("body").getLength());

                        // The body of each question.  It's kind of weird, but... that's the way it looks.
                        for (int i = 1; i < eElement.getElementsByTagName("body").getLength(); i++) {
                            qChoice.add(eElement.getElementsByTagName("body").item(i).getTextContent());
                            System.out.println("Body Text for Questions: " + eElement.getElementsByTagName("body").item(i).getTextContent());
                        }

                        // Last, we push everything together and finally on brain.
                        brain.setRightAnswer(rightAnswer);
                        brain.setqChoice(qChoice);
                        brainhoney.add(brain);

                        // For aesthetic purposes.
                        System.out.println(" ");
                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: Cannot read file.  Exception thrown!");
        }
        
        System.out.println("Number of questions: " + brainhoney.size());
    }

    /*
     * Probably WILL NOT be implementing, but I am leaving this here just in
     * case something happens.
    */
    public void xMLParseSax() {
    }

}
