/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.xmlTools;

import GradeItems.GradeCategories;
import Items.AssetLink;
import Items.Content;
import Items.DiscussionBoard;
import Items.DropBox;
import Items.Folder;
import Items.Item;
import Items.QuizItem;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class GatherItems {

    private int identifier;
    private int id;
    private ArrayList<Item> items;
    private ArrayList<QuizItem> quizItem;
    private String savePath;
    private String brainhoneyPath;
    private int gradeAssociation;
    private ArrayList<GradeCategories> gradeCategories;
    private int itemID;
    private int quizID;
    private ArrayList<DropBox> dropBoxes;
    private String courseTitle;
    private ArrayList<String> contentNames;
    private ArrayList<Folder> folders;

    /**
     * GATHER ITEMS
     *
     * This Constructor will initialize
     */
    public GatherItems() {
        Random randomGen = new Random();

        this.quizID = 1;
        this.itemID = randomGen.nextInt(400001) + 100000;
        this.id = 3;
        this.identifier = randomGen.nextInt(40001) + 10000;
        this.items = new ArrayList<>();
        this.quizItem = new ArrayList<>();
        this.gradeCategories = new ArrayList<>();
        this.dropBoxes = new ArrayList<>();
        this.folders = new ArrayList<>();
    }

    /**
     *
     */
    public void populateItems() {
        contentNames = new ArrayList<>();

        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(brainhoneyPath);

            // Normalize the document.  
            doc.getDocumentElement().normalize();

            Element title = (Element) doc.getElementsByTagName("course").item(0);
            courseTitle = title.getAttribute("title");

            NodeList node = doc.getElementsByTagName("item");

            for (int i = 1; i < node.getLength(); i++) {
                Element nodes = (Element) node.item(i);
                Element dataStructure = (Element) nodes.getElementsByTagName("data").item(0);
                NodeList dataS = nodes.getElementsByTagName("data");
                if (dataS.getLength() > 0) {

                    NodeList testing = dataStructure.getElementsByTagName("type");

                    if (testing.getLength() < 1) {
                        // Likely a folder.  We shall see... we shall see.
                        createFolder(dataStructure, doc);

                    } else if (testing.item(0).getTextContent().isEmpty()) {

                    } else {
                        String type = dataStructure.getElementsByTagName("type").item(0).getTextContent();

                        switch (type) {
                            case ("Assessment"):
                            case ("Homework"):
                                createQuizItem(dataStructure, doc);
                                break;
                            case ("Assignment"):
                                createDropBox(dataStructure, doc);
                                break;
                            case ("Discussion"):
                                createDiscussionBoard(dataStructure, doc);
                                break;
                            case ("Resource"):
                                createContentPage(dataStructure, doc);
                                break;
                            case ("AssetLink"):
                                createAssetLink(dataStructure, doc);
                                break;
                        }
                    }
                }
            }

            parseGradingCategories(doc.getElementsByTagName("categories"));

        } catch (ParserConfigurationException ex) {
        } catch (SAXException ex) {
            Logger.getLogger(GatherItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GatherItems.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     *
     * @return
     */
    public int getIdentifier() {
        return identifier;
    }

    /**
     *
     * @param identifier
     */
    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     *
     * @param items
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    /**
     *
     * @return
     */
    public String getSavePath() {
        return savePath;
    }

    /**
     *
     * @return
     */
    public int getGradeAssociation() {
        return gradeAssociation;
    }

    /**
     *
     * @param gradeAssociation
     */
    public void setGradeAssociation(int gradeAssociation) {
        this.gradeAssociation = gradeAssociation;
    }

    /**
     *
     * @return
     */
    public ArrayList<GradeCategories> getGradeCategories() {
        return gradeCategories;
    }

    /**
     *
     * @param gradeCategories
     */
    public void setGradeCategories(ArrayList<GradeCategories> gradeCategories) {
        this.gradeCategories = gradeCategories;
    }

    /**
     *
     * @return
     */
    public int getItemID() {
        return itemID;
    }

    /**
     *
     * @param itemID
     */
    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    /**
     *
     * @return
     */
    public int getQuizID() {
        return quizID;
    }

    /**
     *
     * @param quizID
     */
    public void setQuizID(int quizID) {
        this.quizID = quizID;
    }

    /**
     *
     * @param savePath
     */
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }

    /**
     *
     * @return
     */
    public ArrayList<QuizItem> getQuizItem() {
        return quizItem;
    }

    /**
     *
     * @param quizItem
     */
    public void setQuizItem(ArrayList<QuizItem> quizItem) {
        this.quizItem = quizItem;
    }

    /**
     *
     * @return
     */
    public ArrayList<DropBox> getDropBoxes() {
        return dropBoxes;
    }

    /**
     *
     * @param dropBoxes
     */
    public void setDropBoxes(ArrayList<DropBox> dropBoxes) {
        this.dropBoxes = dropBoxes;
    }

    public ArrayList<String> getContentNames() {
        return contentNames;
    }

    public void setContentNames(ArrayList<String> contentNames) {
        this.contentNames = contentNames;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    /**
     *
     * @return
     */
    public String getCourseTitle() {
        return courseTitle;
    }

    /**
     *
     * @param courseTitle
     */
    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    /**
     *
     * @param data
     * @param doc
     */
    void createQuizItem(Element data, Document doc) {
        QuizItem quiz = new QuizItem();
        quiz.setSavePath(savePath);
        quiz.setBrainhoneyPath(brainhoneyPath);
        quiz.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        if (data.getElementsByTagName("title").getLength() != 0){
        quiz.setName(data.getElementsByTagName("title").item(0).getTextContent());
        }
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
                        quizQuestion.setDidFill(true);
                        quizQuestion.setBody(bQuestion.getElementsByTagName("body").item(0).getTextContent());
                        quizQuestion.setScore(bQuestion.getAttribute("score"));
                        quizQuestion.setInteractionType(bQuestion.getElementsByTagName("interaction").item(0).getAttributes().getNamedItem("type").getTextContent());
                        quizQuestion.setPartial(bQuestion.getAttribute("partial"));

                        if (bQuestion.getElementsByTagName("value").getLength() > 0) {
                            NodeList values;
                            values = bQuestion.getElementsByTagName("value");
                            ArrayList<String> value = new ArrayList<>();
                            for (int k = 0; k < values.getLength(); k++) {
                                value.add(values.item(k).getTextContent());
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

    /**
     *
     * @param data
     * @param doc
     */
    public void createDropBox(Element data, Document doc) {

        NodeList isDropBox = data.getElementsByTagName("dropbox");

        DropBox dropBox = new DropBox();

        dropBox.setBrainhoneyPath(brainhoneyPath);
        dropBox.setSavePath(savePath);
        dropBox.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        if (data.getElementsByTagName("title").getLength() > 0) {
            dropBox.setName(data.getElementsByTagName("title").item(0).getTextContent());
        } else {
            dropBox.setName("Untitled DropBox");
        }

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

        if (isDropBox.getLength() > 0) {
            items.add(dropBox);
            dropBoxes.add(dropBox);
        } else {
            createContentPage(data, doc);
        }
        identifier++;
        id++;

    }

    /**
     *
     * @param data
     * @param doc
     */
    public void createDiscussionBoard(Element data, Document doc) {
        DiscussionBoard discussionBoard = new DiscussionBoard();

        discussionBoard.setBrainhoneyPath(brainhoneyPath);
        discussionBoard.setSavePath(savePath);
        discussionBoard.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        discussionBoard.setName(data.getElementsByTagName("title").item(0).getTextContent());
        discussionBoard.setLocation(data.getElementsByTagName("href").item(0).getTextContent());
        if (data.getElementsByTagName("gradable").getLength() > 0) {
            discussionBoard.setGradeable(data.getElementsByTagName("gradable").item(0).getTextContent());
            discussionBoard.setWeight(data.getElementsByTagName("weight").item(0).getTextContent());
            discussionBoard.setCategory(data.getElementsByTagName("category").item(0).getTextContent());
        } else {
            discussionBoard.setGradeable("false");
        }
        discussionBoard.setItemID(Integer.toString(id));
        discussionBoard.setIdent(Integer.toString(identifier));
        discussionBoard.setDid(Integer.toString(id + 1));
        discussionBoard.setDident(Integer.toString(identifier + 1));

        items.add(discussionBoard);
        identifier++;
        identifier++;
        id++;
        id++;
    }

    /**
     *
     */
    public void writeItems() {
        items.stream().forEach((item) -> {
            item.writeItem();
        });
    }

    /**
     *
     * @param nodeList
     */
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

    void createContentPage(Element data, Document doc) {
        Content content = new Content();

        if (data.getElementsByTagName("title").getLength() > 0) {
            content.setName(data.getElementsByTagName("title").item(0).getTextContent());
        } else {
            content.setName("Empty Page");
        }

        // If I randomize the name to one number in 999999, there is a one in a million
        // chance of getting the exact same number as another one.
        for (String contentName : contentNames) {
            if (content.getName().equals(contentName)) {
                Random randomGen = new Random();
                content.setName(content.getName() + Integer.toString(randomGen.nextInt(999999)));
            }
        }

        content.setMaterialType("content");
        contentNames.add(content.getName());
        content.setGradeable("false");
        content.setLocation(data.getElementsByTagName("href").item(0).getTextContent());
        content.setItemID(data.getAttribute("id"));
        content.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        content.setIdent(Integer.toString(identifier));
        identifier++;
        content.setBrainhoneyPath(brainhoneyPath);
        content.setSavePath(savePath);
        content.setItemType("Content");
        items.add(content);
    }

    void createInternalAsset(Element data, Document doc) {

    }

    void createExternalLink(Element data, Document doc) {

    }

    /**
     * CREATE FOLDER
     *
     * Creates a Folder and adds it to the folders array list.
     *
     * @param data
     * @param doc
     */
    void createFolder(Element data, Document doc) {
        Folder folder = new Folder();
        if (data.getElementsByTagName("folder").getLength() > 0){
        folder.setFolderID(data.getElementsByTagName("folder").item(0).getTextContent());
        }
        else
            folder.setFolderID("000001");
        if (data.getElementsByTagName("folder").getLength() > 0) {
            folder.setFolderName(data.getElementsByTagName("title").item(0).getTextContent());
        } else {
            folder.setFolderName("Blank Folder");
        }
        folder.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        folders.add(folder);
    }

    /**
     * ASSET LINK: Pulls a link from the content and puts it right into the
     * content page.
     *
     * @param data
     * @param doc
     */
    public void createAssetLink(Element data, Document doc) {
        AssetLink asset = new AssetLink();

        // For links, we need the parent, the title (if one exists), the link
        // location, and... that's it. 
        //
        // However, it should be noted that some assets are called as documents,
        // and others are called as URL's.  We need to decide between the two,
        // so an if/then statement should suffice.  If it is a URL, simply slap
        // it on the href.  If it is a content item though, we have to strip the
        // location and place it in Course Files
        asset.setParent(data.getElementsByTagName("parent").item(0).getTextContent());

        if (data.getElementsByTagName("title").getLength() > 0) {
            asset.setName(data.getElementsByTagName("title").item(0).getTextContent());
        } else {
            asset.setName("Unnamed Link");
        }

        String href = data.getElementsByTagName("href").item(0).getTextContent();
        // No folder location SHOULD have a // in it, so that is very convenient.
        if (href.contains("//")) {
            asset.setHref(href);
            asset.setMaterialType("contentlink");
        } else {
            String appendedPath = new String();
            for (int i = 0; i < href.length(); i++) {
                appendedPath += href.charAt(i);
                if (href.charAt(i) == '/') {
                    appendedPath = new String();
                }
            }

            // I am going to simply pull the item right now, so the transition
            // smoothly comes across.
            String newLocation = brainhoneyPath.replace("brainhoneymanifest.xml", "") + "resources\\" + href;
            File source = new File(newLocation);
            File dest = new File(savePath + "\\Course Files\\Documents and Images\\" + appendedPath);
            try {
                FileUtils.copyFile(source, dest);
            } catch (IOException e) {
                System.out.println("Error!!! Could not load file!");
                System.out.println("Source is: " + source.getAbsolutePath());
                System.out.println("Destination is: " + dest.getAbsolutePath());
            }

            href = "Course Files\\Documents and Images\\" + appendedPath;
            asset.setMaterialType("content");
        }
        
        asset.setItemID(data.getAttribute("id"));
        asset.setParent(data.getElementsByTagName("parent").item(0).getTextContent());
        asset.setIdent(Integer.toString(identifier));
        identifier++;
        asset.setBrainhoneyPath(brainhoneyPath);
        asset.setSavePath(href);
        

        asset.setHref(href);

        items.add(asset);
    }

    public String getBrainhoneyPath() {
        return brainhoneyPath;
    }

    public void setBrainhoneyPath(String brainhoneyPath) {
        this.brainhoneyPath = brainhoneyPath;
    }

}
