/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.xmlTools;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;

/**
 *
 * @author hallm8
 */
public class XMLWriterTool {

    public XMLWriterTool() {
        try {
            
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder;
           documentBuilder = documentFactory.newDocumentBuilder(); 
            Document document = documentBuilder.newDocument();

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(XMLWriterTool.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
