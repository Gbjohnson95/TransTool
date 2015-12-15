/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Manifest;

import FixHTML.FixHTML;
import GradeItems.WriteGrades;
import Items.DropBox;
import Items.Folder;
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
import javax.swing.JTextPane;
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
import org.w3c.dom.NodeList;
import transtool.quiz.QuestionDB;
import transtool.quiz.Section;
import transtool.ui.TransToolUI;
import transtool.xmlTools.GatherItems;

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
    private String jPanelText;
    private JTextPane eventLogPanel;
    private String loadPath;
    private ArrayList<Element> contentPage;
    
    public Manifest(String toSave, String brainhoneyPath) {
        title = "import";
        savePath = toSave;
        manifestList = new ArrayList<>();
        this.brainhoneyPath = brainhoneyPath;
        System.out.println(savePath);
        contentPage = new ArrayList<>();
    }

    /**
     *
     */
    public void buildManifest() {
        try {
            
            File file = new File(savePath + "\\Course Files");
            if (!file.exists()) {
                if (file.mkdir()) {
                    System.out.println("Directory is created!");
                } else {
                    System.out.println("Error!  Directory already exists!!");
                }
            }
            
            jPanelText = "Creating new manifest...";
            
            eventLogPanel.setText(jPanelText);
            jPanelText += "Testing...";
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
            organizations.appendChild(organization);
            organizations.setAttribute("default", "d2l_orgs");
            organization.setAttribute("identifier", "d2l_org");

            // Creating the question library in the XML
            resource.setAttribute("identifier", "res_question_library");
            resource.setAttribute("type", "webcontent");
            resource.setAttribute("d2l_2p0:material_type", "d2lquestionlibrary");
            resource.setAttribute("d2l_2p0:link_target", "");
            resource.setAttribute("href", "questiondb.xml");
            resource.setAttribute("title", "Question Library");
            resources.appendChild(resource);

            // Now creating the actual sections themselves.
            fileNames.add("questiondb.xml");

            // for loop, creating content items
            // while going through the loop, we are also pulling out the 
            // XML names to add to the file zipper
            //BrainhoneyItemParse bParse = new BrainhoneyItemParse(brainhoneyPath, quiz.getBrainhoneyContents());
            GatherItems gather = new GatherItems();
            gather.setBrainhoneyPath(brainhoneyPath);
            gather.setSavePath(savePath);
            gather.populateItems();
            title = gather.getCourseTitle();
            
            jPanelText += "\nWriting grade items...";
            eventLogPanel.setText(jPanelText);
            // pull each section off, so they can be sent to a reference later
            // for loop through each Item and create a reference
            // Now, let's create the grading categories and items in an XML
            // and link the file up.
            WriteGrades categories = new WriteGrades(savePath, gather.getGradeCategories());
            categories.setItems(gather.getItems());
            categories.writeToXML();
            
            gather.setItems(categories.getItems());
            gather.writeItems();
            
            ArrayList<DropBox> dBox = gather.getDropBoxes();
            ArrayList<Item> theItem = gather.getItems();
            ArrayList<Folder> folders = gather.getFolders();
            
            System.out.println("Item size: " + gather.getItems().size() + " And DropBox Size: " + dBox.size());
            
            jPanelText += "\nSetting up DropBoxes...";
            eventLogPanel.setText(jPanelText);
            for (Item item1 : theItem) {
                for (DropBox dB : dBox) {
                    if (item1.getItemID().equals(dB.getItemID())) {
                        dB = (DropBox) item1;
                        break;
                    }
                }
                for (Folder folder : folders) {
                    if (item1.getParent().equals(folder.getFolderID())) {
                        ArrayList<Item> cItems = folder.getItems();
                        cItems.add(item1);
                        folder.setItems(cItems);
                    }
                }
            }
            
            WriteDropBox dropBox = new WriteDropBox(dBox, savePath, brainhoneyPath);
            
            ArrayList<QuizItem> quizItems = gather.getQuizItem();
            ArrayList<Section> sections = new ArrayList<>();
            
            jPanelText += "\nAdding sections to quizzes...";
            eventLogPanel.setText(jPanelText);
            for (QuizItem quizItem : quizItems) {
                sections.add(quizItem.getSection());
            }
            
            QuestionDB questionDB = new QuestionDB(sections, savePath);
            
            jPanelText += "\nCreating the resources in the manifest...";
            eventLogPanel.setText(jPanelText);
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
            
            int contentOrder = 1;
            int resContent = 200000;
            for (Folder folder : folders) {
                Element fItem = doc.createElement("item");
                Element fResource = doc.createElement("resource");
                
                if (folder.getParent().equals("DEFAULT")) {
                    organization.appendChild(fItem);
                } else {
                    NodeList folderGroup = doc.getElementsByTagName("item");
                    for (int i = 0; i < folderGroup.getLength(); i++) {
                        Element thisItem = (Element) folderGroup.item(i);
                        if (thisItem.getAttribute("identifier").equals(folder.getParent())) {
                            thisItem.appendChild(fItem);
                            break;
                        }
                    }
                    
                }
                
                resources.appendChild(fResource);
                
                fItem.setAttribute("identifier", folder.getFolderID());
                fItem.setAttribute("identifierref", "RES_CONTENT_" + folder.getFolderID());
                fItem.setAttribute("d2l_2p0:id", Integer.toString(contentOrder));
                contentOrder++;
                fItem.setAttribute("d2l_2p0:resource_code", "byui_produ-" + folder.getFolderID());
                fItem.setAttribute("description", "");
                fItem.setAttribute("completion_type", "1");
                fItem.setAttribute("resource_type_key", "");
                Element cTitle = doc.createElement("title");
                fItem.appendChild(cTitle);
                cTitle.setTextContent(folder.getFolderName());
                
                fResource.setAttribute("identifier", "RES_CONTENT_" + folder.getFolderID());
                fResource.setAttribute("type", "webcontent");
                fResource.setAttribute("d2l_2p0:material_type", "contentmodule");
                fResource.setAttribute("d2l_2p0:link_target", "");
                fResource.setAttribute("href", "");
                fResource.setAttribute("title", "");
                
                for (Item item : folder.getItems()) {
                    Element res_Item = doc.createElement("item");
                    fItem.appendChild(res_Item);
                    Element iTitle = doc.createElement("title");
                    
                    res_Item.setAttribute("identifier", item.getIdent());
                    res_Item.setAttribute("identifierref", "RES_CONTENT_" + Integer.toString(resContent));
                    res_Item.setAttribute("d2l_2p0:id", Integer.toString(contentOrder));
                    res_Item.setAttribute("description", "");
                    res_Item.setAttribute("completion_type", "1");
                    res_Item.setAttribute("resource_type_key", "");
                    res_Item.appendChild(iTitle);
                    iTitle.setTextContent(item.getName());
                    
                    Element iResource = doc.createElement("resource");
                    resources.appendChild(iResource);
                    iResource.setAttribute("identifier", "RES_CONTENT_" + Integer.toString(resContent));
                    iResource.setAttribute("type", "webcontent");
                    if (!item.getItemType().equals("Content")) {
                        iResource.setAttribute("d2l_2p0:material_type", "contentlink");
                    }
                    else {
                        iResource.setAttribute("d2l_2p0:material_type", "content");
                    }
                    iResource.setAttribute("d2l_2p0:link_target", "");
                    switch (item.getItemType()) {
                        case "Dropbox":
                            iResource.setAttribute("href", "/d2l/common/dialogs/quickLink/quickLink.d2l?ou={orgUnitId}&type=dropbox&rCode=byui_produ-" + item.getIdent());
                            break;
                        case "Discussion":
                            iResource.setAttribute("href", "/d2l/common/dialogs/quickLink/quickLink.d2l?ou={orgUnitId}&type=discuss&rCode=byui_produ-" + item.getIdent());
                            break;
                        case "Quiz":
                            iResource.setAttribute("href", "/d2l/common/dialogs/quickLink/quickLink.d2l?ou={orgUnitId}&type=quiz&rCode=byui_produ-" + item.getIdent());
                            break;
                        default:
                            iResource.setAttribute("href", item.getHref());
                            break;
                    }
                    
                    iResource.setAttribute("title", "");
                    
                    resContent++;
                    contentOrder++;
                }
            }
            
            fileNames.add("grades_d2l.xml");
            
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

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(savePath) + "\\imsmanifest.xml");

            // Save the file to the opened file.
            transformer.transform(source, result);
            
            fileNames.add("imsmanifest.xml");
            fileNames.add("dropbox_d2l.xml");
            
            jPanelText += "\nZipping Files...";
            eventLogPanel.setText(jPanelText);
            //zipFiles();
        } catch (ParserConfigurationException pce) {
            System.out.println("Oops!  Error!!");
        } catch (TransformerException ex) {
            Logger.getLogger(QuestionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * ZIP FILES Zips the files up. Also, deletes the files after zipping them
     * up.
     */
    public void zipFiles() {
        byte[] buffer = new byte[1024];
        try {

            // On rare occasions, there will be a semi colon in the title name,
            // ruining everything while zipping the file.
            if (title.contains(":") || title.contains(".")) {
                String temp = "";
                for (int i = 0; i < title.length(); i++) {
                    
                    if (title.charAt(i) == ':' || title.charAt(i) == '.') {
                        
                    } else {
                        temp += title.charAt(i);
                    }
                }
                title = temp;
            }
            System.out.println("File name: " + title);
            
            FileOutputStream fos = new FileOutputStream(savePath + "\\" + title + ".zip");
            ZipOutputStream zos = new ZipOutputStream(fos);
            
            for (String fileName : fileNames) {
                
                System.out.println("zipping file: " + fileName);
                
                fileName = savePath + "\\" + fileName;
                File srcFile = new File(fileName);
                FileInputStream fis;
                if (srcFile.exists()) {
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
            }
            zos.close();
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TransToolUI.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("File not found!");
        } catch (IOException ex) {
            Logger.getLogger(TransToolUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<String> getManifestList() {
        return manifestList;
    }
    
    public void setManifestList(ArrayList<String> manifestList) {
        this.manifestList = manifestList;
    }
    
    public String getSavePath() {
        return savePath;
    }
    
    public void setSavePath(String savePath) {
        this.savePath = savePath;
    }
    
    public String getBrainhoneyPath() {
        return brainhoneyPath;
    }
    
    public void setBrainhoneyPath(String brainhoneyPath) {
        this.brainhoneyPath = brainhoneyPath;
    }
    
    public ArrayList<String> getFileNames() {
        return fileNames;
    }
    
    public void setFileNames(ArrayList<String> fileNames) {
        this.fileNames = fileNames;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public JTextPane getEventLogPanel() {
        return eventLogPanel;
    }
    
    public void setEventLogPanel(JTextPane eventLogPanel) {
        this.eventLogPanel = eventLogPanel;
    }
    
    public String getjPanelText() {
        return jPanelText;
    }
    
    public void setjPanelText(String jPanelText) {
        this.jPanelText = jPanelText;
    }
    
    public String getLoadPath() {
        return loadPath;
    }
    
    public void setLoadPath(String loadPath) {
        this.loadPath = loadPath;
    }
    
}
