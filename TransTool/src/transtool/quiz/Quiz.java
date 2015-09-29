package transtool.quiz;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.*;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import nu.xom.*;

/**
 * A class for building Quizzes.
 *
 * @author gbjohnson
 */
public class Quiz {

    private int count = 1000;
    private int responseCounter = 0;

    private String questionIdent;
    private String responseLidIdent;
    private String questionResponseIdent;

    private Element assesment;
    private Element section;
    private Element item;
    private Element presentation;
    private Element response_lid;
    private Element render_choice;
    private Element resprocessing;
    private Element respcondition;

    /**
     * Generates a QTI format quiz.
     *
     * @throws TransformerException
     */
    public Quiz() throws TransformerException {
        // Starts the document.
        beginAssesment("demo");
        newSection("main");
    }

    /**
     * Prints to a terminal.
     *
     * @throws TransformerException
     */
    public void print() throws TransformerException {
        System.out.print(getString());
    }

    /**
     * Returns a string of the XML document.
     *
     * @return
     * @throws TransformerException
     */
    public String getString() throws TransformerException {
        // Genrates a document, and puts the document into a String.
        Document doc = new Document(assesment);
        String xmlText = doc.toXML();

        // Fixes the HTML tags, could be an issue with math questions, which
        // at this point it doesn't support....
        xmlText = xmlText.replaceAll("&lt;", "<");
        xmlText = xmlText.replaceAll("&gt;", ">");

        // Prints out a pretty formated document to terminal windows. The key 
        // code is "prettyFormat(xmlText, 4);"
        return prettyFormat(xmlText, 4);
    }

    // Lets you set the name of the assesment. 
    private void beginAssesment(String title) {
        assesment = new Element("assesment");

        Attribute assesmentTitle = new Attribute("title", title);
        assesment.addAttribute(assesmentTitle);
        Attribute assesmentIdent = new Attribute("ident", "ident");
        assesment.addAttribute(assesmentIdent);
    }

    // Lets you set the name of the section.
    private void newSection(String title) {
        section = new Element("section");
        Attribute sectionTitle = new Attribute("title", title);
        section.addAttribute(sectionTitle);
        ++count;
        Attribute sectionIdent = new Attribute("ident", "ident");
        section.addAttribute(sectionIdent);

        assesment.appendChild(section);
    }

    /**
     * Makes a new Question, should probably implement a question weight int....
     *
     * @param questionTitle
     * @param question
     */
    public void newQuestion(String questionTitle, String question) {
        // General set up.
        questionIdent = ident("QUE_");
        responseCounter = 0;

        // Generates a new item. 
        item = new Element("item");
        Attribute itemTitle = new Attribute("title", questionTitle);
        item.addAttribute(itemTitle);
        Attribute itemIdent = new Attribute("ident", questionIdent);
        item.addAttribute(itemIdent);

        // The code here is whats presented to the user. -----------------------
        presentation = new Element("presentation");

        Element material = new Element("material");

        Element mattext = new Element("mattext");
        Attribute texttype = new Attribute("texttype", "text/html");
        mattext.addAttribute(texttype);

        responseLidIdent = questionIdent + "_RL";
        response_lid = new Element("response_lid");
        Attribute response_lidIdent = new Attribute("ident", responseLidIdent);
        response_lid.addAttribute(response_lidIdent);

        render_choice = new Element("render_choice");

        section.appendChild(item);
        item.appendChild(presentation);
        presentation.appendChild(material);
        material.appendChild(mattext);
        mattext.appendChild(question);
        response_lid.appendChild(render_choice);
        presentation.appendChild(response_lid);

        // From here down is response processing -------------------------------
        resprocessing = new Element("resprocessing");
        Element outcomes = new Element("outcomes");

        Element decvar = new Element("decvar");

        Attribute vartype = new Attribute("vartype", "Integer");
        Attribute defaultval = new Attribute("defaultval", "0");
        Attribute varname = new Attribute("varname", "que_score");
        Attribute maxvalue = new Attribute("maxvalue", "100");
        Attribute minvalue = new Attribute("minvalue", "0");

        decvar.addAttribute(vartype);
        decvar.addAttribute(defaultval);
        decvar.addAttribute(varname);
        decvar.addAttribute(maxvalue);
        decvar.addAttribute(minvalue);

        item.appendChild(resprocessing);
        resprocessing.appendChild(outcomes);
        outcomes.appendChild(decvar);
    }

    /**
     * Generates a new response, and gets the value for the response.
     *
     * @param response
     * @param value
     */
    public void newResponse(String response, String value) {
        responseCounter++;
        questionResponseIdent = questionIdent + "_A" + responseCounter;

        Element response_label = new Element("response_label");
        Attribute response_labelIdent = new Attribute("ident", questionResponseIdent);
        response_label.addAttribute(response_labelIdent);

        Element material = new Element("material");

        Element mattext = new Element("mattext");
        Attribute texttype = new Attribute("texttype", "text/html");
        mattext.addAttribute(texttype);
        mattext.appendChild(response);

        material.appendChild(mattext);
        response_label.appendChild(material);
        render_choice.appendChild(response_label);

        // From here down is resprocessing -------------------------------------
        respcondition = new Element("respcondition");
        Element conditionvar = new Element("conditionvar");

        Element varequal = new Element("varequal");
        Attribute respident = new Attribute("respident", responseLidIdent);
        varequal.appendChild(questionResponseIdent);
        varequal.addAttribute(respident);

        Element setvar = new Element("setvar");
        Attribute varname = new Attribute("varname", "que_score");
        Attribute action = new Attribute("action", "add");
        setvar.appendChild(value);
        setvar.addAttribute(varname);
        setvar.addAttribute(action);

        resprocessing.appendChild(respcondition);
        respcondition.appendChild(conditionvar);
        conditionvar.appendChild(varequal);
        respcondition.appendChild(setvar);

    }

    // Helps set the ident of things.
    private String ident(String prefix, String suffix) {
        count++;
        String str = prefix + count + suffix;
        return str;
    }

    public void newFeedback(String feedback) {
        String feedbackIdent = questionResponseIdent + "_FB";

        Element displayfeedback = new Element("displayfeedback");
        Attribute feedbacktype = new Attribute("feedbacktype", "Response");
        Attribute linkrefid = new Attribute("linkrefid", feedbackIdent);
        displayfeedback.addAttribute(feedbacktype);
        displayfeedback.addAttribute(linkrefid);
        respcondition.appendChild(displayfeedback);

        Element itemfeedback = new Element("itemfeedback");
        Attribute ident = new Attribute("ident", feedbackIdent);
        Attribute view = new Attribute("view", "Canidate");
        itemfeedback.addAttribute(ident);
        itemfeedback.addAttribute(view);

        Element material = new Element("material");
        Element mattext = new Element("mattext");
        Attribute texttype = new Attribute("texttype", "text/html");
        mattext.addAttribute(texttype);
        mattext.appendChild(feedback);
        material.appendChild(mattext);
        itemfeedback.appendChild(material);
        item.appendChild(itemfeedback);

    }

    // Helps set the ident of things.
    private String ident(String prefix) {
        count++;
        String str = prefix + count;
        return str;
    }

    // Prints pretty XML.
    private static String prettyFormat(String input, int indent) throws TransformerConfigurationException, TransformerException {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (IllegalArgumentException | TransformerException e) {
            throw new RuntimeException(e);
        }
    }
}
