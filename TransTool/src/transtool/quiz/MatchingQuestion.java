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
public class MatchingQuestion extends BrainhoneyQuestion{
    
    public MatchingQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, id, item, root);
        writeHeader();

        //Field entries are very question specific.  Well... for the question type.
        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Matching"));
        Element qtiDataField2 = (Element) rootItem.getElementsByTagName("qti_metadatafield").item(1);
        qtiDataField2.appendChild(fieldEntry2);
        
        
        
        
        
        
    }
    
}
