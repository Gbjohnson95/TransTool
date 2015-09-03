
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




}
