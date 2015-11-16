/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class MultiSelect extends BrainhoneyQuestion {

    public MultiSelect(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, id, item, root);
        writeHeader();

        //Field entries are very question specific.  Well... for the question type.
        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Multi-Select"));
        Element qtiDataField2 = (Element) rootItem.getElementsByTagName("qti_metadatafield").item(1);
        qtiDataField2.appendChild(fieldEntry2);

        Element presentation = (Element) rootItem.getElementsByTagName("presentation").item(0);
        Element flow = doc.createElement("flow");
        presentation.appendChild(flow);

        Element resprocessing = doc.createElement("resprocessing");
        rootItem.appendChild(resprocessing);
        // Carrying on from the other thingamajig.  I don't know what I am trying to say.
        Element material = doc.createElement("material");
        Element extension = doc.createElement("response_extension");
        Element lid = doc.createElement("response_lid");

        flow.appendChild(material);
        flow.appendChild(extension);
        flow.appendChild(lid);

        Element matText = doc.createElement("mattext");
        material.appendChild(matText);

        matText.setAttribute("texttype", "text/html");
        matText.appendChild(doc.createTextNode(brainhoney.getBody()));

        Element dStyle = doc.createElement("d2l_2p0:display_style");
        Element enumeration = doc.createElement("d2l_2p0:enumeration");
        Element gType = doc.createElement("d2l_2p0:grading_type");
        extension.appendChild(dStyle);
        extension.appendChild(enumeration);
        extension.appendChild(gType);

        dStyle.appendChild(doc.createTextNode("2"));
        enumeration.appendChild(doc.createTextNode("5"));
        gType.appendChild(doc.createTextNode("2"));

        Attr identity = doc.createAttribute("ident");
        Attr rcardinality = doc.createAttribute("rcardinality");
        identity.setValue(randID + "_LID");
        rcardinality.setValue("Multiple");
        lid.setAttributeNode(identity);
        lid.setAttributeNode(rcardinality);

        Element renderChoice = doc.createElement("render_choice");
        renderChoice.setAttribute("shuffle", "no");
        lid.appendChild(renderChoice);

        for (String qChoice : brainhoney.getqChoice()) {

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

        Element outcomes = doc.createElement("outcomes");
        Element decVar = doc.createElement("decvar");
        Element decVar2 = doc.createElement("decVar");
        Element decVar3 = doc.createElement("decVar");

        decVar.setAttribute("vartype", "Integer");
        decVar.setAttribute("defaultval", "0");
        decVar.setAttribute("minvalue", "0");
        decVar.setAttribute("varname", "que_score");
        decVar.setAttribute("maxvalue", "100");

        decVar2.setAttribute("vartype", "Integer");
        decVar2.setAttribute("defaultval", "0");
        decVar2.setAttribute("minvalue", "0");
        decVar2.setAttribute("varname", "D2L_Correct");

        decVar3.setAttribute("vartype", "Integer");
        decVar3.setAttribute("defaultval", "0");
        decVar3.setAttribute("minvalue", "0");
        decVar3.setAttribute("varname", "D2L_Incorrect");

        resprocessing.appendChild(outcomes);
        outcomes.appendChild(decVar);
        outcomes.appendChild(decVar2);
        outcomes.appendChild(decVar3);

        for (int i = 0; i < brainhoney.getqChoice().size(); i++) {
            Element respcondition = doc.createElement("respcondition");
            Element conditionvar = doc.createElement("conditionvar");
            Element varequal = doc.createElement("varequal");
            Element setvar = doc.createElement("setvar");

            resprocessing.appendChild(respcondition);
            respcondition.appendChild(conditionvar);
            conditionvar.appendChild(varequal);
            respcondition.appendChild(setvar);

            respcondition.setAttribute("title", "Response Condition");
            respcondition.setAttribute("continue", "yes");

            varequal.setAttribute("respident", randID + "_LID");
            varequal.setTextContent(randID + "_A" + questionNumber);
            questionNumber++;

            setvar.setAttribute("action", "Add");
            setvar.setTextContent("1");
            
            if (brainhoney.getRightAnswer().contains(Integer.toString(i + 1))) {
                System.out.println("Woohoo!!!");
                setvar.setAttribute("varname", "D2L_Correct");
            } else {
                setvar.setAttribute("varname", "D2L_Incorrect");
            }

        }

        for (String qChoice : brainhoney.getqChoice()) {
            Element itemFeedback = doc.createElement("itemfeedback");
            itemFeedback.setAttribute("ident", randID + "_IF" + feedbackNumber);
            Element fMaterial = doc.createElement("material");
            Element mattext = doc.createElement("mattext");
            mattext.setAttribute("texttype", "text/html");

            rootItem.appendChild(itemFeedback);
            itemFeedback.appendChild(fMaterial);
            fMaterial.appendChild(mattext);
            feedbackNumber++;
        }

    }
}
