/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import Items.QuizItem;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import transtool.questions.BrainhoneyContents;
import transtool.xmlTools.Quiz;

/**
 *
 * @author hallm8
 */
public class Section {

    private Quiz quiz;
    private Document doc;
    private int itemNumber;
    private int questionNumber;
    private int feedbackNumber;
    private int idNumber;
    private Element rootItem;
    private QuizItem quizItem;

    public Section(int itemNumber, int idNumber, Element rootItem, QuizItem quizItem, Document doc) {
        this.itemNumber = itemNumber;
        this.idNumber = idNumber;
        this.rootItem = rootItem;
        this.quizItem = quizItem;
        questionNumber = itemNumber;
        feedbackNumber = itemNumber;
        this.doc = doc;
    }

    public Element createSection() {

        ArrayList<BrainhoneyContents> brainhoney = quizItem.getBrainhoney();
        Element section = doc.createElement("section");
        section.setAttribute("d2l_2p0:id", Integer.toString(idNumber));
        idNumber++;
        section.setAttribute("ident", "SECT_" + (idNumber + 1));
        section.setAttribute("title", quizItem.getName());
        Element sectionproc = doc.createElement("sectionproc_extension");
        Element displaySectionName = doc.createElement("d2l_2p0:display_section_name");
        displaySectionName.appendChild(doc.createTextNode("no"));
        Element displaySectionLine = doc.createElement("d2l_2p0:display_section_line");
        displaySectionLine.appendChild(doc.createTextNode("no"));
        Element typeDisplaySection = doc.createElement("d2l_2p0:type_display_section");
        typeDisplaySection.appendChild(doc.createTextNode("0"));

        rootItem.appendChild(section);
        section.appendChild(sectionproc);
        sectionproc.appendChild(displaySectionName);
        sectionproc.appendChild(displaySectionLine);
        sectionproc.appendChild(typeDisplaySection);

        // each Brainhoney class is actually a single question
        for (BrainhoneyContents brainhoneyContent : brainhoney) {
            if (brainhoneyContent.isDidFill()) {
                boolean didChoose = false;
                BrainhoneyQuestion question = new BrainhoneyQuestion();
                switch (brainhoneyContent.getInteractionType()) {
                    case "choice":
                        question = new MultipleChoice(brainhoneyContent, doc, idNumber, itemNumber, section);
                        didChoose = true;
                        break;

                    case "text":
                        question = new ShortAnswerQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                        didChoose = true;
                        break;

                    case "essay":
                        question = new LongAnswerQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                        didChoose = true;
                        break;

                    case "match":
                        question = new MatchingQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                        didChoose = true;
                        break;

                    case "order":
                        //question = new OrderQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                        break;

                    case "answer":
                        question = new MultiSelect(brainhoneyContent, doc, idNumber, itemNumber, section);
                        didChoose = true;
                        break;

                    case "custom":
                    case "composite":
                    default:
                        System.out.println("Custom/Composite Question selected!");
                        break;

                }

                if (didChoose == true) {
                    itemNumber = question.getItemNumber();
                    feedbackNumber = question.getItemNumber();
                    questionNumber = question.getItemNumber();
                    section.appendChild(question.getItem());
                }
            }
        }

        return section;
    }

    /**
     *
     * @return
     */
    public Quiz getQuiz() {
        return quiz;
    }

    /**
     *
     * @param quiz
     */
    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    /**
     *
     * @return
     */
    public Document getDoc() {
        return doc;
    }

    /**
     *
     * @param doc
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     *
     * @return
     */
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     *
     * @param itemNumber
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     *
     * @return
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     *
     * @param questionNumber
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     *
     * @return
     */
    public int getFeedbackNumber() {
        return feedbackNumber;
    }

    /**
     *
     * @param feedbackNumber
     */
    public void setFeedbackNumber(int feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    /**
     *
     * @return
     */
    public int getIdNumber() {
        return idNumber;
    }

    /**
     *
     * @param idNumber
     */
    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    /**
     *
     * @return
     */
    public Element getRootItem() {
        return rootItem;
    }

    /**
     *
     * @param rootItem
     */
    public void setRootItem(Element rootItem) {
        this.rootItem = rootItem;
    }

}
