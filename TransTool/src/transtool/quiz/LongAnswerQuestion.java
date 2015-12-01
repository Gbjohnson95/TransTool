/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class LongAnswerQuestion extends BrainhoneyQuestion {

    /**
     * 
     * @param brain
     * @param document
     * @param id
     * @param item
     * @param root 
     */
    public LongAnswerQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, brain.getQuestionID(), item, root);
        writeHeader();
        
        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Long Answer"));
        Element qtiDataField2 = (Element) rootItem.getElementsByTagName("qti_metadatafield").item(1);
        qtiDataField2.appendChild(fieldEntry2);

        Element presentation = (Element) rootItem.getElementsByTagName("presentation").item(0);
        Element flow = doc.createElement("flow");
        presentation.appendChild(flow);

        Element material = doc.createElement("material");
        Element extension = doc.createElement("response_extension");
        Element str = doc.createElement("response_str");

        flow.appendChild(material);
        flow.appendChild(extension);
        flow.appendChild(str);

        Element matText = doc.createElement("mattext");
        material.appendChild(matText);

        matText.setAttribute("texttype", "text/html");
        matText.appendChild(doc.createTextNode(brainhoney.getBody()));

        Element hasSigned = doc.createElement("d2l_2p0:has_signed_comments");
        Element htmlEditor = doc.createElement("d2l_2p0:has_htmleditor");

        hasSigned.setTextContent("no");
        htmlEditor.setTextContent("no");

        extension.appendChild(hasSigned);
        extension.appendChild(htmlEditor);

        Element fib = doc.createElement("render_fib");
        Element response_label = doc.createElement("response_label");
        Element material2 = doc.createElement("material");
        Element matText2 = doc.createElement("mattext");

        str.appendChild(fib);
        fib.appendChild(response_label);
        response_label.appendChild(material2);
        material2.appendChild(matText2);

        str.setAttribute("ident", brain.getQuestionID() + "_STR");
        str.setAttribute("cardinality", "multiple");

        fib.setAttribute("rows", "5");
        fib.setAttribute("columns", "80");
        fib.setAttribute("prompt", "Box");
        fib.setAttribute("fibtype", "String");

        response_label.setAttribute("ident", brain.getQuestionID() + "_LA");

        matText2.setAttribute("texttype", "text/plain");

        itemNumber++;
        questionNumber++;
        feedbackNumber++;
    }

}
