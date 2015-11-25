/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import transtool.questions.BrainhoneyContents;

/**
 *
 * @author hallm8
 */
public class ShortAnswerQuestion extends BrainhoneyQuestion {

    public ShortAnswerQuestion(BrainhoneyContents brain, Document document, int id, int item, Element root) {
        super(brain, document, id, item, root);
        writeHeader();

        Element fieldEntry2 = doc.createElement("fieldentry");
        fieldEntry2.appendChild(doc.createTextNode("Short Answer"));
        Element qtiDataField2 = (Element) rootItem.getElementsByTagName("qti_metadatafield").item(1);
        qtiDataField2.appendChild(fieldEntry2);

        Element presentation = (Element) rootItem.getElementsByTagName("presentation").item(0);
        Element flow = doc.createElement("flow");
        presentation.appendChild(flow);

        Element material = doc.createElement("material");
        Element str = doc.createElement("response_str");

        flow.appendChild(material);
        flow.appendChild(str);

        Element matText = doc.createElement("mattext");
        material.appendChild(matText);

        matText.setAttribute("texttype", "text/html");
        matText.appendChild(doc.createTextNode(brainhoney.getBody()));

        Element fib = doc.createElement("render_fib");
        Element response_label = doc.createElement("response_label");

        str.appendChild(fib);
        fib.appendChild(response_label);

        str.setAttribute("ident", randID + "_A" + questionNumber + "_ANS");
        str.setAttribute("rcardinality", "Single");

        fib.setAttribute("rows", "1");
        fib.setAttribute("columns", "40");
        fib.setAttribute("prompt", "Box");
        fib.setAttribute("fibtype", "String");

        response_label.setAttribute("ident", randID + "_A" + questionNumber + "_ANS");

        Element resprocessing = doc.createElement("resprocessing");
        Element outcomes = doc.createElement("outcomes");
        rootItem.appendChild(resprocessing);
        resprocessing.appendChild(outcomes);

        Element decvar = doc.createElement("decvar");
        outcomes.appendChild(decvar);
        decvar.setAttribute("vartype", "Integer");
        decvar.setAttribute("minvalue", "100");
        decvar.setAttribute("maxvalue", "100");
        decvar.setAttribute("varname", "Question_" + itemNumber);

        answerCheck();

        for (String rightAnswer : brainhoney.getRightAnswer()) {
            Element respcondition = doc.createElement("respcondition");
            resprocessing.appendChild(respcondition);

            Element conditionvar = doc.createElement("conditionvar");
            respcondition.appendChild(conditionvar);

            Element varequal = doc.createElement("varequal");

            varequal.setAttribute("respident", randID + "_A" + questionNumber + "_ANS");
            varequal.setAttribute("case", "no");

            varequal.setTextContent(rightAnswer);

            conditionvar.appendChild(varequal);

            Element setvar = doc.createElement("setvar");
            respcondition.appendChild(setvar);

            setvar.setAttribute("action", "Set");
            setvar.setTextContent("100.000000000");
        }

        itemNumber++;
        questionNumber++;
        feedbackNumber++;
    }

    public void answerCheck() {

        ArrayList<String> correctAnswer = new ArrayList<>();
        ArrayList<String> brainhoneyAnswer = brainhoney.getRightAnswer();

        for (String rightAnswer : brainhoneyAnswer) {
            if (rightAnswer.contains("..")) {
                String lAnswer = "";
                String hAnswer = "";

                double high;
                double low;

                boolean isHigh = false;

                int precision = 0;
                boolean isPrecision = false;

                System.out.println("Regex item discovered!!! Simplifying!");
                for (int i = 0; i < rightAnswer.length(); i++) {
                    if (rightAnswer.charAt(i) == '.' && rightAnswer.charAt(i + 1) == '.') {
                        i++;
                        isHigh = true;
                    } else if (isHigh == false) {
                        lAnswer = lAnswer + rightAnswer.charAt(i);
                    } else {
                        hAnswer = hAnswer + rightAnswer.charAt(i);
                    }
                    if (rightAnswer.charAt(i) == '.' && rightAnswer.charAt(i + 1) != '.') {
                        isPrecision = true;
                    } else if (isPrecision == true && isHigh == false) {
                        precision++;
                    }
                }
                low = Double.valueOf(lAnswer);
                high = Double.valueOf(hAnswer);

                String itemPrecision = ".";
                for (int i = 0; i < precision; i++) {
                    if (i + 1 >= precision) {
                        itemPrecision = itemPrecision + "1";
                    } else {
                        itemPrecision = itemPrecision + "0";
                    }
                }


                DecimalFormat df = new DecimalFormat("#.####");
                df.setRoundingMode(RoundingMode.CEILING);

                if (precision > 0) {
                    for (double i = low; i <= Double.valueOf(hAnswer); i = i + Double.valueOf(itemPrecision)) {
                        DecimalFormat format = new DecimalFormat("#");
                        format.setMinimumFractionDigits(precision);
                        i = new BigDecimal(i).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
                        correctAnswer.add(format.format(i));
                    }
                } else {
                    for (double i = low; i <= Double.valueOf(hAnswer); i++) {
                        DecimalFormat format = new DecimalFormat("#");
                        format.setMinimumFractionDigits(precision);
                        i = new BigDecimal(i).setScale(precision, BigDecimal.ROUND_HALF_UP).doubleValue();
                        correctAnswer.add(format.format(i));

                        brainhoney.setRightAnswer(correctAnswer);
                    }
                }
            }
        }
    }

}
