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
import transtool.quiz.Section;

/**
 *
 * @author hallm8
 */
public class QuizItem extends Item {

    private String category;
    private String gradeable;
    private String weight;
    private String attemptLimit;
    private String securityLevel;
    private String password;
    private Element quizItem;
    private String fillLater;

    ArrayList<Section> sections;

    public QuizItem(ArrayList<Section> sections, String toSave) {
        this.sections = sections;
        toSave = savePath;

    }

    @Override
    public void writeItem() {

        try {

            // Standard DOM procedures
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element root = doc.createElement("questestinterop");
            doc.appendChild(root);

            root.setAttribute("xmlns:d2l_2p0", "http://desire2learn.com/xsd/d2lcp_v2p0");

            Element assessment = doc.createElement("assessment");
            root.appendChild(assessment);

            assessment.setAttribute("d2l_2p0:resource_code", "byui_produ-114352");
            assessment.setAttribute("ident", ident);
            assessment.setAttribute("title", sections.get(0).getQuiz().getQuizName());
            assessment.setAttribute("d2l_2p0:id", itemID);

            Element rubric = doc.createElement("rubric");
            Element flow_mat = doc.createElement("flow_mat");
            Element material = doc.createElement("material");
            Element mattext = doc.createElement("mattext ");

            assessment.appendChild(rubric);
            rubric.appendChild(flow_mat);
            flow_mat.appendChild(material);
            material.appendChild(mattext);

            material.setAttribute("d2l_2p0:isdisplayed", "yes");
            material.setAttribute("texttype", "text/html");

            Element presentation_material = doc.createElement("presentation_material");
            Element flow_mat2 = doc.createElement("flow_mat");
            Element material2 = doc.createElement("material ");
            Element mattext2 = doc.createElement("mattext");
            Element material3 = doc.createElement("material");
            Element mattext3 = doc.createElement("mattext");

            assessment.appendChild(presentation_material);
            presentation_material.appendChild(flow_mat2);
            flow_mat2.appendChild(material2);
            flow_mat2.appendChild(material3);
            material2.appendChild(mattext2);
            material3.appendChild(mattext3);

            material2.setAttribute("label", "page header");
            mattext2.setAttribute("d2l_2p0:isdisplayed", "no");
            material3.setAttribute("label", "page footer");
            mattext3.setAttribute("d2l_2p0:isdisplayed", "no");

            Element assess_procextension = doc.createElement("assess_procextension");
            Element introMessage = doc.createElement("d2l_2p0:intro_message");
            Element category1 = doc.createElement("category");
            Element grade_item = doc.createElement("grade_item");
            Element dRightClick = doc.createElement("d2l_2p0:disable_right_click");
            Element dPager = doc.createElement("d2l_2p0:disable_pager_access");
            Element is_active = doc.createElement("is_active");
            Element start = doc.createElement("d2l_2p0:date_start");
            Element end = doc.createElement("d2l_2p0:date_end");
            Element hasSchedule = doc.createElement("d2l_2p0:has_schedule_event");
            Element attRldb = doc.createElement("d2l_2p0:is_attempt_Rldb");
            Element subRldb = doc.createElement("d2l_2p0:is_subview_Rldb");
            Element tLimit = doc.createElement("d2l_2p0:time_limit");
            Element showClock = doc.createElement("d2l_2p0:show_clock");
            Element enforceTime = doc.createElement("d2l_2p0:enforce_time_limit");
            Element grace = doc.createElement("d2l_2p0:grace_period");
            Element late = doc.createElement("d2l_2p0:late_limit");
            Element attempts = doc.createElement("d2l_2p0:attempts_allowed");
            Element restrictions = doc.createElement("d2l_2p0:attempt_restrictions");
            Element calcType = doc.createElement("d2l_2p0:mark_calculation_type");
            Element forward = doc.createElement("d2l_2p0:is_forward_only");

            introMessage.setAttribute("d2l_2p0:isdisplayed", "no");
            introMessage.setAttribute("texttype", "text/html");
            grade_item.setAttribute("d2l_2p0:is_autoexport", "yes");
            grade_item.setAttribute("resource_code", fillLater);

            assessment.appendChild(assess_procextension);
            assess_procextension.appendChild(introMessage);
            assess_procextension.appendChild(category1);
            assess_procextension.appendChild(grade_item);
            assess_procextension.appendChild(dRightClick);
            assess_procextension.appendChild(dPager);
            assess_procextension.appendChild(is_active);
            assess_procextension.appendChild(start);
            assess_procextension.appendChild(end);
            assess_procextension.appendChild(hasSchedule);
            assess_procextension.appendChild(attRldb);
            assess_procextension.appendChild(subRldb);
            assess_procextension.appendChild(tLimit);
            assess_procextension.appendChild(showClock);
            assess_procextension.appendChild(enforceTime);
            assess_procextension.appendChild(grace);
            assess_procextension.appendChild(late);
            assess_procextension.appendChild(attempts);
            assess_procextension.appendChild(restrictions);
            assess_procextension.appendChild(calcType);
            assess_procextension.appendChild(forward);

            Element assessfeedback = doc.createElement("assessfeedback");
            Element rubric2 = doc.createElement("rubric");
            Element flow_mat3 = doc.createElement("flow_mat");
            Element material4 = doc.createElement("material");
            Element mattext4 = doc.createElement("mattext");
            Element duration = doc.createElement("d2l_2p0:duration");
            Element displayID = doc.createElement("d2l_2p0:response_display_type_id");
            Element showAnswers = doc.createElement("d2l_2p0:show_correct_answers");
            Element restrictIP = doc.createElement("d2l_2p0:submission_restrictip");
            Element average = doc.createElement("d2l_2p0:show_class_average");
            Element scoreDistr = doc.createElement("d2l_2p0:show_score_distribution");

            assessment.appendChild(assessfeedback);
            assessfeedback.appendChild(rubric2);
            rubric2.appendChild(flow_mat3);
            flow_mat3.appendChild(material4);
            material4.appendChild(mattext4);
            rubric2.appendChild(duration);
            rubric2.appendChild(displayID);
            rubric2.appendChild(showAnswers);
            rubric2.appendChild(restrictIP);
            rubric2.appendChild(average);
            rubric2.appendChild(scoreDistr);

            for (Section section : sections) {
            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(savePath + "\\quiz_d2l_" + itemID));

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getGradeable() {
        return gradeable;
    }

    public void setGradeable(String gradeable) {
        this.gradeable = gradeable;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getAttemptLimit() {
        return attemptLimit;
    }

    public void setAttemptLimit(String attemptLimit) {
        this.attemptLimit = attemptLimit;
    }

    public String getSecurityLevel() {
        return securityLevel;
    }

    public void setSecurityLevel(String securityLevel) {
        this.securityLevel = securityLevel;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Element getQuizItem() {
        return quizItem;
    }

    public void setQuizItem(Element quizItem) {
        this.quizItem = quizItem;
    }

    public void populateClass() {
        if (quizItem != null) {

        } else {
            System.out.println("Object not initialized!  Please initialize!");
        }
    }

}
