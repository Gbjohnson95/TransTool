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
public class OrderQuestion extends BrainhoneyQuestion{
    
    public OrderQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, id, item, root);
    }
    
}
