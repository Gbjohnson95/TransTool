
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


    public static void main(String[] args) {
        // TODO code application logic here
        XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
    }

    /*
    public static void main(String[] args) throws XMLStreamException, IOException {
<<<<<<< HEAD
        // Test Quiz Maker
        

        quiz test = new quiz();
        System.out.print(test.print());

        // Test XML Parser
        // Test UI, BAD LOOP SOMEWHERE, RUN AT YOUR OWN RISK
        //Window window = new Window();
=======
        xmlWriter question = new xmlWriter();

        question.newElement("BaseElement");
        question.newElement("People");
        question.newElement("Jane");

        question.closeElement();
        question.closeElement();

        question.newElement("Jack", "Some Item");
        question.closeElement();

        question.newElement("Stan");
        question.newElementAtribute("Grade", "A+");
        question.closeElement();
        question.closeElement();

        String str = question.toStringAndClose();

        System.out.print(str);

        xmlDocument doc = new xmlDocument();

        doc.addElements(str);
        //doc.filePath();
        doc.fileName("test.xml");
        doc.writeToFile();
        System.out.println("file shoudl have been made");

        

           // public static void main(String[] args) {
        // TODO code application logic here
        //XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
        //}
>>>>>>> origin/master
    }

    //}
           }
       
     */


}
