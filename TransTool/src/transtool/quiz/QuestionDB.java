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
 * OTHER QUIZ Titled "Other Quiz" because Grant was working on another quiz
 * writer, instead going through the QTI standard as opposed to Brainhoney.
 *
 * VARIABLES: - Item Number - Each Brightspace import associates with an item
 * number - Question Number - Each Brightspace import also associates with a
 * question number. - Feedback Number - Each question number is associated with
 * a feedback ID as well. - ID number - Each question has a Question ID to link
 * back to quizzes by their ID. This number is pretty important.
 *
 * @author hallm8
 *
 */
public class QuestionDB {

    int itemNumber = 50000;
    int questionNumber = 50000;
    int feedbackNumber = 50000;
    int idNumber = 1;

    String toSave;

    /**
     * Constructor. Does pretty much everything for this program.
     *
     * @param quiz
     * @param savePath
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    public QuestionDB(ArrayList<transtool.xmlTools.Quiz> quiz, String savePath) throws TransformerConfigurationException, TransformerException {
        try {
            // Save file path.
            toSave = savePath;

            // This is a manifest builder that links to each quiz, including 
            // this question bank.
            WriteManifest manifest = new WriteManifest(toSave);
            manifest.buildManifest();

            // Standard DOM procedures
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = docFactory.newDocumentBuilder();

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

            // OK!! So here goes: The Brightspace XML document is convoluted at
            // best.  A huge portion of this XML is hard coded in, and you can
            // see that many, many variables are made.  However, the majority
            // of it is straightforward.  I will comment anything that is not.
            Attr attr2 = doc.createAttribute("ident");
            attr2.setValue("QLIB_1000");
            staff.setAttributeNode(attr2);

            // Each quiz question has been separated into quizzes.  So now, we
            // parse through each quiz and pull them out.  This has been done
            // so that we can separate them in the quiz bank.
            for (int l = 0; l < quiz.size(); l++) {
                ArrayList<BrainhoneyContents> brainhoney = quiz.get(l).getBrainhoney();
                Element section = doc.createElement("section");
                section.setAttribute("d2l_2p0:id", Integer.toString(idNumber));
                idNumber++;
                section.setAttribute("ident", "SECT_" + (l + 1));
                section.setAttribute("title", quiz.get(l).getQuizName());
                Element sectionproc = doc.createElement("sectionproc_extension");
                Element displaySectionName = doc.createElement("d2l_2p0:display_section_name");
                displaySectionName.appendChild(doc.createTextNode("no"));
                Element displaySectionLine = doc.createElement("d2l_2p0:display_section_line");
                displaySectionLine.appendChild(doc.createTextNode("no"));
                Element typeDisplaySection = doc.createElement("d2l_2p0:type_display_section");
                typeDisplaySection.appendChild(doc.createTextNode("0"));

                staff.appendChild(section);
                section.appendChild(sectionproc);
                sectionproc.appendChild(displaySectionName);
                sectionproc.appendChild(displaySectionLine);
                sectionproc.appendChild(typeDisplaySection);

                // each Brainhoney class is actually a single question
                for (BrainhoneyContents brainhoneyContent : brainhoney) {

                    switch (brainhoneyContent.getInteractionType()) {
                        case "choice":
                            MultipleChoice multipleChoice = new MultipleChoice(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = multipleChoice.getIdNumber();
                            itemNumber = multipleChoice.getItemNumber();
                            feedbackNumber = multipleChoice.getItemNumber();
                            questionNumber = multipleChoice.getItemNumber();
                            section.appendChild(multipleChoice.getItem());
                            break;
                        case "text":
                            /*
                            ShortAnswerQuestion shortAnswer = new ShortAnswerQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = shortAnswer.getIdNumber();
                            itemNumber = shortAnswer.getItemNumber();
                            feedbackNumber = shortAnswer.getItemNumber();
                            questionNumber = shortAnswer.getItemNumber();
                            section.appendChild(shortAnswer.getItem());
                            */
                            break;
                        case "essay":
                            LongAnswerQuestion longAnswer = new LongAnswerQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = longAnswer.getIdNumber();
                            itemNumber = longAnswer.getItemNumber();
                            feedbackNumber = longAnswer.getItemNumber();
                            questionNumber = longAnswer.getItemNumber();
                            section.appendChild(longAnswer.getItem());

                            break;
                        case "match":
/*
                            MatchingQuestion matchingQuestion = new MatchingQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = matchingQuestion.getIdNumber();
                            itemNumber = matchingQuestion.getItemNumber();
                            feedbackNumber = matchingQuestion.getItemNumber();
                            questionNumber = matchingQuestion.getItemNumber();
                            section.appendChild(matchingQuestion.getItem());
                            */
                            break;
                        case "order":
/*
                            OrderQuestion orderQuestion = new OrderQuestion(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = orderQuestion.getIdNumber();
                            itemNumber = orderQuestion.getItemNumber();
                            feedbackNumber = orderQuestion.getItemNumber();
                            questionNumber = orderQuestion.getItemNumber();
                            section.appendChild(orderQuestion.getItem());
                            */
                            break;

                        case "answer":
                            /*
                            MultiSelect multiSelect = new MultiSelect(brainhoneyContent, doc, idNumber, itemNumber, section);
                            idNumber = multiSelect.getIdNumber();
                            itemNumber = multiSelect.getItemNumber();
                            feedbackNumber = multiSelect.getItemNumber();
                            questionNumber = multiSelect.getItemNumber();
                            section.appendChild(multiSelect.getItem());
                             */
                            break;

                        case "custom":
                        case "composite":
                            break;
                    }
                }
            }
            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(toSave + "\\questiondb.xml"));

		// Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error!!! Unable to save file! Something wrong!!");
        }
    }
}
