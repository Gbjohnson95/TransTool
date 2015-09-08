
package transtool;


import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import javax.xml.stream.XMLStreamException;
import transtool.quiz.quiz;

import transtool.xmlTools.*;
import transtool.quiz.*;


//import transtool.xmlTools.XMLParser;


public class TransTool {

    /**
     * @param args the command line arguments
     * @throws javax.xml.stream.XMLStreamException
     */


    public static void main(String[] args) throws XMLStreamException {
        
        quiz test = new quiz();
        System.out.print(test.print());
        
        
        
        //XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
    }




}
