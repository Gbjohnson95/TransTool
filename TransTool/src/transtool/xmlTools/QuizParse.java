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
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 *
 * QUIZ PARSE
 *
 * Variables: Question ID - contains a list of ID's that belong to the quiz.
 */
public class QuizParse {

    private ArrayList<Quiz> quiz;
    private String nameOfXML;
    private ArrayList<BrainhoneyContents> brainhoneyContents;
    private ArrayList<String> quizName = new ArrayList<>();

    public QuizParse(String nameOfXML, ArrayList<BrainhoneyContents> brainhoney) {
        this.quiz = new ArrayList<>();

        brainhoneyContents = brainhoney;

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
            NodeList testList = doc.getElementsByTagName("type");

            //NodeList nodie  = doc.getElementsByTagName("item");
            //NodeList nodeTest2 = nodie.item(3).getChildNodes();
            // And now we sort through each question individually.
            // Normalize the document.  
            doc.getDocumentElement().normalize();

            for (int temp = 0; temp < testList.getLength(); temp++) {
                Element eElement = (Element) testList.item(temp).getParentNode();

                if (testList.item(temp).getTextContent().equals("Assessment") && eElement.getElementsByTagName("title").getLength() != 0) {
                    System.out.println(eElement.getElementsByTagName("title").item(0).getTextContent());
                    quizName.add(eElement.getElementsByTagName("title").item(0).getTextContent());
                } else if (testList.item(temp).getTextContent().equals("Assessment") && eElement.getElementsByTagName("title").getLength() == 0) {
                    quizName.add("Blank Test");
                }

            }
            System.out.println(quizName.size());

            for (int temp = 0; temp < nodeList.getLength() - 1; temp++) {

                boolean isQuiz = false;
                // Creating a quiz to push onto the quiz stack.
                Quiz tQuiz = new Quiz();
                ArrayList<String> questions = new ArrayList<>();

                Node nNode = nodeList.item(temp);

                NodeList question = nNode.getChildNodes();

                //System.out.println(eElement.getParentNode().getChildNodes().item(4).getTextContent());
                for (int i = 0; i < question.getLength(); i++) {

                    if (question.item(i).getNodeType() == Node.ELEMENT_NODE) {

                        Element element = (Element) question.item(i);
                        if (!element.getAttribute("id").isEmpty()) {
                            questions.add(element.getAttribute("id"));

                        }
                        isQuiz = true;
                    }
                }
                tQuiz.setQuizQuestions(questions);
                //tQuiz.setQuizName(quizName.get(temp));

                if (isQuiz == true) {
                    quiz.add(tQuiz);
                }

            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(QuizParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(quiz.size() + " and question size: " + quizName.size());
        for (int i = 0; i < quiz.size(); i++) {
            System.out.println(quizName.get(i));
            quiz.get(i).setQuizName(quizName.get(i));
        }
        assignSections();
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

    void assignSections() {

        for (Quiz quiz1 : quiz) {
            ArrayList<BrainhoneyContents> tempContents = new ArrayList<>();
            for (String question : quiz1.getQuizQuestions()) {

                for (BrainhoneyContents brainhoneyContent : brainhoneyContents) {
                    if (question.equals(brainhoneyContent.getQuestionID())) {
                        tempContents.add(brainhoneyContent);
                    }
                }
            }
            quiz1.setBrainhoney(tempContents);
        }
    }

}
