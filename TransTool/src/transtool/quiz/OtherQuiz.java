/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import java.io.File;
import java.util.ArrayList;
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
 *
 * @author hallm8
 *
 * Hey Grant, don't delete this; I am going to play with the DOM parser and
 * create it as a D2L file instead, so I don't have to mess with your code.
 *
 *
 */
public class OtherQuiz {

    int itemNumber = 55011;
    int questionNumber = 55011;
    int feedbackNumber = 55011;

    public OtherQuiz(ArrayList<BrainhoneyContents> brainhoney) throws TransformerConfigurationException, TransformerException {
        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

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

            Attr attr2 = doc.createAttribute("ident");
            attr2.setValue("QLIB_1000");
            staff.setAttributeNode(attr2);

            for (int i = 0; i < brainhoney.size(); i++) {

                Element item = doc.createElement("item");
                Element metaData = doc.createElement("itemmetadata");
                Element itemproc = doc.createElement("itemproc_extension");
                Element presentation = doc.createElement("presentation");
                Element resprocessing = doc.createElement("resprocessing");
                staff.appendChild(item);
                item.appendChild(metaData);
                item.appendChild(itemproc);
                item.appendChild(presentation);
                item.appendChild(resprocessing);

                Attr id = doc.createAttribute("d2l_2p0:id");
                id.setValue(Integer.toString(i + 1));
                item.setAttributeNode(id);

                String randID = "QUES_18115_18120";
                String randQuestion = "OBJ_78306";
                randQuestion = randQuestion.substring(0, randQuestion.length() - Integer.toString(i).length());

                randID = randID.substring(0, randID.length() - Integer.toString(i).length());
                randID = randID + Integer.toString(i);

                item.setAttribute("ident", randQuestion + i);

                Attr label = doc.createAttribute("label");
                label.setValue(randID);
                item.setAttributeNode(label);

                Attr page = doc.createAttribute("d2l_2p0:page");
                page.setValue("1");
                item.setAttributeNode(page);

                Element qtiMetaData = doc.createElement("qtimetadata");
                metaData.appendChild(qtiMetaData);

                Element qtiDataField1 = doc.createElement("qti_metadatafield");
                qtiMetaData.appendChild(qtiDataField1);
                Element qtiDataField2 = doc.createElement("qti_metadatafield");
                qtiMetaData.appendChild(qtiDataField2);
                Element qtiDataField3 = doc.createElement("qti_metadatafield");
                qtiMetaData.appendChild(qtiDataField3);
                Element qtiDataField4 = doc.createElement("qti_metadatafield");
                qtiMetaData.appendChild(qtiDataField4);
                Element qtiDataField5 = doc.createElement("qti_metadatafield");
                qtiMetaData.appendChild(qtiDataField5);

                Element fieldLabel = doc.createElement("fieldlabel");
                fieldLabel.appendChild(doc.createTextNode("qmd_computerscored"));
                qtiDataField1.appendChild(fieldLabel);

                Element fieldentry = doc.createElement("fieldentry");
                fieldentry.appendChild(doc.createTextNode("yes"));
                qtiDataField1.appendChild(fieldentry);

                Element fieldLabel2 = doc.createElement("fieldlabel");
                fieldLabel2.appendChild(doc.createTextNode("qmd_questiontype"));
                qtiDataField2.appendChild(fieldLabel2);

                Element fieldEntry2 = doc.createElement("fieldentry");

                switch (brainhoney.get(i).getInteractionType()) {
                    case "choice":
                        System.out.println("Multiple Choice Question Found!!");
                        fieldEntry2.appendChild(doc.createTextNode("Multiple Choice"));
                        qtiDataField2.appendChild(fieldEntry2);
                        break;
                    case "text":
                        System.out.println("SA Question Found!");
                        fieldEntry2.appendChild(doc.createTextNode("Short Answer"));
                        qtiDataField2.appendChild(fieldEntry2);
                        break;
                    case "essay":
                        System.out.println("LA Question Found!");
                        fieldEntry2.appendChild(doc.createTextNode("Long Answer"));
                        qtiDataField2.appendChild(fieldEntry2);
                        break;
                    case "match":
                        System.out.println("Matching Question Found!");
                        fieldEntry2.appendChild(doc.createTextNode("Matching"));
                        qtiDataField2.appendChild(fieldEntry2);
                        break;
                    case "order":
                        System.out.println("ORdering Question Found!");
                        fieldEntry2.appendChild(doc.createTextNode("Ordering"));
                        qtiDataField2.appendChild(fieldEntry2);
                        break;
                    case "answer":
                        System.out.println("Multi-Answer Question Found!");
                        fieldEntry2.appendChild(doc.createTextNode("Multi-Select"));
                        qtiDataField2.appendChild(fieldEntry2);
                        break;
                    case "custom":
                        System.out.println("Custom found!  Sorry, we don't currently have support for custom questions!!");
                    default:
                        System.out.println("Error!!! Question not recognized!!!");
                        System.out.println(brainhoney.get(i).getInteractionType());
                        break;
                }

                Element fieldLabel3 = doc.createElement("fieldlabel");
                fieldLabel3.appendChild(doc.createTextNode("qmd_weighting"));
                qtiDataField3.appendChild(fieldLabel3);

                Element fieldEntry3 = doc.createElement("fieldentry");
                fieldEntry3.appendChild(doc.createTextNode(brainhoney.get(i).getScore() + ".000000000"));
                qtiDataField3.appendChild(fieldEntry3);

                Element fieldLabel4 = doc.createElement("fieldlabel");
                fieldLabel4.appendChild(doc.createTextNode("qmd_globalid"));
                qtiDataField4.appendChild(fieldLabel4);

                Element fieldEntry4 = doc.createElement("fieldentry");
                String randVariable = "54e92f71-a948-44f1-83d1-71852872bef4";
                randVariable = randVariable.substring(0, randVariable.length() - Integer.toString(i).length());
                fieldEntry4.appendChild(doc.createTextNode(randVariable + Integer.toString(i)));
                qtiDataField4.appendChild(fieldEntry4);

                Element fieldLabel5 = doc.createElement("fieldlabel");
                fieldLabel5.appendChild(doc.createTextNode("qmd_displayid"));
                qtiDataField5.appendChild(fieldLabel5);

                Element fieldEntry5 = doc.createElement("fieldentry");
                fieldEntry5.appendChild(doc.createTextNode("D2LSIM-CO-" + Integer.toString(i)));
                qtiDataField5.appendChild(fieldEntry5);

                Element difficulty = doc.createElement("d2l_2p0:difficulty");
                Element isBonus = doc.createElement("d2l_2p0:isbonus");
                Element isMandatory = doc.createElement("d2l_2p0:ismandatory");

                difficulty.appendChild(doc.createTextNode("3"));
                isBonus.appendChild(doc.createTextNode("no"));
                isMandatory.appendChild(doc.createTextNode("no"));

                itemproc.appendChild(difficulty);
                itemproc.appendChild(isBonus);
                itemproc.appendChild(isMandatory);

                Element flow = doc.createElement("flow");
                presentation.appendChild(flow);

                Element material = doc.createElement("material");
                Element extension = doc.createElement("response_extension");
                Element lid = doc.createElement("response_lid");
                flow.appendChild(material);
                flow.appendChild(extension);
                flow.appendChild(lid);

                Element matText = doc.createElement("mattext");
                material.appendChild(matText);

                Attr textType = doc.createAttribute("texttype");
                textType.setValue("text/html");
                matText.setAttributeNode(textType);
                matText.appendChild(doc.createTextNode(brainhoney.get(i).getBody()));

                Element dStyle = doc.createElement("d2l_2p0:display_style");
                Element enumeration = doc.createElement("d2l_2p0:enumeration");
                Element gType = doc.createElement("d2l_2p0:grading_type");
                extension.appendChild(dStyle);
                extension.appendChild(enumeration);
                extension.appendChild(gType);

                dStyle.appendChild(doc.createTextNode("2"));
                enumeration.appendChild(doc.createTextNode("5"));
                gType.appendChild(doc.createTextNode("0"));

                Attr identity = doc.createAttribute("ident");
                Attr rcardinality = doc.createAttribute("rcardinality");
                identity.setValue(randID + "_LID");
                rcardinality.setValue("Single");
                lid.setAttributeNode(identity);
                lid.setAttributeNode(rcardinality);

                Element renderChoice = doc.createElement("render_choice");
                renderChoice.setAttribute("shuffle", "no");
                lid.appendChild(renderChoice);

                for (String qChoice : brainhoney.get(i).getqChoice()) {

                    Element flowLabel = doc.createElement("flow_label");
                    renderChoice.appendChild(flowLabel);

                    Attr classLabel = doc.createAttribute("class");
                    classLabel.setValue("Block");
                    flowLabel.setAttributeNode(classLabel);

                    Element responseLabel = doc.createElement("response_label");
                    responseLabel.setAttribute("ident", randID + "_A" + itemNumber);
                    Element flowMat = doc.createElement("flow_mat");
                    Element mbody = doc.createElement("material");
                    Element materialText = doc.createElement("mattext");
                    materialText.setAttribute("texttype", "text/html");

                    materialText.appendChild(doc.createTextNode(qChoice));

                    flowLabel.appendChild(responseLabel);
                    responseLabel.appendChild(flowMat);
                    flowMat.appendChild(mbody);
                    mbody.appendChild(materialText);
                    itemNumber++;
                }

                for (int j = 0; j < brainhoney.get(i).getqChoice().size(); j++) {
                    Element respCondition = doc.createElement("respcondition");
                    respCondition.setAttribute("title", "Response Condition " + Integer.toString(j + 1));
                    Element conditionvar = doc.createElement("conditionvar");
                    Element varequal = doc.createElement("varequal");
                    varequal.setAttribute("respident", randID + "_LID");
                    varequal.appendChild(doc.createTextNode(randID + "_A" + questionNumber));
                    Element setVar = doc.createElement("setvar");
                    setVar.setAttribute("action", "Set");

                    switch (brainhoney.get(i).getInteractionType()) {
                        case "choice":
                            if (brainhoney.get(i).getRightAnswer().get(0).equals(Integer.toString(j + 1))) {
                                setVar.appendChild(doc.createTextNode("100.000000000"));
                            } else {
                                setVar.appendChild(doc.createTextNode("0.000000000"));
                            }

                            Element displayFeedback = doc.createElement("displayfeedback");
                            displayFeedback.setAttribute("feedbacktype", "Response");
                            displayFeedback.setAttribute("linkrefid", randID + "_IF" + questionNumber);
                            questionNumber++;
                            respCondition.appendChild(displayFeedback);
                            break;

                        case "text":
                            System.out.println("SA Question Found!");

                            break;
                        case "essay":
                            System.out.println("LA Question Found!");

                            break;
                        case "match":
                            System.out.println("Matching Question Found!");

                            break;
                        case "order":
                            System.out.println("ORdering Question Found!");

                            break;
                        case "answer":
                            System.out.println("Multi-Answer Question Found!");

                            break;
                        case "custom":
                            System.out.println("Custom found!  Sorry, we don't currently have support for custom questions!!");
                        default:
                            System.out.println("Error!!! Question not recognized!!!");
                            System.out.println(brainhoney.get(i).getInteractionType());
                            break;
                    }
                    if (!brainhoney.get(i).getRightAnswer().isEmpty()) {

                        resprocessing.appendChild(respCondition);
                        respCondition.appendChild(conditionvar);
                        conditionvar.appendChild(varequal);
                        respCondition.appendChild(setVar);

                    }
                }

                for (String qChoice : brainhoney.get(i).getqChoice()) {
                    Element itemFeedback = doc.createElement("itemfeedback");
                    itemFeedback.setAttribute("ident", randID + "_IF" + feedbackNumber);
                    Element fMaterial = doc.createElement("material");
                    Element mattext = doc.createElement("mattext");
                    mattext.setAttribute("texttype", "text/html");

                    item.appendChild(itemFeedback);
                    itemFeedback.appendChild(fMaterial);
                    fMaterial.appendChild(mattext);
                    feedbackNumber++;
                }

            }

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("C:\\questiondb.xml"));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            System.out.println("Oops!  Error!!");
        }
    }
}