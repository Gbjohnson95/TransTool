/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import nu.xom.*;

/**
 *
 * @author gbjohnson
 */
public class Quiz2 {

    private int count = 1000;
    private int responseCounter = 0;
    private String questionIdent;
    private Element assesment;
    private Element section;
    private Element presentation;
    private Element item;
    private Element render_choice;
    private Element responce_lid;

    public Quiz2() throws TransformerException {
        beginAssesment("demo");
        newSection("main");

        newQuestion("Question", "text");
        newResponse("ResponceA", "100");
        newResponse("ResponceB", "-100");
        
        //newQuestion("test2", "text goes here");
        //newResponse("textA", "100");
        //newResponse("textb", "100");

        Document doc = new Document(assesment);
        String test = doc.toXML();

        System.out.println(prettyFormat(test, 4));

    }

    private void beginAssesment(String title) {
        assesment = new Element("assesment");
        Attribute assesmentTitle = new Attribute("title", title);
        Attribute assesmentIdent = new Attribute("ident", "ident");
        assesment.addAttribute(assesmentTitle);
        assesment.addAttribute(assesmentIdent);
    }

    private void newSection(String title) {
        section = new Element("section");
        Attribute sectionTitle = new Attribute("title", title);
        Attribute sectionIdent = new Attribute("ident", "ident");
        section.addAttribute(sectionTitle);
        section.addAttribute(sectionIdent);
        assesment.appendChild(section);
    }

    private void newQuestion(String questionTitle, String question) {
        questionIdent = ident("QUE_");
        item = new Element("item");
        Attribute itemTitle = new Attribute("title", questionTitle);
        Attribute itemIdent = new Attribute("ident", questionIdent);
        presentation = new Element("presentation");
        item.appendChild(presentation);
        Element material = new Element("material");
        presentation.appendChild(material);
        Element mattext = new Element("mattext");
        Attribute texttype = new Attribute("texttype", "text/html");
        material.appendChild(mattext);
        mattext.addAttribute(texttype);
        mattext.appendChild(question);
        section.appendChild(item);
    }

    private void newResponse(String responce, String value) {
        responseCounter++;
        String responce_lidIdent1 = questionIdent + "_RL";
        responce_lid = new Element("response_lid");
        Attribute responce_lidIdent = new Attribute("ident", responce_lidIdent1);
        responce_lid.addAttribute(responce_lidIdent);
        render_choice = new Element("render_choice");
        Element response_label = new Element("response_label");
        String questionResponseIdent = questionIdent + "_A" + responseCounter;
        Attribute response_lableIdent = new Attribute("ident", questionResponseIdent);
        Element material = new Element("material");
        Element mattext = new Element("mattext");
        Attribute texttype = new Attribute("texttype", "text/html");
        mattext.appendChild("responce");
        mattext.addAttribute(texttype);
        material.appendChild(mattext);
        response_label.appendChild(material);
        render_choice.appendChild(response_label);
        responce_lid.appendChild(render_choice);
        presentation.appendChild(responce_lid);
    }

    private String ident(String prefix, String suffix) {
        count++;
        String str = prefix + count + suffix;
        return str;
    }

    private String ident(String prefix) {
        count++;
        String str = prefix + count;
        return str;
    }

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
