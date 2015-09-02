package transtool;

import java.io.IOException;
import javax.xml.stream.XMLStreamException;
import transtool.quiz.quiz;
import transtool.xmlTools.*;

//import transtool.xmlTools.XMLParser;
public class TransTool {

    /**
     * @param args the command line arguments
     * @throws javax.xml.stream.XMLStreamException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws XMLStreamException, IOException {
        quiz test = new quiz();
        System.out.print(test.print());
    }

}
