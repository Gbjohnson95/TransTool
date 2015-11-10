/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GradeItems;

import GradeItems.GradeCategories;
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
public class WriteGradeItems {

    private ArrayList<GradeCategories> gradeCategories = new ArrayList<>();
    private String filePath;
    private int sortOrder = 1;
    private int categoryID = 50000;
    private String replaceIfShName = "";

    public WriteGradeItems(String savePath, ArrayList<GradeCategories> gCategories) {
        // Save file path.
        filePath = savePath;
        gradeCategories = gCategories;

        // Standard DOM procedures
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {
            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("grades");
            doc.appendChild(rootElement);

            Element gradingScheme = doc.createElement("schemes");
            Element scheme = doc.createElement("scheme");
            scheme.setAttribute("identifier", "381");
            scheme.setAttribute("name", "BYUI_STANDARD");
            scheme.setAttribute("short_name", "BYUI");
            scheme.setAttribute("is_valid", "true");

            rootElement.appendChild(gradingScheme);
            gradingScheme.appendChild(scheme);

            ArrayList<Element> range = new ArrayList();

            for (int i = 0; i < 12; i++) {
                range.add(doc.createElement("range"));
                range.get(i).setAttribute("color_id", "1");
                scheme.appendChild(range.get(i));
            }

            range.get(0).setAttribute("percent_start", "0");
            range.get(0).setAttribute("symbol", "F");

            range.get(1).setAttribute("percent_start", "60");
            range.get(1).setAttribute("symbol", "D-");

            range.get(2).setAttribute("percent_start", "63");
            range.get(2).setAttribute("symbol", "D");

            range.get(3).setAttribute("percent_start", "67");
            range.get(3).setAttribute("symbol", "D+");

            range.get(4).setAttribute("percent_start", "70");
            range.get(4).setAttribute("symbol", "C-");

            range.get(5).setAttribute("percent_start", "73");
            range.get(5).setAttribute("symbol", "C");

            range.get(6).setAttribute("percent_start", "77");
            range.get(6).setAttribute("symbol", "C+");

            range.get(7).setAttribute("percent_start", "80");
            range.get(7).setAttribute("symbol", "B-");

            range.get(8).setAttribute("percent_start", "83");
            range.get(8).setAttribute("symbol", "B");

            range.get(9).setAttribute("percent_start", "87");
            range.get(9).setAttribute("symbol", "B+");

            range.get(10).setAttribute("percent_start", "90");
            range.get(10).setAttribute("symbol", "A-");

            range.get(11).setAttribute("percent_start", "93");
            range.get(11).setAttribute("symbol", "A");

            Element configuration = doc.createElement("configuration");
            rootElement.appendChild(configuration);

            Element categories = doc.createElement("categories");
            Element items = doc.createElement("items");

            rootElement.appendChild(categories);
            rootElement.appendChild(items);

            Element autoUpdate = doc.createElement("auto_update_final_grade");
            Element gradeSystem = doc.createElement("grading_system");
            Element emptyGradesFin = doc.createElement("include_empty_grades_in_final");
            Element defScheme = doc.createElement("default_scheme");
            Element userName = doc.createElement("show_user_name");
            Element userEmail = doc.createElement("show_user_email");

            autoUpdate.appendChild(doc.createTextNode("1"));
            gradeSystem.appendChild(doc.createTextNode("1"));
            emptyGradesFin.appendChild(doc.createTextNode("false"));
            defScheme.appendChild(doc.createTextNode("381"));
            userName.appendChild(doc.createTextNode("0"));
            userEmail.appendChild(doc.createTextNode("0"));

            configuration.appendChild(autoUpdate);
            configuration.appendChild(gradeSystem);
            configuration.appendChild(emptyGradesFin);
            configuration.appendChild(defScheme);
            configuration.appendChild(userName);
            configuration.appendChild(userEmail);

            for (int i = 0; i < gradeCategories.size(); i++) {
                Element category = doc.createElement("category");
                Element scoring = doc.createElement("scoring");

                Element name = doc.createElement("name");
                Element sName = doc.createElement("short_name");
                Element sOrder = doc.createElement("sort_order");
                Element sAverage = doc.createElement("show_average");
                Element sDist = doc.createElement("show_distribution");
                Element desc = doc.createElement("description");
                Element isActive = doc.createElement("is_active");

                Element weight = doc.createElement("weight");
                Element canExceed = doc.createElement("can_exceed_weight");
                Element distType = doc.createElement("WeightDistributionType");
                Element isAuto = doc.createElement("is_auto_pointed");
                Element highDrop = doc.createElement("high_non_bonus_drop");
                Element lowDrop = doc.createElement("low_non_bonus_drop");
                Element maxPoints = doc.createElement("max_item_points");

                categories.appendChild(category);
                category.appendChild(scoring);

                category.appendChild(name);
                category.appendChild(sName);
                category.appendChild(sOrder);
                category.appendChild(sAverage);
                category.appendChild(sDist);
                category.appendChild(desc);
                category.appendChild(isActive);

                scoring.appendChild(weight);
                scoring.appendChild(canExceed);
                scoring.appendChild(distType);
                scoring.appendChild(isAuto);
                scoring.appendChild(highDrop);
                scoring.appendChild(lowDrop);
                scoring.appendChild(maxPoints);

                category.setAttribute("id", Integer.toString(i + 1));
                category.setAttribute("identifier", Integer.toString(categoryID));
                categoryID++;

                name.appendChild(doc.createTextNode(gradeCategories.get(i).getCatName()));
                sName.appendChild(doc.createTextNode(replaceIfShName));
                sOrder.appendChild(doc.createTextNode(Integer.toString(sortOrder)));
                sortOrder++;

                sAverage.appendChild(doc.createTextNode("false"));
                sDist.appendChild(doc.createTextNode("false"));

                desc.setAttribute("text_type", "text/html");
                desc.setAttribute("is_displayed", "false");

                isActive.appendChild(doc.createTextNode("true"));

                weight.appendChild(doc.createTextNode(gradeCategories.get(i).getCatWeight()));
                canExceed.appendChild(doc.createTextNode("false"));

                distType.appendChild(doc.createTextNode("2"));
                isAuto.appendChild(doc.createTextNode("false"));
                highDrop.appendChild(doc.createTextNode("0"));

                if (gradeCategories.get(i).getDropLowest().isEmpty()) {
                    lowDrop.appendChild(doc.createTextNode("0"));
                } else {
                    lowDrop.appendChild(doc.createTextNode(gradeCategories.get(i).getDropLowest()));
                }

                maxPoints.appendChild(doc.createTextNode("0"));

            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath) + "\\grades_d2l.xml");

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException ex) {
            Logger.getLogger(WriteGradeItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(WriteGradeItems.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(WriteGradeItems.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<GradeCategories> getGradeCategories() {
        return gradeCategories;
    }

    public void setGradeCategories(ArrayList<GradeCategories> gradeCategories) {
        this.gradeCategories = gradeCategories;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public int getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getReplaceIfShName() {
        return replaceIfShName;
    }

    public void setReplaceIfShName(String replaceIfShName) {
        this.replaceIfShName = replaceIfShName;
    }

}
