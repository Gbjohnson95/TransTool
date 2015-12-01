/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import FixHTML.FixHTML;
import java.io.File;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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

    /**
     *
     */
    public WriteDropBox() {

    }

    /**
     *
     * @param dropBoxes
     * @param savePath
     * @param loadPath
     */
    public WriteDropBox(ArrayList<DropBox> dropBoxes, String savePath, String loadPath) {
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
                dropBox.setBrainhoneyPath(loadPath);
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
                
                

                FixHTML fix = new FixHTML();

                fix.setFilePath(dropBox.getBrainhoneyPath());
                fix.setItem(dropBox);
                fix.fix();
                dropBox.setBodyText(fix.getBodyText());
                
                if (!dropBox.getBodyText().isEmpty()){
                    Element instructions = doc.createElement("instructions");
                    Element text = doc.createElement("text");
                    folder.appendChild(instructions);
                    instructions.appendChild(text);
                    
                    instructions.setAttribute("text_type", "text/html");
                    text.setTextContent(dropBox.getBodyText());
                }

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File(savePath + "//dropbox_d2l.xml"));

            // Output to console for testing
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException ex) {
            Logger.getLogger(WriteDropBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
