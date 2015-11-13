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
public class LongAnswerQuestion extends BrainhoneyQuestion{
   
    public LongAnswerQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, id, item, root);
                writeHeader();

        Element presentation = (Element) rootItem.getElementsByTagName("presentation").item(0);
        Element flow = doc.createElement("flow");
        presentation.appendChild(flow);
        
        Element material = doc.createElement("material");
        Element extension = doc.createElement("response_extension");
        Element str  = doc.createElement("response_str");
        
        flow.appendChild(material);
        flow.appendChild(extension);
        flow.appendChild(str);
        
        Element matText = doc.createElement("mattext");
        material.appendChild(matText);
        
        matText.setAttribute("texttype","text/html");
        matText.appendChild(doc.createTextNode(brainhoney.getBody()));
        
        Element hasSigned = doc.createElement("questiondb.xml");
        Element htmlEditor = doc.createElement("d2l_2p0:has_htmleditor");
        
        hasSigned.setTextContent("no");
        htmlEditor.setTextContent("no");
        
        extension.appendChild(hasSigned);
        extension.appendChild(htmlEditor);
        
        Element fib = doc.createElement("render_fib");
    }
   
}
