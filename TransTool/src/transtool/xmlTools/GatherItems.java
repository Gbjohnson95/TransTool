/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.xmlTools;

import GradeItems.GradeCategories;
import GradeItems.WriteGradeItems;
import Items.DropBox;
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
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<QuizItem> quizItem = new ArrayList<>();
    private String savePath;
    private int gradeAssociation;
    private ArrayList<GradeCategories> gradeCategories = new ArrayList<>();

    private int itemID = 10000;
    private int quizID = 1;
    private ArrayList<DropBox> dropBoxes = new ArrayList<>();

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
                Element nodes = (Element) node.item(i);
                Element dataStructure = (Element) nodes.getElementsByTagName("data").item(0);
                NodeList data = dataStructure.getChildNodes();

                NodeList testing = dataStructure.getElementsByTagName("type");

                if (testing.getLength() < 1) {

                } else if (testing.item(0).getTextContent().isEmpty()) {

                } else {
                    String type = dataStructure.getElementsByTagName("type").item(0).getTextContent();

                    switch (type) {
                        case ("Assessment"):
                        case ("Homework"):
                            System.out.println("Creating a quiz item now: " + dataStructure.getElementsByTagName("title").item(0).getTextContent());

                            createQuizItem(dataStructure, doc);
                            break;
                        case ("Assignment"):
                            System.out.println("Creating a DropBox now.");
                            createDropBox(dataStructure, doc);
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

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public String getSavePath() {
        return savePath;
    }

    public int getGradeAssociation() {
        return gradeAssociation;
    }

    public void setGradeAssociation(int gradeAssociation) {
        this.gradeAssociation = gradeAssociation;
    }

    public ArrayList<GradeCategories> getGradeCategories() {
        return gradeCategories;
    }

    public void setGradeCategories(ArrayList<GradeCategories> gradeCategories) {
        this.gradeCategories = gradeCategories;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public int getQuizID() {
        return quizID;
    }

    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    public ArrayList<QuizItem> getQuizItem() {
        return quizItem;
    }

    public void setQuizItem(ArrayList<QuizItem> quizItem) {
        this.quizItem = quizItem;
    }

    public ArrayList<DropBox> getDropBoxes() {
        return dropBoxes;
    }

    public void setDropBoxes(ArrayList<DropBox> dropBoxes) {
        this.dropBoxes = dropBoxes;
    }

    void createQuizItem(Element data, Document doc) {
        QuizItem quiz = new QuizItem();
        quiz.setSavePath(savePath);
        quiz.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        quiz.setName(data.getElementsByTagName("title").item(0).getTextContent());
        quiz.setLocation(data.getElementsByTagName("href").item(0).getTextContent());
        if (data.getElementsByTagName("gradable").getLength() > 0) {
            quiz.setGradeable(data.getElementsByTagName("gradable").item(0).getTextContent());
            quiz.setWeight(data.getElementsByTagName("weight").item(0).getTextContent());
            quiz.setCategory(data.getElementsByTagName("category").item(0).getTextContent());
        } else {
            quiz.setGradeable("false");
        }

        NodeList attemptLimit = data.getElementsByTagName("attemptlimit");
        if (attemptLimit.getLength() > 0) {
            quiz.setAttemptLimit(data.getElementsByTagName("attemptlimit").item(0).getTextContent());
        }

        NodeList question = data.getElementsByTagName("question");

        ArrayList<BrainhoneyContents> quizQuestions = new ArrayList<>();

        for (int j = 0; j < question.getLength(); j++) {

            BrainhoneyContents quizQuestion = new BrainhoneyContents();
            Element quizElement = (Element) question.item(j);
            if (quizElement.hasAttribute("id")) {
                if (quizElement.getAttribute("id").length() > 5) {
                    quizQuestion.setQuestionID(quizElement.getAttribute("id"));
                    quizQuestions.add(quizQuestion);
                }
            }
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
                        quizQuestion.setScore(bQuestion.getAttribute("score"));
                        if (bQuestion.getElementsByTagName("value").getLength() > 0) {
                            quizQuestion.setScore(bQuestion.getElementsByTagName("value").item(0).getTextContent());
                        }
                        quizQuestion.setInteractionType(bQuestion.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());
                        quizQuestion.setPartial(bQuestion.getAttribute("partial"));

                        if (bQuestion.getElementsByTagName("value").getLength() > 0) {
                            NodeList values;
                            values = bQuestion.getElementsByTagName("value");
                            ArrayList<String> value = new ArrayList<>();
                            for (int k = 0; k < values.getLength(); k++) {
                                value.add(values.item(k).getTextContent());
                                System.out.println("Correct Answer: " + values.item(k).getTextContent() + " is this spacing?");
                            }
                            quizQuestion.setRightAnswer(value);

                        } else if (quizQuestion.getInteractionType().equals("match")) {
                            NodeList answers;
                            answers = bQuestion.getElementsByTagName("answer");
                            ArrayList<String> answer = new ArrayList<>();
                            for (int k = 0; k < answers.getLength(); k++) {
                                answer.add(answers.item(k).getTextContent());
                            }
                            quizQuestion.setRightAnswer(answer);

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

        quiz.setItemQuizFeed(itemID, quizID);
        quiz.setBrainhoney(quizQuestions);

        items.add(quiz);
        quizItem.add(quiz);

        identifier++;
        id++;

    }

    public void createDropBox(Element data, Document doc) {

        NodeList isDropBox = data.getElementsByTagName("dropbox");
        if (isDropBox.getLength() > 0) {
            DropBox dropBox = new DropBox();

            dropBox.setSavePath(savePath);
            dropBox.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
            dropBox.setName(data.getElementsByTagName("title").item(0).getTextContent());
            dropBox.setLocation(data.getElementsByTagName("href").item(0).getTextContent());
            if (data.getElementsByTagName("gradable").getLength() > 0) {
                dropBox.setGradeable(data.getElementsByTagName("gradable").item(0).getTextContent());
                dropBox.setWeight(data.getElementsByTagName("weight").item(0).getTextContent());
                dropBox.setCategory(data.getElementsByTagName("category").item(0).getTextContent());
            } else {
                dropBox.setGradeable("false");
            }
            dropBox.setItemID(Integer.toString(id));
            dropBox.setIdent(Integer.toString(identifier));

            items.add(dropBox);
            dropBoxes.add(dropBox);
            identifier++;
            id++;
        }
    }

    public void writeItems() {
        for (Item item : items) {
            item.writeItem();
        }
    }

}
