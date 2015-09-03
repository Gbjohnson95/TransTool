package transtool;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.xml.stream.XMLStreamException;
import transtool.quiz.quiz;
import transtool.xmlTools.*;
import transtool.ui.Window;

//import transtool.xmlTools.XMLParser;
public class TransTool {

    /**
     * @param args the command line arguments
     * @throws javax.xml.stream.XMLStreamException
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws XMLStreamException, IOException {
        // Test Quiz Maker
        

        quiz test = new quiz();
        System.out.print(test.print());

        // Test XML Parser
        // Test UI, BAD LOOP SOMEWHERE, RUN AT YOUR OWN RISK
        //Window window = new Window();
    }

}
