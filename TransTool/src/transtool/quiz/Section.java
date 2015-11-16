/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

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

    public Section(Quiz quiz, Document doc, int itemNumber, int idNumber, Element rootItem) {
        this.quiz = quiz;
        this.doc = doc;
        this.itemNumber = itemNumber;
        questionNumber = itemNumber;
        feedbackNumber = itemNumber;
        this.idNumber = idNumber;
        this.rootItem = rootItem;
    }



    Element createSection() {
        
        ArrayList<BrainhoneyContents> brainhoney = quiz.getBrainhoney();
        Element section = doc.createElement("section");
        section.setAttribute("d2l_2p0:id", Integer.toString(idNumber));
        idNumber++;
        section.setAttribute("ident", "SECT_" + (idNumber + 1));
        section.setAttribute("title", quiz.getQuizName());
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

            switch (brainhoneyContent.getInteractionType()) {
                case "choice":
                    MultipleChoice multipleChoice = new MultipleChoice(brainhoneyContent, doc, idNumber, itemNumber, section);
                    idNumber = multipleChoice.getIdNumber();
                    itemNumber = multipleChoice.getItemNumber();
                    feedbackNumber = multipleChoice.getItemNumber();
                    questionNumber = multipleChoice.getItemNumber();
                    section.appendChild(multipleChoice.getItem());
                    break;
                case "text":

                    ShortAnswerQuestion shortAnswer = new ShortAnswerQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                    idNumber = shortAnswer.getIdNumber();
                    itemNumber = shortAnswer.getItemNumber();
                    feedbackNumber = shortAnswer.getItemNumber();
                    questionNumber = shortAnswer.getItemNumber();
                    section.appendChild(shortAnswer.getItem());

                    break;
                case "essay":
                    LongAnswerQuestion longAnswer = new LongAnswerQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                    idNumber = longAnswer.getIdNumber();
                    itemNumber = longAnswer.getItemNumber();
                    feedbackNumber = longAnswer.getItemNumber();
                    questionNumber = longAnswer.getItemNumber();
                    section.appendChild(longAnswer.getItem());

                    break;
                case "match":
                    /*
                            MatchingQuestion matchingQuestion = new MatchingQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = matchingQuestion.getIdNumber();
                            itemNumber = matchingQuestion.getItemNumber();
                            feedbackNumber = matchingQuestion.getItemNumber();
                            questionNumber = matchingQuestion.getItemNumber();
                            section.appendChild(matchingQuestion.getItem());
                     */
                    break;
                case "order":
                    /*
                            OrderQuestion orderQuestion = new OrderQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = orderQuestion.getIdNumber();
                            itemNumber = orderQuestion.getItemNumber();
                            feedbackNumber = orderQuestion.getItemNumber();
                            questionNumber = orderQuestion.getItemNumber();
                            section.appendChild(orderQuestion.getItem());
                     */
                    break;

                case "answer":

                    System.out.println("M-S Size: " + brainhoneyContent.getRightAnswer().size());
            for (String rightAnswer : brainhoneyContent.getRightAnswer()) {
                System.out.println(rightAnswer);
            }
                    MultiSelect multiSelect = new MultiSelect(brainhoneyContent, doc, idNumber, itemNumber, section);
                    
                    idNumber = multiSelect.getIdNumber();
                    itemNumber = multiSelect.getItemNumber();
                    feedbackNumber = multiSelect.getItemNumber();
                    questionNumber = multiSelect.getItemNumber();
                    section.appendChild(multiSelect.getItem());

                    break;

                case "custom":
                case "composite":
                    break;
            }
        }

        return section;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Document getDoc() {
        return doc;
    }

    public void setDoc(Document doc) {
        this.doc = doc;
    }

    public int getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getFeedbackNumber() {
        return feedbackNumber;
    }

    public void setFeedbackNumber(int feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public Element getRootItem() {
        return rootItem;
    }

    public void setRootItem(Element rootItem) {
        this.rootItem = rootItem;
    }



    
    
}
