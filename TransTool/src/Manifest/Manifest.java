/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manifest;

import GradeItems.WriteGradeItems;
import Items.DropBox;
import Items.Item;
import Items.QuizItem;
import Items.WriteDropBox;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
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
import transtool.quiz.Section;
import transtool.ui.TransToolUI;
import transtool.xmlTools.GatherItems;
import transtool.xmlTools.XMLParser;

/**
 *
 * @author hallm8
 */
public class Manifest {

    private ArrayList<String> manifestList;
    private String savePath;
    private String brainhoneyPath;
    private ArrayList<String> fileNames = new ArrayList<>();
    private String title;

    public Manifest(String toSave, String brainhoneyPath) {
        title = "import";
        manifestList = new ArrayList<>();
        savePath = toSave;
        this.brainhoneyPath = brainhoneyPath;
        System.out.println(savePath);
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

            System.out.println("creating organizations, resources, etc...");
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

            System.out.println("Running the XML parser...");
            // Create the question database and write the xml
            XMLParser toParse = new XMLParser(brainhoneyPath);

            // Creating the question library in the XML
            resource.setAttribute("identifier", "res_question_library");
            resource.setAttribute("type", "webcontent");
            resource.setAttribute("d2l_2p0:material_type", "d2lquestionlibrary");
            resource.setAttribute("d2l_2p0:link_target", "");
            resource.setAttribute("href", "questiondb.xml");
            resource.setAttribute("title", "Question Library");
            resources.appendChild(resource);

            System.out.println("Creating the sections and writing them.");
            // Now creating the actual sections themselves.

            fileNames.add("questiondb.xml");

            System.out.println("Creating the content items themselves");
            // for loop, creating content items
            // while going through the loop, we are also pulling out the 
            // XML names to add to the file zipper
            //BrainhoneyItemParse bParse = new BrainhoneyItemParse(brainhoneyPath, quiz.getBrainhoneyContents());
            GatherItems gather = new GatherItems();
            gather.setNameOfXML(brainhoneyPath);
            gather.setSavePath(savePath);
            gather.populateItems();

            // pull each section off, so they can be sent to a reference later
            // for loop through each Item and create a reference
            // Now, let's create the grading categories and items in an XML
            // and link the file up.
            WriteGradeItems categories = new WriteGradeItems(savePath, gather.getGradeCategories());
            categories.setItems(gather.getItems());
            categories.writeToXML();

            gather.setItems(categories.getItems());
            gather.writeItems();

            ArrayList<DropBox> dBox = gather.getDropBoxes();
            ArrayList<Item> theItem = gather.getItems();

            System.out.println("Item size: " + gather.getItems().size() + " And DropBox Size: " + dBox.size());

            for (Item item1 : theItem) {
                for (DropBox dB : dBox) {
                    if (item1.getItemID().equals(dB.getItemID())) {

                        System.out.println("successfully matched a dropbox!");
                        dB = (DropBox) item1;
                    }
                }
            }

            WriteDropBox dropBox = new WriteDropBox(dBox, savePath);

            ArrayList<QuizItem> quizItems = gather.getQuizItem();
            ArrayList<Section> sections = new ArrayList<>();

            for (QuizItem quizItem : quizItems) {
                sections.add(quizItem.getSection());
            }

            QuestionDB questionDB = new QuestionDB(sections, savePath);

            // for loop through each item, adding them to the manifest.
            for (Item item : gather.getItems()) {
                if (!item.getItemType().equals("Dropbox")) {
                    fileNames.add(item.getHref());

                    Element qResource = doc.createElement("resource");
                    resources.appendChild(qResource);

                    qResource.setAttribute("title", item.getName());
                    qResource.setAttribute("href", item.getHref());
                    qResource.setAttribute("d2l_2p0:link_target", item.getLinkTarget());
                    qResource.setAttribute("d2l_2p0:material_type", item.getMaterialType());
                    qResource.setAttribute("type", "webcontent");
                    qResource.setAttribute("identifier", item.getIdent());
                }
            }

            System.out.println("writing the grade items.");

            System.out.println("number of items is: " + categories.getItems().size());
            fileNames.add("grades_d2l.xml");

            System.out.println("Writing XML.  This shouldn't fail, but you never know.");
            Element grades = doc.createElement("resource");
            grades.setAttribute("title", "Grade Items and Categories");
            grades.setAttribute("href", "grades_d2l.xml");
            grades.setAttribute("d2l_2p0:link_target", "");
            grades.setAttribute("d2l_2p0:material_type", "d2lgrades");
            grades.setAttribute("type", "webcontent");
            grades.setAttribute("identifier", "res_grades");
            resources.appendChild(grades);

            // writing the dropbox folder path into the manifest.
            Element dElement = doc.createElement("resource");
            dElement.setAttribute("title", "Dropbox Folders");
            dElement.setAttribute("href", "dropbox_d2l.xml");
            dElement.setAttribute("d2l_2p0:link_target", "");
            dElement.setAttribute("d2l_2p0:material_type", "d2ldropbox");
            dElement.setAttribute("type", "webcontent");
            dElement.setAttribute("identifier", "res_dropbox");
            resources.appendChild(dElement);

            System.out.println("Write contents to XML");
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(savePath) + "\\imsmanifest.xml");

            System.out.println("Saving manifest.");
            // Save the file to the opened file.
            transformer.transform(source, result);

            fileNames.add("imsmanifest.xml");
            fileNames.add("dropbox_d2l.xml");

            zipFiles();
        } catch (ParserConfigurationException pce) {
            System.out.println("Oops!  Error!!");
        } catch (TransformerException ex) {
            Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void zipFiles() {
        byte[] buffer = new byte[1024];
        try {

            //String[] sourceFiles = {savePath + "\\imsmanifest.xml", savePath + "\\questiondb.xml"};
            FileOutputStream fos = new FileOutputStream(savePath + "\\" + title + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);

            for (String fileName : fileNames) {

                System.out.println("zipping file: " + fileName);
                fileName = savePath + "\\" + fileName;
                File srcFile = new File(fileName);
                FileInputStream fis;
                fis = new FileInputStream(srcFile);
                zos.putNextEntry(new ZipEntry(srcFile.getName()));
                int length;
                while ((length = fis.read(buffer)) > 0) {
                    zos.write(buffer, 0, length);
                }
                zos.closeEntry();
                fis.close();

                boolean success = (new File(fileName)).delete();
            }
            zos.close();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(TransToolUI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File not found!");
        } catch (IOException ex) {
            Logger.getLogger(TransToolUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
