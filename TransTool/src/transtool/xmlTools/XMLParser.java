/*
 * Ravenclaw Rules!!! 
 */
package transtool.xmlTools;

import Items.Item;
import Parameter.Parameter;
import transtool.questions.BrainhoneyContents;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
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
 * XML Parser will take in a String path and load in a file. It will parse
 * through an XML Document, and store certain information into an ArrayList
 */
public class XMLParser {

    // After everything has been parsed, brainhoney will hold all the variables.
    private ArrayList<BrainhoneyContents> brainhoney;

    // name of XML document to be parsed.
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
            // Normalize the document.  
            doc.getDocumentElement().normalize();

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
                        ArrayList<Parameter> parameter = new ArrayList<>();
                        NodeList param = eElement.getElementsByTagName("parameter");
                        Element tElement = (Element) param.item(0);

                        // The easiest of questions are in the attributes.
                        // One liner.  awwwhh yeahhh.
                        brain.setPartial(eElement.getAttribute("partial"));
                        brain.setScore(eElement.getAttribute("score"));

                        // Keep this for now.  May go in the final product.  
                        // Next, we have to pull the interaction type off.
                        // Very long code. 
                        brain.setInteractionType(eElement.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());

                        // Now we pull off the correct answers, if there are any.
                        //if (eElement.getElementsByTagName("parameter").getLength() > 0) {
                        for (int i = 0; i < eElement.getElementsByTagName("value").getLength(); i++) {
                            rightAnswer.add(eElement.getElementsByTagName("value").item(i).getTextContent());
                        }

                        brain.setBody(eElement.getElementsByTagName("body").item(0).getTextContent());
                        // Last, we push everything together and finally on brain.
                        if (param.getLength() > 0) {
                            if (tElement.getElementsByTagName("value").getLength() < 2) {
                                // Do nothing.
                            } else {
                                Element pValues = (Element) param.item(0);
                                NodeList values = pValues.getElementsByTagName("value");

                                for (int i = 0; i < values.getLength(); i++) {
                                    ArrayList<String> pChoice = new ArrayList<>();
                                    ArrayList<String> pRightAnswer = new ArrayList<>();
                                    BrainhoneyContents multi = new BrainhoneyContents();

                                    multi.setPartial(eElement.getAttribute("partial"));
                                    multi.setScore(eElement.getAttribute("score"));
                                    multi.setInteractionType(eElement.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());
                                    multi.setQuestionID(eElement.getAttribute("questionid"));

                                    multi.setRightAnswer(pRightAnswer);
                                    multi.setqChoice(pChoice);
                                    multi.setBody(eElement.getElementsByTagName("body").item(0).getTextContent());

                                    ArrayList<Element> eList = new ArrayList<>();

                                    for (int j = 0; j < param.getLength(); j++) {
                                        System.out.println(j + " = J");
                                    }

                                }
                            }
                        } else {
                            for (int i = 1; i < eElement.getElementsByTagName("body").getLength(); i++) {
                                qChoice.add(eElement.getElementsByTagName("body").item(i).getTextContent());
                            }
                            // The body of each question.  It's kind of weird, but... that's the way it looks.
                            if (brain.getInteractionType().equals("text")) {

                                for (int i = 0; i < rightAnswer.size(); i++) {
                                    qChoice.add(rightAnswer.get(i));
                                }
                            }
                            if (brain.getInteractionType().equals("match")) {
                                NodeList answer = eElement.getElementsByTagName("answer");
                                for (int i = 0; i < answer.getLength(); i++){
                                    rightAnswer.add(answer.item(i).getTextContent());
                                }
                            }
                            brain.setRightAnswer(rightAnswer);
                            brain.setqChoice(qChoice);
                            brain.setQuestionID(eElement.getAttribute("questionid"));
                            brainhoney.add(brain);
                        }

                    }
                }
            }

        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(XMLParser.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error: Cannot read file.  Exception thrown!");
        }

        System.out.println("Number of questions: " + brainhoney.size());
    }

    public ArrayList<BrainhoneyContents> getBrainhoney() {
        return brainhoney;
    }

    public void setBrainhoney(ArrayList<BrainhoneyContents> brainhoney) {
        this.brainhoney = brainhoney;
    }
}
