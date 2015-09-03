
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


    public static void main(String[] args) {
        // TODO code application logic here
        XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
    }

    /*
    public static void main(String[] args) throws XMLStreamException, IOException {
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
    }

    //}
           }
       
     */


}
