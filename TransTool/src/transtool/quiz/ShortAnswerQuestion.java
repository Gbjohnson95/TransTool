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

        //Field entries are very question specific.  Well... for the question type.
        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Short Answer"));
        Element qtiDataField2 = (Element) root.getElementsByTagName("qtimetadata").item(0);
        qtiDataField2.appendChild(fieldEntry2);

        Element extension = doc.createElement("response_extension");
        Element lid = doc.createElement("response_lid");

        Element responseStr = doc.createElement("response_str");
        responseStr.setAttribute("ident", randID + "_A" + questionNumber + "_STR");
        responseStr.setAttribute("rcardinality", "Single");

        Element renderFib = doc.createElement("render_fib");
        renderFib.setAttribute("rows", "3");
        renderFib.setAttribute("columns", "60");
        renderFib.setAttribute("prompt", "Box");
        renderFib.setAttribute("fibtype", "String");

        Element responseLabel = doc.createElement("response_label");
        responseLabel.setAttribute("ident", randID + "_A" + questionNumber + "_ANS");

        //flow.appendChild(responseStr);
        responseStr.appendChild(renderFib);
        renderFib.appendChild(responseLabel);
        
        

    }

}
