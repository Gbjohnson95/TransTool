/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.xmlTools;

import Items.Item;
import Items.QuizItem;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class GatherItems {

    private String nameOfXML;
    private int identifier = 10000;
    private int id = 3;
    private DocumentBuilder doc;
    private ArrayList<Item> items = new ArrayList<>();
    private String savePath;
    private int gradeAssociation = 20000;

    public void populateItems() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();

            Document doc = dBuilder.parse(nameOfXML);

            // Normalize the document.  
            doc.getDocumentElement().normalize();

            NodeList node = doc.getElementsByTagName("item");

            for (int i = 0; i < node.getLength(); i++) {

                Element data = (Element) node.item(0);

                if (data.getChildNodes().getLength() <= 15) {

                } else {
                    String type = data.getElementsByTagName("type").item(0).getTextContent();

                    switch (type) {
                        case ("Assessment"):
                        case ("Homework"):
                            createQuizItem(data, doc);
                            break;
                        case ("Assignment"):
                            break;
                        case ("Discussion"):
                            break;
                        case ("Resource"):
                            break;

                    }
                }

            }

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(BrainhoneyItemParse.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(GatherItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GatherItems.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getNameOfXML() {
        return nameOfXML;
    }

    public void setNameOfXML(String nameOfXML) {
        this.nameOfXML = nameOfXML;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DocumentBuilder getDoc() {
        return doc;
    }

    public void setDoc(DocumentBuilder doc) {
        this.doc = doc;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getSavePath() {
        return savePath;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    void createQuizItem(Element data, Document doc) {
        QuizItem quiz = new QuizItem();
        quiz.setSavePath(savePath);
        quiz.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        quiz.setName(data.getElementsByTagName("title").item(0).getTextContent());
        quiz.setLocation(data.getElementsByTagName("href").item(0).getTextContent());
        quiz.setGradeable(data.getElementsByTagName("gradable").item(0).getTextContent());

        if (quiz.getGradeable().equals("true")) {
            quiz.setWeight(data.getElementsByTagName("weight").item(0).getTextContent());
            quiz.setGradeAssociation(Integer.toString(gradeAssociation));
            gradeAssociation++;
        }

        quiz.setAttemptLimit(data.getElementsByTagName("attemptLimit").item(0).getTextContent());

        NodeList question = data.getElementsByTagName("question");

        ArrayList<BrainhoneyContents> quizQuestions = new ArrayList<>();

        for (int j = 0; j < question.getLength(); j++) {
            BrainhoneyContents quizQuestion = new BrainhoneyContents();
            Element quizElement = (Element) question.item(j);
            quizQuestion.setQuestionID(quizElement.getAttribute("id"));
            quizQuestions.add(quizQuestion);
        }

        quiz.setItemID(Integer.toString(id));
        quiz.setIdent(Integer.toString(identifier));

        NodeList questions = doc.getElementsByTagName("question");

        for (int j = 0; j < questions.getLength(); j++) {
            Element bQuestion = (Element) questions.item(j);
            if (bQuestion.hasAttribute("questionid")) {
                for (BrainhoneyContents quizQuestion : quizQuestions) {
                    if (quizQuestion.getQuestionID().equals(bQuestion.getAttribute("questionid"))) {
                        quizQuestion.setBody(bQuestion.getElementsByTagName("body").item(0).getTextContent());
                        quizQuestion.setScore(bQuestion.getElementsByTagName("value").item(0).getTextContent());
                        quizQuestion.setInteractionType(bQuestion.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());
                        quizQuestion.setPartial(bQuestion.getAttribute("partial"));

                        if (bQuestion.getElementsByTagName("value").getLength() > 0) {
                            NodeList values = bQuestion.getElementsByTagName("value");
                            ArrayList<String> value = new ArrayList<>();
                            for (int k = 0; k < values.getLength(); k++) {
                                value.add(values.item(k).getTextContent());
                            }
                            quizQuestion.setRightAnswer(value);
                        }

                        if (bQuestion.getElementsByTagName("body").getLength() > 1) {
                            NodeList bodies = bQuestion.getElementsByTagName("body");
                            ArrayList<String> bText = new ArrayList<>();
                            for (int k = 1; k < bodies.getLength(); k++) {
                                Element body = (Element) bodies.item(k);
                                bText.add(body.getTextContent());
                            }
                            quizQuestion.setqChoice(bText);
                        }
                    }
                }
            }
        }

        quiz.populateClass();
        items.add(quiz);

        identifier++;
        id++;

    }

}
