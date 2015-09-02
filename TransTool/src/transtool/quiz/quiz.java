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
    private int count = 1000;
    private int responseCounter = 0;
    private String questionIdent;

    public quiz() throws XMLStreamException {
        doc = new xmlWriter();
        newAssesment("demo");
        newSection("main");
        newQuestion("Question1", "Question Text");
        addQuestionResponce("ResponceA", false);
        addQuestionResponce("ResponceB", true);
        endQuestion();
        endSection();
        endAssesment();
        
    }
    
    public String print() {
        return doc.toStringAndClose();
    }

    private String ident(String prefix, String suffix) {
        count += 1;
        String str = prefix + count + suffix;
        
        return str;
    }

    private String ident(String prefix) {
        count += 1;
        String str = prefix + count;
        return str;
    }

    private void newAssesment(String title) throws XMLStreamException {
        doc.newElement("assesment"); // closed
        doc.newElementAtribute("title", title);
        doc.newElementAtribute("ident", ident("a"));
    }

    private void endAssesment() throws XMLStreamException {
        doc.closeElement(); // Closes assesment
        

    }

    private void newSection(String title) throws XMLStreamException {
        doc.newElement("section"); // closed
        doc.newElementAtribute("title", title);
        doc.newElementAtribute("ident", ident("s"));
    }

    private void endSection() throws XMLStreamException {
        doc.closeElement(); // Closes section

    }

    private void newQuestion(String questionTitle, String question) throws XMLStreamException {
        // Generates a new question item.
        questionIdent = ident("QUE_");
        doc.newElement("item"); // closed
        doc.newElementAtribute("title", questionTitle);
        doc.newElementAtribute("ident", questionIdent);
        doc.newElement("presentation"); //closed
        
        // Actual question material.
        doc.newElement("material"); //closed
        doc.newElement("mattext", question); //closed
        //doc.newElementAtribute("Texttype", "test");
        doc.closeElement(); // Closes mattext
        doc.closeElement(); // Closes material
        
        // Opening code for the responces.
        doc.newElement("responce_lid", questionIdent + "_RL"); //closed
        doc.newElement("render_choice"); //closed
        
        // Resets the responseCounter to 1.
    }

    private void endQuestion() throws XMLStreamException {
        doc.closeElement(); // Closes render_choice
        doc.closeElement(); // Closes response_lid
        
        doc.closeElement(); // Closes presentation
        doc.closeElement(); // Closes item

    }

    private void addQuestionResponce(String response, boolean isAnswer) throws XMLStreamException {
        doc.newElement("responce_lable"); //closed
        responseCounter += 1;
        doc.newElementAtribute("ident", questionIdent + "_A" + responseCounter);
        
        doc.newElement("material"); // closed
        doc.newElement("mattext", response); //closed
        //doc.newElementAtribute("texttype", "txt//html");
        
        doc.closeElement(); // Closes mattext
        doc.closeElement(); // Closes material
        doc.closeElement(); // Closes responce_lable
    }

    //private void addFeedback(String feedback) {
    //}
}
