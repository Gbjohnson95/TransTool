/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixHTML;

/**
 *
 * @author hallm8
 */
public class extratext {
    
    
    
    
    
    /*
    
                    /**
                    questionNumber = itemNumber;
                    feedbackNumber
                            = questionNumber;
                    Element item = doc.createElement("item");
                    Element metaData = doc.createElement("itemmetadata");
                    Element itemproc
                            = doc.createElement("itemproc_extension");
                    Element presentation = doc.createElement("presentation");
                    Element resprocessing = doc.createElement("resprocessing");
                    section.appendChild(item);
                    item.appendChild(metaData);
                    item.appendChild(itemproc);
                    item.appendChild(presentation);
                    item.appendChild(resprocessing);
                    Attr id
                            = doc.createAttribute("d2l_2p0:id");
                    id.setValue(Integer.toString(idNumber));
                    item.setAttributeNode(id);
                    String randID
                            = "QUES_18115_10000";
                    String randQuestion = "OBJ_10000";
                    randQuestion = randQuestion.substring(0,
                            randQuestion.length()
                            - Integer.toString(idNumber).length());
                    randID
                            = randID.substring(0, randID.length()
                                    -  * Integer.toString(idNumber).length());
                    randID = randID
                            + Integer.toString(idNumber);
                    item.setAttribute("ident",
                            randQuestion + idNumber);
                    Attr label
                            = doc.createAttribute("label");
                    label.setValue(randID);
                    item.setAttributeNode(label);
                    Attr page
                            = doc.createAttribute("d2l_2p0:page");
                    page.setValue("1");
                    item.setAttributeNode(page);
                    Element qtiMetaData
                            = doc.createElement("qtimetadata");
                    metaData.appendChild(qtiMetaData);
                    Element qtiDataField1
                            = doc.createElement("qti_metadatafield");
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
                    Element fieldentry
                            = doc.createElement("fieldentry");
                    fieldentry.appendChild(doc.createTextNode("yes"));
                    qtiDataField1.appendChild(fieldentry);
                    Element fieldLabel2 = doc.createElement("fieldlabel");
                    fieldLabel2.appendChild(doc.createTextNode("qmd_questiontype"));
                    qtiDataField2.appendChild(fieldLabel2);
                    Element fieldEntry2 = doc.createElement("fieldentry"); // Each
                    question type has a slightly different XML setup
                    . // this
  simply mediates between them
                    . switch  * (brainhoney.get(i).getInteractionType())    {
                        case "choice":
                            fieldEntry2.appendChild(doc.createTextNode(
                            "Multiple
                      Choice
                        ")); qtiDataField2.appendChild(fieldEntry2); break;
                      case "text":
                            fieldEntry2.appendChild(doc.createTextNode(
                            "Short
                      Answer
                        ")); qtiDataField2.appendChild(fieldEntry2); break;
                      case "essay":
                            fieldEntry2.appendChild(doc.createTextNode(
                            "Long
                      Answer
                        ")); qtiDataField2.appendChild(fieldEntry2); break;
                      case "match":
                            fieldEntry2.appendChild(doc.createTextNode("Matching"));
                            qtiDataField2.appendChild(fieldEntry2);
                            break;
                        case "order":
                            fieldEntry2.appendChild(doc.createTextNode("Ordering"));
                            qtiDataField2.appendChild(fieldEntry2);
                            break;
                        case "answer":
                            fieldEntry2.appendChild(doc.createTextNode("Multi-Select"));
                            qtiDataField2.appendChild(fieldEntry2);
                            break;
                        case "custom":
                        case "composite":
                            System.out.println(
                            "Custom
                      found
                            ! Sorry
                            , we don
                             
                             
                             
                            't currently have support for custom

                              questions   ! !"); default: System.out.println(" Error   {
                                ! ! !Question 
                            }
                            not recognized
                            !!!");
                      System.out.println(brainhoney.get(i).getInteractionType());
                            break;
                    }
                    Element fieldLabel3
                            = doc.createElement("fieldlabel");
                    fieldLabel3.appendChild(doc.createTextNode("qmd_weighting"));
                    qtiDataField3.appendChild(fieldLabel3);
                    Element fieldEntry3 = doc.createElement("fieldentry"); // Some
                    scores in Brainhoney aren
                     
                     
                     
                     
                     
                     
                     
                     
                     
                    't given a point value,
//                    because they are only worth 1 point.This just says 
// if they don't have a point value, we give it one point. if
                       (brainhoney.get(i).getScore().isEmpty()
                    
                        ) {
                        fieldEntry3.appendChild(doc.createTextNode("1.000000000"));
                    }else {
                        fieldEntry3.appendChild(doc.createTextNode(brainhoney.get(i).getScore()
                                + ".000000000"));
                    }
                    qtiDataField3.appendChild(fieldEntry3);
                    Element fieldLabel4 = doc.createElement("fieldlabel");
                    fieldLabel4.appendChild(doc.createTextNode("qmd_globalid"));
                    qtiDataField4.appendChild(fieldLabel4);
                    Element fieldEntry4 = doc.createElement("fieldentry");
                    String randVariable = "54e92f71-a948-44f1-83d1-71852872bef4";
                    randVariable = randVariable.substring(0,
                            randVariable.length()
                            - Integer.toString(idNumber).length());
                    fieldEntry4.appendChild(doc.createTextNode(randVariable
                            + Integer.toString(idNumber)));
                    qtiDataField4.appendChild(fieldEntry4);
                    idNumber++;
                    Element fieldLabel5 = doc.createElement("fieldlabel");
                    fieldLabel5.appendChild(doc.createTextNode("qmd_displayid"));
                    qtiDataField5.appendChild(fieldLabel5);
                    Element fieldEntry5 = doc.createElement("fieldentry");
                    fieldEntry5.appendChild(doc.createTextNode("D2LSIM-CO-"
                            + Integer.toString(i)));
                    qtiDataField5.appendChild(fieldEntry5);
                    Element difficulty = doc.createElement("d2l_2p0:difficulty");
                    Element isBonus = doc.createElement("d2l_2p0:isbonus");
                    Element isMandatory
                            = doc.createElement("d2l_2p0:ismandatory");
                    difficulty.appendChild(doc.createTextNode("3"));
                    isBonus.appendChild(doc.createTextNode("no"));
                    isMandatory.appendChild(doc.createTextNode("no"));
                    itemproc.appendChild(difficulty);
                    itemproc.appendChild(isBonus);
                    itemproc.appendChild(isMandatory);
                    Element flow
                            = doc.createElement("flow");
                    presentation.appendChild(flow);
                    Element extension
                            = doc.createElement("response_extension");
                    Element lid
                            = doc.createElement("response_lid");
                    Element material
                            = doc.createElement("material");
                    flow.appendChild(material);
// Another switch case                    statement.This has mainly to do 
// with the body text                        and                       the                     way                     the correct answer is 
// found by                             Brightspace.
                    if (!brainhoney.get(i).getInteractionType().equals("text")) {
                        flow.appendChild(extension);
                        flow.appendChild(lid);
                    } else {
                        Element responseStr
                                = doc.createElement("response_str");
                        responseStr.setAttribute("ident", randID + "_A"
                                + questionNumber + "_STR");
                        responseStr.setAttribute("rcardinality", "Single");
                        Element renderFib = doc.createElement("render_fib");
                        renderFib.setAttribute("rows", "3");
                        renderFib.setAttribute("columns", "60");
                        renderFib.setAttribute("prompt", "Box");
                        renderFib.setAttribute("fibtype", "String");
                        Element responseLabel = doc.createElement("response_label");
                        responseLabel.setAttribute("ident", randID + "_A"
                                + questionNumber + "_ANS");
                        flow.appendChild(responseStr);
                        responseStr.appendChild(renderFib);
                        renderFib.appendChild(responseLabel);
                    }
                    Element matText
                            = doc.createElement("mattext");
                    material.appendChild(matText);
                    Attr textType
                            = doc.createAttribute("texttype");
                    textType.setValue("text/html");
                    matText.setAttributeNode(textType);
                    matText.appendChild(doc.createTextNode(brainhoney.get(i).getBody()));
                    Element dStyle
                            = doc.createElement("d2l_2p0:display_style");
                    Element enumeration = doc.createElement("d2l_2p0:enumeration");
                    Element gType
                            = doc.createElement("d2l_2p0:grading_type");
                    extension.appendChild(dStyle);
                    extension.appendChild(enumeration);
                    extension.appendChild(gType);
                    dStyle.appendChild(doc.createTextNode("2"));
                    enumeration.appendChild(doc.createTextNode("5"));
                    gType.appendChild(doc.createTextNode("0"));
                    Attr identity
                            = doc.createAttribute("ident");
                    Attr rcardinality
                            = doc.createAttribute("rcardinality");
                    identity.setValue(randID + "_LID");
                    rcardinality.setValue("Single");
                    lid.setAttributeNode(identity);
                    lid.setAttributeNode(rcardinality);
                    Element renderChoice
                            = doc.createElement("render_choice");
                    renderChoice.setAttribute("shuffle", "no");
                    lid.appendChild(renderChoice); // This is annoying, but
                    each possible answer has several // different settings.
                            So, we just go through them all and // set them in
                    accordance to preference
                    . for (String qChoice
                            : brainhoney.get(i).getqChoice()) {
                        Element flowLabel
                                = doc.createElement("flow_label");
                        renderChoice.appendChild(flowLabel);
                        Attr classLabel
                                = doc.createAttribute("class");
                        classLabel.setValue("Block");
                        flowLabel.setAttributeNode(classLabel);
                        Element responseLabel = doc.createElement("response_label");
                        responseLabel.setAttribute("ident", randID + "_A"
                                + itemNumber);
                        Element flowMat
                                = doc.createElement("flow_mat");
                        Element mbody
                                = doc.createElement("material");
                        Element materialText
                                = doc.createElement("mattext");
                        materialText.setAttribute("texttype", "text/html");
                        materialText.appendChild(doc.createTextNode(qChoice));
                        flowLabel.appendChild(responseLabel);
                        responseLabel.appendChild(flowMat);
                        flowMat.appendChild(mbody);
                        mbody.appendChild(materialText);
                        itemNumber++;
                    }
                    for (int j = 0; j < brainhoney.get(i).getqChoice().size();
                            j++) {
                        Element respCondition
                                = doc.createElement("respcondition");
                        Element conditionvar
                                = doc.createElement("conditionvar");
                        Element varequal
                                = doc.createElement("varequal");
                        Element decVar
                                = doc.createElement("decvar"); // This portion has to do
                        with setting the correct // answer. switch
                        (brainhoney.get(i).getInteractionType()
                        
                        
                    
                        
                        
                    
                    ) {
                        
                    
                    case "choice":
                      Element setVariable = doc.createElement("setvar");
                      respCondition.setAttribute("title", "Response Condition "
                      + Integer.toString(j + 1));
                      varequal.setAttribute("respident", randID + "_LID");
                      varequal.appendChild(doc.createTextNode(randID + "_A" +
                      questionNumber)); setVariable.setAttribute("action",
                      "Set"); if
                      (!brainhoney.get(i).getRightAnswer().isEmpty()) { if
                      (brainhoney.get(i).getRightAnswer().get(0).equals(Integer.toString(j
                      + 1))) {
                      setVariable.appendChild(doc.createTextNode("100.000000000"));
                      } else {
                      setVariable.appendChild(doc.createTextNode("0.000000000"));
                      } } Element displayFeedback =
                      doc.createElement("displayfeedback");
                      displayFeedback.setAttribute("feedbacktype", "Response");
                      displayFeedback.setAttribute("linkrefid", randID + "_IF"
                      + questionNumber); questionNumber++;
                      respCondition.appendChild(displayFeedback);
                      resprocessing.appendChild(respCondition);
                      respCondition.appendChild(conditionvar);
                      conditionvar.appendChild(varequal);
                      respCondition.appendChild(setVariable); break; case
                      "text": Element outcomes = doc.createElement("outcomes");
                      decVar.setAttribute("vartype", "Integer");
                      decVar.setAttribute("minvalue", "0");
                      decVar.setAttribute("maxvalue", "100");
                      decVar.setAttribute("varname", "Blank_1");
                      resprocessing.appendChild(outcomes);
                      outcomes.appendChild(decVar); if
                      (!brainhoney.get(i).getRightAnswer().isEmpty()) { //for
                      (String rightAnswer : brainhoney.get(i).getRightAnswer())
                      { Element setVar = doc.createElement("setvar"); Element
                      varequals = doc.createElement("varequal");
                      varequals.setAttribute("respident", randID + "_A" +
                      questionNumber + "_ANS"); varequals.setAttribute("case",
                      "no");
                      varequals.appendChild(doc.createTextNode(brainhoney.get(i).getRightAnswer().get(j)));
                      setVar.setAttribute("action", "Set");
                      setVar.appendChild(doc.createTextNode("100.000000000"));
                      resprocessing.appendChild(respCondition);
                      respCondition.appendChild(conditionvar);
                      conditionvar.appendChild(varequals);
                      respCondition.appendChild(setVar); //} } break; case
                      "essay": case "match": break; case "order": break; case
                      "answer": Element setVariation =
                      doc.createElement("setvar"); Element out =
                      doc.createElement("outcomes");
                      decVar.setAttribute("vartype", "Integer");
                      decVar.setAttribute("defaultval", "0");
                      decVar.setAttribute("minvalue", "0"); Element decVar2 =
                      doc.createElement("decVar"); Element decVar3 =
                      doc.createElement("decVar");
                      decVar2.setAttribute("vartype", "Integer");
                      decVar2.setAttribute("defaultval", "0");
                      decVar2.setAttribute("minvalue", "0");
                      decVar3.setAttribute("vartype", "Integer");
                      decVar3.setAttribute("defaultval", "0");
                      decVar3.setAttribute("minvalue", "0");
                      decVar.setAttribute("varname", "que_score");
                      decVar.setAttribute("maxvalue", "100");
                      decVar2.setAttribute("varname", "D2L_Correct");
                      decVar3.setAttribute("varname", "D2L_Incorrect");
                      varequal.setAttribute("respident", randID + "_LID");
                      varequal.appendChild(doc.createTextNode(randID + "_A" +
                      questionNumber)); setVariation.setAttribute("action",
                      "Add");
                      setVariation.appendChild(doc.createTextNode("1"));
                      boolean didFind = false; for (int k = 0; k <
                      brainhoney.get(i).getRightAnswer().size(); k++) { if
                      (brainhoney.get(i).getRightAnswer().get(k).equals(Integer.toString(j
                      + 1))) { setVariation.setAttribute("varname",
                      "D2L_Correct"); didFind = true;
                      respCondition.appendChild(setVariation); } } if (didFind
                      == false) { setVariation.setAttribute("varname",
                      "D2L_Incorrect"); } questionNumber++;
                      resprocessing.appendChild(out); out.appendChild(decVar);
                      out.appendChild(decVar2); out.appendChild(decVar3);
                      resprocessing.appendChild(respCondition);
                      respCondition.appendChild(conditionvar);
                      conditionvar.appendChild(varequal); break; case "custom":
                      case "composite": System.out.println("Custom found!
                      Sorry, we don't currently have support for custom
                      questions!!"); default: System.out.println("Error!!!
                      Question not recognized!!!");
                      System.out.println(brainhoney.get(i).getInteractionType());
                      break; } if
                      (!brainhoney.get(i).getRightAnswer().isEmpty()) { } } //
                      Right here holds item feedback for the questions. They //
                      are associated with each question via feedback ID. for
                      (String qChoice : brainhoney.get(i).getqChoice()) {
                      Element itemFeedback = doc.createElement("itemfeedback");
                      itemFeedback.setAttribute("ident", randID + "_IF" +
                      feedbackNumber); Element fMaterial =
                      doc.createElement("material"); Element mattext =
                      doc.createElement("mattext");
                      mattext.setAttribute("texttype", "text/html");
                      item.appendChild(itemFeedback);
                      itemFeedback.appendChild(fMaterial);
                      fMaterial.appendChild(mattext); feedbackNumber++; }
                     
                }

            }

            // Now that we are done, we write the program.
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
                    Transformer transformer = transformerFactory.newTransformer();
                    DOMSource source = new DOMSource(doc);
                    StreamResult result = new StreamResult(new File(savePath) + "\\questiondb.xml");

                    // Output to console for testing
                    // StreamResult result = new StreamResult(System.out);
                    transformer.transform(source, result);

                    System.out.println("File saved!");


               */
    
}
