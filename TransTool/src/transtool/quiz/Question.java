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

/**
 *
 * @author gbjohnson
 */
public class Question {

    xmlWriter presDoc;
    xmlWriter resDoc;
    int counter;
    String questionIdent;

    /**
     *
     * @param questionTitle
     * @param question
     * @param count
     * @throws XMLStreamException
     */
    public Question(String questionTitle, String question, int count) throws XMLStreamException {
        questionIdent = ident("QUE_");

        presDoc = new xmlWriter();
        resDoc = new xmlWriter();

        presDoc.newElement("item");
        presDoc.newElementAtribute("title", questionTitle);
        presDoc.newElementAtribute("ident", questionIdent);
        presDoc.newElement("presentation");
        presDoc.newElement("material");
        presDoc.newElement("mattext", question);
        presDoc.newElementAtribute("texttype", "test");
        presDoc.closeElement();
        presDoc.closeElement();
        presDoc.newElement("responce_lid", questionIdent + "_RL");
        presDoc.newElement("render_choice");
        // Write the question responses

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

    private String ident(String prefix) {
        counter++;
        String str = prefix + counter;
        return str;
    }

    public void newResponce(String responceText, boolean isTrue) {
        

    }

    public void printPres() {
        String string = presDoc.toStringAndClose();
        
    }
 
}
