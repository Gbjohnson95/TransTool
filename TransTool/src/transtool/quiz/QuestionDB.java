/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import Manifest.Manifest;
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
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import transtool.questions.BrainhoneyContents;

/**
 * OTHER QUIZ Titled "Other Quiz" because Grant was working on another quiz
 * writer, instead going through the QTI standard as opposed to Brainhoney.
 *
 * VARIABLES: - Item Number - Each Brightspace import associates with an item
 * number - Question Number - Each Brightspace import also associates with a
 * question number. - Feedback Number - Each question number is associated with
 * a feedback ID as well. - ID number - Each question has a Question ID to link
 * back to quizzes by their ID. This number is pretty important.
 *
 * @author hallm8
 *
 */
public class QuestionDB {

    int itemNumber = 50000;
    int questionNumber = 50000;
    int feedbackNumber = 50000;
    int idNumber = 1;
    String toSave;

    /**
     * Constructor. Does pretty much everything for this program.
     *
     * @param quiz
     * @param savePath
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public QuestionDB(ArrayList<transtool.xmlTools.Quiz> quiz, String savePath) {
        try {
            // Save file path.
            toSave = savePath;

            // Standard DOM procedures
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("questestinterop");
            doc.appendChild(rootElement);

            // staff elements
            Element staff = doc.createElement("objectbank");
            rootElement.appendChild(staff);

            // set attribute to staff element
            Attr attr = doc.createAttribute("xmlns:d2l_2p0");
            attr.setValue("http://desire2learn.com/xsd/d2lcp_v2p0");
            staff.setAttributeNode(attr);

            // OK!! So here goes: The Brightspace XML document is convoluted at
            // best.  A huge portion of this XML is hard coded in, and you can
            // see that many, many variables are made.  However, the majority
            // of it is straightforward.  I will comment anything that is not.
            Attr attr2 = doc.createAttribute("ident");
            attr2.setValue("QLIB_1000");
            staff.setAttributeNode(attr2);

            // Each quiz question has been separated into quizzes.  So now, we
            // parse through each quiz and pull them out.  This has been done
            // so that we can separate them in the quiz bank.
            for (int l = 0; l < quiz.size(); l++) {
                //Section section = new Section(quiz.get(l), doc, itemNumber, idNumber, staff);
                //staff.appendChild(section.createSection());
                //itemNumber = section.getItemNumber();
               // questionNumber = section.getItemNumber();
                //feedbackNumber = section.getItemNumber();
                //idNumber = section.getIdNumber();
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(toSave + "\\questiondb.xml"));

            // Output to console for testing
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error!!! Unable to save file! Something wrong!!");
        } catch (TransformerException ex) {
            Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
