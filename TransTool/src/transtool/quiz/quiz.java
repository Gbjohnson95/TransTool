/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import transtool.xmlTools.*;
import transtool.quiz.Question;

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

        newQuestion("Question1", "text");
        addQuestionResponce("ResponceA", false);
        addQuestionResponce("ResponceB", true);
        endQuestion();

        newQuestion("Question2", "text");
        addQuestionResponce("ResponceA", false);
        addQuestionResponce("ResponceB", true);
        addQuestionResponce("ResponceC", false);
        endQuestion();

        endSection();
        endAssesment();
        
        Question question1 = new Question("Question1", "Question Text", 1);
    }

    /**
     * Invokes the toStringAndClose of xmlWriter.
     *
     * @return
     */
    public String print() {
        String string = doc.toStringAndClose();
        string = string.replaceAll("</presentation>", "</presentation>" + resDoc.toStringAndClose());
        string = string.replaceAll("<mattext>", "<mattext texttype=\"text/html\">");
        string = prettyFormat(string, 3);
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
        //doc.newElementText();
        //doc.newElementAtribute("texttype", "test");
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
        responseCounter = 0;
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

    private static String prettyFormat(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (IllegalArgumentException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
