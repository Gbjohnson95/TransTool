/*
 * QUIZ PARSER
 *  This quiz parsing tool will find each question and link them to a quiz.  
 * This in turn will allow us to split everything into sections.
 */
package transtool.xmlTools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author hallm8
 *
 * QUIZ PARSE
 *
 * Variables: Question ID - contains a list of ID's that belong to the quiz.
 */
public class QuizParse {

    ArrayList<Quiz> quiz;
    private String nameOfXML;

    public QuizParse(String nameOfXML) {
        this.quiz = new ArrayList<>();

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
            NodeList nodeList = doc.getElementsByTagName("questions");
            
            //NodeList nodie  = doc.getElementsByTagName("item");
            //NodeList nodeTest2 = nodie.item(3).getChildNodes();
            
            
            // And now we sort through each question individually.
            // Normalize the document.  
            doc.getDocumentElement().normalize();

            for (int temp = 0; temp < nodeList.getLength(); temp++) {

                // Creating a quiz to push onto the quiz stack.
                Quiz tQuiz = new Quiz();
                ArrayList<String> questions = new ArrayList<>();

                Node nNode = nodeList.item(temp);

                Node data = nNode.getParentNode();
                Element eElement = (Element) nNode;

                NodeList question = nNode.getChildNodes();

                
                //System.out.println(eElement.getParentNode().getChildNodes().item(4).getTextContent());

                for (int i = 0; i < question.getLength(); i++) {
                    if (question.item(i).getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) question.item(i);
                        if (!element.getAttribute("id").isEmpty()) {
                            questions.add(element.getAttribute("id"));
                        }
                    }
                }
                tQuiz.setQuizQuestions(questions);
                tQuiz.setQuizName(Integer.toString(temp));
            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(QuizParse.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(ArrayList<Quiz> quiz) {
        this.quiz = quiz;
    }

    public String getNameOfXML() {
        return nameOfXML;
    }

    public void setNameOfXML(String nameOfXML) {
        this.nameOfXML = nameOfXML;
    }
    
    
    
    

}
