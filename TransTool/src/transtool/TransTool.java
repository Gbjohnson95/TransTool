package transtool;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import transtool.quiz.OtherQuiz;
import transtool.quiz.Quiz;

//import transtool.xmlTools.XMLParser;
public class TransTool {

    public static void main(String[] args) throws XMLStreamException, TransformerException {

        Quiz test = new Quiz();

        test.newQuestion("Question1", "<p>How fast is a swallow?</p>");
        test.newResponse("African?", "50");
        test.newFeedback("Well I dont know?!");
        test.newResponse("European?", "50");

        test.print();
        
        

        //XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
    }

}
