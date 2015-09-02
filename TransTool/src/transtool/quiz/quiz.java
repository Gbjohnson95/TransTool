/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import javax.xml.stream.XMLStreamException;
import transtool.xmlTools.*;

/**
 * Generates a Quiz document.
 *
 * @author gbjohnson
 */
public class quiz {

    xmlWriter doc;
    xmlWriter resDoc;
    private int count = 1000;
    private int responseCounter = 0;
    private String questionIdent;

    /**
     * Currently it generates a generic quiz, it does not generate the grade
     * values and stuff.
     *
     *
     * @throws XMLStreamException
     */
    public quiz() throws XMLStreamException {
        doc = new xmlWriter();
        resDoc = new xmlWriter();
        newAssesment("demo");
        newSection("main");
        newQuestion("Question1", "Question Text");
        addQuestionResponce("ResponceA", false);
        addQuestionResponce("ResponceB", true);
        endQuestion();
        endSection();
        endAssesment();
    }

    /**
     * Invokes the toStringAndClose of xmlWriter.
     *
     * @return
     */
    public String print() {
        String string = doc.toStringAndClose();
        string = string.replaceAll("</presentation>", "</presentation>" + resDoc.toStringAndClose());
        return string;
    }

    private String ident(String prefix, String suffix) {
        count++;
        String str = prefix + count + suffix;
        return str;
    }

    private String ident(String prefix) {
        count++;
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
        questionIdent = ident("QUE_");
        doc.newElement("item");
        doc.newElementAtribute("title", questionTitle);
        doc.newElementAtribute("ident", questionIdent);
        doc.newElement("presentation");
        doc.newElement("material");
        doc.newElement("mattext", question);
        //doc.newElementAtribute("Texttype", "test");
        doc.closeElement();
        doc.closeElement();
        doc.newElement("responce_lid", questionIdent + "_RL");
        doc.newElement("render_choice");
        resDoc.newElement("resprocessing");
        resDoc.newElement("outcomes");
        resDoc.newEmptyElement("decvar");
        resDoc.newElementAtribute("vartype", "Integer");
        resDoc.newElementAtribute("defaultval", "0");
        resDoc.newElementAtribute("varname", "que_score");
        resDoc.newElementAtribute("maxvalue", "100");
        resDoc.newElementAtribute("minvalue", "0");
        resDoc.closeElement();

    }

    private void endQuestion() throws XMLStreamException {
        doc.closeElement(); // Closes render_choice
        doc.closeElement(); // Closes response_lid
        doc.closeElement(); // Closes presentation
        //String resDocText = resDoc.toStringAndClose();
        //doc.writeCharacters(resDocText);

        resDoc.closeElement(); //Closes resprocessing

        doc.closeElement(); // Closes item

    }

    private void addQuestionResponce(String response, boolean isAnswer) throws XMLStreamException {
        doc.newElement("responce_lable"); //closed
        responseCounter += 1;
        String questionResponceID = questionIdent + "_A" + responseCounter;
        doc.newElementAtribute("ident", questionResponceID);
        doc.newElement("material"); // closed
        doc.newElement("mattext", response); //closed
        //doc.newElementAtribute("texttype", "txt//html");
        doc.closeElement(); // Closes mattext
        doc.closeElement(); // Closes material
        doc.closeElement(); // Closes responce_lable

        resDoc.newElement("respcondition");
        resDoc.newElement("conitionvar");
        resDoc.newElement("varequal", questionResponceID);
        resDoc.closeElement(); // CLoses varequal
        resDoc.closeElement(); // Closes conditionvar

        String queScore;
        if (isAnswer) {
            queScore = "100.00";
        } else {
            queScore = "-100.00";
        }

        resDoc.newElement("setvar", queScore);
        //resDoc.newElementAtribute("varname", "que_score");
        //resDoc.newElementAtribute("action", "Add");
        resDoc.closeElement(); //closes setvar
        resDoc.closeElement(); // Closes Responce
    }
}
