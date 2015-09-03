/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import javax.xml.stream.XMLStreamException;
import transtool.xmlTools.*;

/**
 *
 * @author gbjohnson
 */
public class quiz {

    xmlWriter doc;
    private int count = 0;
    private int responseCounter = 1;
    private String questionIdent;

    public quiz() throws XMLStreamException {
        newAssesment("demo");
        newSection("Main");
        newQuestion("Question1", "Question Text");
        addQuestionResponce("ResponceA", false);
        addQuestionResponce("ResponceB", true);
        endQuestion();
        endSection();
        endAssesment();
    }

    private String ident(String prefix, String suffix) {
        String str = prefix + count + suffix;
        return str;
    }

    private String ident(String prefix) {
        String str = prefix + count;
        return str;
    }

    private void newAssesment(String title) throws XMLStreamException {
        doc.newElement("assesment");
        doc.newElementAtribute("title", title);
        doc.newElementAtribute("ident", ident("A"));
    }

    private void endAssesment() {

    }

    private void newSection(String title) throws XMLStreamException {
        doc.newElement("section");
        doc.newElementAtribute("title", title);
        doc.newElementAtribute("ident", ident("S"));
    }

    private void endSection() {

    }

    private void newQuestion(String questionTitle, String question) throws XMLStreamException {
        // Generates a new question item.
        questionIdent = ident("QUE_");
        doc.newElement("item");
        doc.newElementAtribute("title", questionTitle);
        doc.newElement("presentation");
        
        // Actual question material.
        doc.newElement("material");
        doc.newElement("mattext", question);
        doc.newElementAtribute("texttype", "text//html");
        doc.closeElement(); // Closes mattext
        doc.closeElement(); // Closes material
        
        // Opening code for the responces.
        doc.newElement("responce_lid", questionIdent + "_RL");
        doc.newElement("render_choice");
        
        // Resets the responseCounter to 1.
    }

    private void endQuestion() throws XMLStreamException {
        doc.closeElement(); // Closes render_choice
        doc.closeElement(); // Closes response_lid

    }

    private void addQuestionResponce(String response, boolean isAnswer) throws XMLStreamException {
        doc.newElement("responce_lable");
        doc.newElementAtribute("ident", questionIdent + "_A" + responseCounter);
        
        doc.newElement("material");
        doc.newElement("mattext", response);
        doc.newElementAtribute("texttype", "txt//html");
        
        doc.closeElement(); // Closes mattext
        doc.closeElement(); // Closes material
        doc.closeElement(); // Closes responce_lable
    }

    //private void addFeedback(String feedback) {
    //}
}
