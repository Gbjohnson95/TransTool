package transtool;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import transtool.quiz.Quiz2;

//import transtool.xmlTools.XMLParser;
public class TransTool {

    public static void main(String[] args) throws XMLStreamException, TransformerException {

        Quiz2 test = new Quiz2();
        
        test.newQuestion("Question1", "<p>How fast is a swallow?</p>");
        test.newResponse("African?", "50");
        test.newResponse("European?", "50");
        
        test.print();

        //XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
    }

}
