/*
 * QUIZ PARSER
 *  This quiz parsing tool will find each question and link them to a quiz.  
 * This in turn will allow us to split everything into sections.
 */
package transtool.xmlTools;

import GradeItems.GradeCategories;
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
    private ArrayList<GradeCategories> gradeCategories = new ArrayList<>();

    /**
     * Constructor. Does all of the work for you.
     *
     * @param nameOfXML
     * @param brainhoney
     */
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

            parseGradingCategories(doc.getElementsByTagName("categories"));

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
                } else if (testList.item(temp).getTextContent().equals("AssetLink")) {
                    System.out.println("Link found!!");
                } else if (testList.item(temp).getTextContent().equals("Resource")) {
                    System.out.println("Resource found!!");
                } else if (testList.item(temp).getTextContent().equals("Assignment")) {
                    System.out.println("Dropbox found!!");
                }
                else
                    System.out.println(testList.item(temp).getTextContent());

            }

            // Goes through the nodelist except for the last node and sorts 
            // through and finds each quiz.
            for (int temp = 0; temp < nodeList.getLength() - 1; temp++) {

                boolean isQuiz = false;
                // Creating a quiz to push onto the quiz stack.
                Quiz tQuiz = new Quiz();
                ArrayList<String> questions = new ArrayList<>();

                Node nNode = nodeList.item(temp);

                NodeList question = nNode.getChildNodes();

                // Checks to see if the item is a quiz.  If it is, it pulls off
                // the name of the quiz.
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

                if (isQuiz == true) {
                    quiz.add(tQuiz);
                }

            }
        } catch (SAXException | IOException | ParserConfigurationException ex) {
            Logger.getLogger(QuizParse.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(quiz.size() + " and question size: " + quizName.size());
        System.out.println("How many quiz names?!" + quizName.size());
        for (int i = 0; i < quizName.size(); i++) {
            System.out.println("Quiz name is: " + quizName.get(i) + ". Index: " + i);
            quiz.get(i).setQuizName(quizName.get(i));

        }
        assignSections();
    }

    /**
     * QUIZ
     *
     * The quiz - question ID's, the Questions, and the quiz name.
     *
     * @return
     */
    public ArrayList<Quiz> getQuiz() {
        return quiz;
    }

    /**
     * QUIZ
     *
     * The quiz - question ID's, the Questions, and the quiz name.
     *
     * @param quiz
     */
    public void setQuiz(ArrayList<Quiz> quiz) {
        this.quiz = quiz;
    }

    /**
     * NAME OF XML
     *
     * This is the name of the XML that we are reading from
     *
     * @return
     */
    public String getNameOfXML() {
        return nameOfXML;
    }

    /**
     * NAME OF XML
     *
     * This is the name of the XML that we are reading from.
     *
     * @param nameOfXML
     */
    public void setNameOfXML(String nameOfXML) {
        this.nameOfXML = nameOfXML;
    }

    /**
     * ASSIGN SECTIONS
     *
     * Here, the questions are injected into each quiz, so that the sections can
     * be made.
     */
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

    public void parseGradingCategories(NodeList nodeList) {
        NodeList categories = nodeList.item(0).getChildNodes();

        // Go through each category and drop it in the ArrayList.
        for (int i = 0; i < categories.getLength(); i++) {
            GradeCategories category = new GradeCategories();
            if (categories.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) categories.item(i);
                category.setCatID(element.getAttribute("id"));
                category.setCatName(element.getAttribute("name"));
                category.setCatWeight(element.getAttribute("weight"));
                category.setDropLowest(element.getAttribute("droplowest"));
                gradeCategories.add(category);
            }
        }

    }
}
