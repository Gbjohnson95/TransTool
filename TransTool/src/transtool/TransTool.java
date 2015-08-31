
package transtool;

import javax.xml.stream.XMLStreamException;
import transtool.xmlTools.*;

import transtool.xmlTools.XMLParser;


public class TransTool {

    /**
     * @param args the command line arguments
     * @throws javax.xml.stream.XMLStreamException
     */
    public static void main(String[] args) {
        // TODO code application logic here
        XMLParser parse = new XMLParser("./brainhoneymanifest.xml");
    }
    
}
