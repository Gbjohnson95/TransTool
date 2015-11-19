/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manifest;

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
import transtool.quiz.QuestionDB;
import transtool.xmlTools.BrainhoneyItemParse;
import transtool.xmlTools.XMLParser;

/**
 *
 * @author hallm8
 */
public class Manifest {

    private ArrayList<String> manifestList;
    private String savePath;

    public Manifest(String toSave) {
        manifestList = new ArrayList<>();
        savePath = toSave;
    }

    public void buildManifest() {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("manifest");
            doc.appendChild(rootElement);
            rootElement.setAttribute("xmlns:d2l_2p0", "http://desire2learn.com/xsd/d2lcp_v2p0");
            rootElement.setAttribute("xmlns:scorm_1p2", "http://www.adlnet.org/xsd/adlcp_rootv1p2");
            rootElement.setAttribute("xmlns", "http://www.imsglobal.org/xsd/imscp_v1p1");

            Element resources = doc.createElement("resources");

            // create organizations, resources, append them and attach
            // standard attributes
            Element organizations = doc.createElement("organizations");
            Element organization = doc.createElement("organization");
            Element resource = doc.createElement("resource");
            rootElement.appendChild(resources);
            rootElement.appendChild(organizations);
            rootElement.appendChild(organization);
            organizations.setAttribute("default", "d2l_orgs");
            organization.setAttribute("identifier", "d2l_org");

            // Create the question database and write the xml
            XMLParser toParse = new XMLParser(savePath);

            // Create a list of Items
            BrainhoneyItemParse quiz = new BrainhoneyItemParse(savePath, toParse.getBrainhoney());
            
            
            // for loop, creating content items
            
            
            
            // Creating the question library
            resource.setAttribute("identifier", "res_question_library");
            resource.setAttribute("type", "webcontent");
            resource.setAttribute("d2l_2p0:material_type", "d2lquestionlibrary");
            resource.setAttribute("d2l_2p0:link_target", "");
            resource.setAttribute("href", "questiondb.xml");
            resource.setAttribute("title", "Question Library");
            resources.appendChild(resource);

            // pull each section off, so they can be sent to a reference later
            // for loop through each Item and create a reference


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(savePath) + "\\imsmanifest.xml");

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            System.out.println("Oops!  Error!!");
        } catch (TransformerException ex) {
            Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
