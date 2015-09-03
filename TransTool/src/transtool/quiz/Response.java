/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import javax.xml.stream.XMLStreamException;
import transtool.xmlTools.xmlWriter;

/**
 *
 * @author gbjohnson
 */
public class Response {
    
    xmlWriter doc;
    xmlWriter resDoc;
    public Response() {
        
    }
    private void addQuestionResponce(String response, boolean isAnswer, String questionIdent) throws XMLStreamException {
       doc.newElement("responce_lable"); //closed
        int responseCounter = 1;
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
