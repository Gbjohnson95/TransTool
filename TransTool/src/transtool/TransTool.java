
package transtool;

import javax.xml.stream.XMLStreamException;
import transtool.xmlTools.*;

import transtool.xmlTools.XMLParser;


public class TransTool {

    /**
     * @param args the command line arguments
     * @throws javax.xml.stream.XMLStreamException
     */
<<<<<<< HEAD
    public static void main(String[] args) throws XMLStreamException {
        xmlWriter question = new xmlWriter();
        
        question.newElement("BaseElement");
        question.closeElement();
        
        question.newElement("BaseElement2", "Some Item");
        question.closeElement();
        
        String test[][] = {{"id","1"},{"grade","95"}};
        
        question.newElement("baseElement3", test);
        question.closeElement();
        
        String str = question.toStringAndClose();
        
        System.out.print(str);
        
=======
    public static void main(String[] args) {
        // TODO code application logic here
        XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
>>>>>>> origin/master
    }
    
}
