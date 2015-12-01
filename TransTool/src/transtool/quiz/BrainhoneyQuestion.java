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
public class BrainhoneyQuestion {

    protected BrainhoneyContents brainhoney;
    protected Document doc;
    protected int itemNumber;
    protected int questionNumber;
    protected int feedbackNumber;
    protected String idNumber;
    protected Element rootItem;
    //protected String randID;
    protected String bID;
    protected int qScore;

    /**
     * 
     */
    BrainhoneyQuestion() {
        brainhoney = new BrainhoneyContents();
    }

    /**
     * 
     * @param brain
     * @param document
     * @param id
     * @param item
     * @param root 
     */
    public BrainhoneyQuestion(BrainhoneyContents brain, Document document, String id, int item, Element root) {
        brainhoney = brain;

        questionNumber = item;
        feedbackNumber = item;
        itemNumber = item;

        doc = document;
        idNumber = id;
        rootItem = doc.createElement("item");
    }

    /**
     * WRITE HEADER Creates everything that is common amongst each individual
     * question.
     */
    public void writeHeader() {
        Element metaData = doc.createElement("itemmetadata");
        Element itemproc = doc.createElement("itemproc_extension");
        Element presentation = doc.createElement("presentation");
        //section.appendChild(item);
        rootItem.appendChild(metaData);
        rootItem.appendChild(itemproc);
        rootItem.appendChild(presentation);

        Attr id = doc.createAttribute("d2l_2p0:id");
        id.setValue(idNumber);

        rootItem.setAttributeNode(id);

        //randID = "QUES_18115_10000";
        String randQuestion = "OBJ_5000";
        //randQuestion = randQuestion.substring(0, randQuestion.length() - Integer.toString(idNumber).length());

        //randID = randID.substring(0, randID.length() - Integer.toString(idNumber).length());
        //randID = randID + Integer.toString(idNumber);

        rootItem.setAttribute("ident", randQuestion + idNumber);
        rootItem.setAttribute("label", idNumber);

        Attr page = doc.createAttribute("d2l_2p0:page");
        page.setValue("1");
        rootItem.setAttributeNode(page);

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

        Element fieldLabel3 = doc.createElement("fieldlabel");
        fieldLabel3.appendChild(doc.createTextNode("qmd_weighting"));
        qtiDataField3.appendChild(fieldLabel3);

        Element fieldEntry3 = doc.createElement("fieldentry");

        // Some scores in Brainhoney aren't given a point value,
        // because they are only worth 1 point.  This just says
        // if they don't have a point value, we give it one point.
        if (brainhoney.getScore().isEmpty()) {
            fieldEntry3.appendChild(doc.createTextNode("1.000000000"));
        } else {
            fieldEntry3.appendChild(doc.createTextNode(brainhoney.getScore() + ".000000000"));
        }
        qtiDataField3.appendChild(fieldEntry3);

        Element fieldLabel4 = doc.createElement("fieldlabel");
        fieldLabel4.appendChild(doc.createTextNode("qmd_globalid"));
        qtiDataField4.appendChild(fieldLabel4);

        Element fieldEntry4 = doc.createElement("fieldentry");
        //String randVariable = "54e92f71-a948-44f1-83d1-71852872bef4";
        //randVariable = randVariable.substring(0, randVariable.length() - Integer.toString(idNumber).length());
        fieldEntry4.appendChild(doc.createTextNode(idNumber));
        qtiDataField4.appendChild(fieldEntry4);
        //idNumber++;

        Element fieldLabel5 = doc.createElement("fieldlabel");
        fieldLabel5.appendChild(doc.createTextNode("qmd_displayid"));
        qtiDataField5.appendChild(fieldLabel5);

        Element fieldEntry5 = doc.createElement("fieldentry");
        fieldEntry5.appendChild(doc.createTextNode("D2LSIM-CO-" + idNumber));
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

    }

    /**
     * WRITE BODY Creates the rest of the body that can't be done by
     * one-shotting it.
     */
    public void writeBody() {

    }

    /**
     * 
     * @return 
     */
    public BrainhoneyContents getBrainhoney() {
        return brainhoney;
    }

    /**
     * 
     * @param brainhoney 
     */
    public void setBrainhoney(BrainhoneyContents brainhoney) {
        this.brainhoney = brainhoney;
    }

    /**
     * 
     * @return 
     */
    public Document getDoc() {
        return doc;
    }

    /**
     * 
     * @param doc 
     */
    public void setDoc(Document doc) {
        this.doc = doc;
    }

    /**
     * 
     * @return 
     */
    public int getItemNumber() {
        return itemNumber;
    }

    /**
     * 
     * @param itemNumber 
     */
    public void setItemNumber(int itemNumber) {
        this.itemNumber = itemNumber;
    }

    /**
     * 
     * @return 
     */
    public int getQuestionNumber() {
        return questionNumber;
    }

    /**
     * 
     * @param questionNumber 
     */
    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    /**
     * 
     * @return 
     */
    public int getFeedbackNumber() {
        return feedbackNumber;
    }

    /**
     * 
     * @param feedbackNumber 
     */
    public void setFeedbackNumber(int feedbackNumber) {
        this.feedbackNumber = feedbackNumber;
    }

    /**
     * 
     * @return 
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * 
     * @param idNumber 
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * 
     * @return 
     */
    public Element getItem() {
        return rootItem;
    }

    /**
     * 
     * @param section 
     */
    public void setItem(Element section) {
        this.rootItem = section;
    }


    /**
     * 
     * @return 
     */
    public Element getRootItem() {
        return rootItem;
    }

    /**
     * 
     * @param rootItem 
     */
    public void setRootItem(Element rootItem) {
        this.rootItem = rootItem;
    }

    /**
     * 
     * @return 
     */
    public String getbID() {
        return bID;
    }

    /**
     * 
     * @param bID 
     */
    public void setbID(String bID) {
        this.bID = bID;
    }

}
