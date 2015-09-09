/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transtool.quiz;

import nu.xom.*;

/**
 *
 * @author gbjohnson
 */
public class Quiz2 {

    private int count = 1000;
    private int responseCounter = 0;
    private String questionIdent;

    public Quiz2() {

    }

    public void beginAssesment(String title) {
        Element assesment = new Element("assesment");
        Attribute assesmentTitle = new Attribute("title", title);
        Attribute assesmentIdent = new Attribute("ident", "ident");
        assesment.addAttribute(assesmentTitle);
        assesment.addAttribute(assesmentIdent);
    }

    public void newSection(String title) {
        Element section = new Element("section");
        Attribute sectionTitle = new Attribute("title", title);
        Attribute sectionIdent = new Attribute("ident", "ident");
        section.addAttribute(sectionTitle);
        section.addAttribute(sectionIdent);
    }

    public void newQuestion(String questionTitle, String question) {
        questionIdent = ident("QUE_");
        
        Element item = new Element("item");
        Attribute itemTitle = new Attribute("title", questionTitle);
        Attribute itemIdent = new Attribute("ident", questionIdent);
        
        Element presentation = new Element("presentation");
        item.appendChild(presentation);
        
        Element material = new Element("material");
        item.appendChild(presentation);
        
        Element mattext = new Element("mattext");
        Attribute texttype = new Attribute("texttype", "text/html");
        mattext.addAttribute(texttype);
        
        
        
        
        

    }

    public void newRespoce() {

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
}
