/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author hallm8
 */
public class WriteDropBox {

    public WriteDropBox(){
        
    }
    public WriteDropBox(ArrayList<DropBox> dropBoxes, String savePath) {
        try {

            // Standard DOM procedures
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            // Header
            Element header = doc.createElement("dropbox");
            doc.appendChild(header);

            int i = 1;
            // For loop creating dropboxes.
            for (DropBox dropBox : dropBoxes) {
                Element folder = doc.createElement("folder");
                header.appendChild(folder);

                folder.setAttribute("name", dropBox.getName());
                folder.setAttribute("folder_type", "2");
                folder.setAttribute("sort_order", Integer.toString(i));
                i++;
                folder.setAttribute("out_of", dropBox.getWeight());
                folder.setAttribute("grade_item", dropBox.getGradeAssociation());
                folder.setAttribute("folder_is_restricted", "false");
                folder.setAttribute("files_per_submission", "0");
                folder.setAttribute("submissions", "2");
                folder.setAttribute("resource_code", "byui_produ-" + dropBox.getIdent());
            }
            
            
            // Writing the dropbox file into the manifest.
            

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(savePath + "//dropbox_d2l.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error!!! Unable to save file! Something wrong!!");
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(QuizItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(QuizItem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
