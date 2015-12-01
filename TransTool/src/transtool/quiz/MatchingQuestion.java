/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class MatchingQuestion extends BrainhoneyQuestion {

    /**
     *
     * @param brain
     * @param document
     * @param id
     * @param item
     * @param root
     */
    public MatchingQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, brain.getQuestionID(), item, root);
        writeHeader();

        //Field entries are very question specific.  Well... for the question type.
        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Matching"));
        Element qtiDataField2 = (Element) rootItem.getElementsByTagName("qti_metadatafield").item(1);
        qtiDataField2.appendChild(fieldEntry2);

        Element presentation = (Element) rootItem.getElementsByTagName("presentation").item(0);
        Element flow = doc.createElement("flow");
        presentation.appendChild(flow);

        Element resprocessing = doc.createElement("resprocessing");
        rootItem.appendChild(resprocessing);
        Element material = doc.createElement("material");
        Element extension = doc.createElement("response_extension");

        flow.appendChild(material);
        flow.appendChild(extension);

        Element matText = doc.createElement("mattext");
        material.appendChild(matText);

        matText.setAttribute("texttype", "text/html");
        matText.appendChild(doc.createTextNode(brainhoney.getBody()));

        Element gradingType = doc.createElement("d2l_2p0:grading_type");
        gradingType.setTextContent("0");
        extension.appendChild(gradingType);

        Element outcomes = doc.createElement("outcomes");
        Element decVar = doc.createElement("decvar");
        Element decVar2 = doc.createElement("decVar");
        Element decVar3 = doc.createElement("decVar");

        decVar.setAttribute("vartype", "Integer");
        decVar.setAttribute("defaultval", "0");
        decVar.setAttribute("minvalue", "0");
        decVar.setAttribute("varname", "D2L_Correct");
        decVar.setAttribute("maxvalue", "100");

        decVar2.setAttribute("vartype", "Integer");
        decVar2.setAttribute("defaultval", "0");
        decVar2.setAttribute("minvalue", "0");
        decVar2.setAttribute("varname", "D2L_Incorrect");
        decVar2.setAttribute("maxvalue", "100");

        decVar3.setAttribute("vartype", "Decimal");
        decVar3.setAttribute("defaultval", "0");
        decVar3.setAttribute("minvalue", "0");
        decVar3.setAttribute("varname", "que_score");
        decVar3.setAttribute("maxvalue", "100");

        resprocessing.appendChild(outcomes);
        outcomes.appendChild(decVar);
        outcomes.appendChild(decVar2);
        outcomes.appendChild(decVar3);

        int qNumber = itemNumber;
        int answerNumber = itemNumber + brainhoney.getqChoice().size();
        questionNumber = answerNumber;

        for (int j = 0; j < brainhoney.getqChoice().size(); j++) {
            Element grp = doc.createElement("response_grp");
            Element material2 = doc.createElement("material");
            Element mattext2 = doc.createElement("mattext");
            Element renderChoice = doc.createElement("render_choice");

            grp.setAttribute("respident", brain.getQuestionID() + "_C" + questionNumber);

            grp.setAttribute("rcardinality", "single");
            mattext2.setAttribute("texttype", "text/html");
            mattext2.setTextContent(brainhoney.getRightAnswer().get(j));
            renderChoice.setAttribute("shuffle", "yes");

            flow.appendChild(grp);
            grp.appendChild(material2);
            material2.appendChild(mattext2);
            grp.appendChild(renderChoice);

            Element flowL = doc.createElement("flow_label");
            renderChoice.appendChild(flowL);
            flowL.setAttribute("class", "Block");

            for (int i = 0; i < brainhoney.getRightAnswer().size(); i++) {

                Element responseLabel = doc.createElement("response_label");
                flowL.appendChild(responseLabel);
                responseLabel.setAttribute("ident", brain.getQuestionID() + "_M" + itemNumber);

                Element flowMat = doc.createElement("flow_mat");
                responseLabel.appendChild(flowMat);

                Element material3 = doc.createElement("material");
                flowMat.appendChild(material3);

                Element mattext3 = doc.createElement("mattext");
                material3.appendChild(mattext3);

                mattext3.setAttribute("texttype", "text/html");
                if (i < brainhoney.getqChoice().size()) {
                    mattext3.setTextContent(brainhoney.getqChoice().get(i));
                }

                Element respcondition = doc.createElement("respcondition");
                Element conditionvar = doc.createElement("conditionvar");
                Element varequal = doc.createElement("varequal");
                Element setvar = doc.createElement("setvar");

                resprocessing.appendChild(respcondition);
                respcondition.appendChild(conditionvar);
                conditionvar.appendChild(varequal);
                respcondition.appendChild(setvar);

                varequal.setAttribute("respident", brain.getQuestionID() + "_C" + questionNumber);
                varequal.setTextContent(brain.getQuestionID() + "_M" + itemNumber);

                setvar.setAttribute("action", "Add");
                setvar.setTextContent("1");

                if (i == j) {
                    setvar.setAttribute("varname", "D2L_Correct");
                } else {
                    setvar.setAttribute("varname", "D2L_Incorrect");
                }
                itemNumber++;

            }
            questionNumber++;
            itemNumber = qNumber;
        }

    }
}
