/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import org.w3c.dom.Document;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class LongAnswerQuestion {
    private BrainhoneyContents brainhoney;
    private Document doc;
    int itemNumber;
    int questionNumber;
    int feedbackNumber;
    int idNumber;
    
    public LongAnswerQuestion(BrainhoneyContents brain, Document document, int id, int item){
    brainhoney = brain;    
    doc = document;
    idNumber = id;
    questionNumber = item;
    feedbackNumber = item;
    itemNumber     = item;
    }
    
}
