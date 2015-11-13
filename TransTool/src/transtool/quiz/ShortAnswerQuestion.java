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
public class ShortAnswerQuestion extends BrainhoneyQuestion {

    public ShortAnswerQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, id, item, root);
        writeHeader();

        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Short Answer"));
        Element qtiDataField2 = (Element) rootItem.getElementsByTagName("qti_metadatafield").item(1);
        qtiDataField2.appendChild(fieldEntry2);

        Element presentation = (Element) rootItem.getElementsByTagName("presentation").item(0);
        Element flow = doc.createElement("flow");
        presentation.appendChild(flow);

        Element material = doc.createElement("material");
        Element str = doc.createElement("response_str");

        flow.appendChild(material);
        flow.appendChild(str);

        Element matText = doc.createElement("mattext");
        material.appendChild(matText);

        matText.setAttribute("texttype", "text/html");
        matText.appendChild(doc.createTextNode(brainhoney.getBody()));

        Element fib = doc.createElement("render_fib");
        Element response_label = doc.createElement("response_label");

        str.appendChild(fib);
        fib.appendChild(response_label);

        str.setAttribute("ident", randID + "_A" + questionNumber + "_ANS");
        str.setAttribute("rcardinality", "Single");

        fib.setAttribute("rows", "1");
        fib.setAttribute("columns", "40");
        fib.setAttribute("prompt", "Box");
        fib.setAttribute("fibtype", "String");

        response_label.setAttribute("ident", randID + "_A" + questionNumber + "_ANS");

        Element resprocessing = doc.createElement("resprocessing");
        Element outcomes = doc.createElement("outcomes");
        rootItem.appendChild(resprocessing);
        resprocessing.appendChild(outcomes);

        Element decvar = doc.createElement("decvar");
        outcomes.appendChild(decvar);
        decvar.setAttribute("vartype", "Integer");
        decvar.setAttribute("minvalue", "100");
        decvar.setAttribute("maxvalue", "100");
        decvar.setAttribute("varname", "Question_" + itemNumber);

        for (String rightAnswer : brainhoney.getRightAnswer()) {
            Element respcondition = doc.createElement("respcondition");
            resprocessing.appendChild(respcondition);

            Element conditionvar = doc.createElement("conditionvar");
            respcondition.appendChild(conditionvar);

            Element varequal = doc.createElement("varequal");

            varequal.setAttribute("respident", randID + "_A" + questionNumber + "_ANS");
            varequal.setAttribute("case", "no");

            varequal.setTextContent(rightAnswer);
            
            conditionvar.appendChild(varequal);

            Element setvar = doc.createElement("setvar");
            respcondition.appendChild(setvar);

            setvar.setAttribute("action", "Set");
            setvar.setTextContent("100.000000000");
        }

        itemNumber++;
        questionNumber++;
        feedbackNumber++;
    }

}
