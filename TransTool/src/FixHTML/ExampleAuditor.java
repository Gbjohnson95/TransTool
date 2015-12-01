/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixHTML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.select.Elements;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 *
 * @author hallm8
 */
public class ExampleAuditor {

    public ExampleAuditor() {
        String content;
        try {
            content = new Scanner(new File("imsmanifest.xml")).useDelimiter("\\Z").next();
            Document xmlDoc = Jsoup.parse(content, "", Parser.xmlParser());

            Elements resources = xmlDoc.select("resource");
            Elements items = xmlDoc.getElementsByTag("item");
            String printString = "Title,HTML Title,BH Links,Box Links,Bad Link "
                    + "Targets,Empty Links,BH Images,Image Width,Bolds,Spans,Bad"
                    + " Tags,Divs,Br,BHVars,Mentions Saturday,Header Order,"
                    + "Template,Filepath\n";
            String title;
            ExamplePageAuditor audit = new ExamplePageAuditor();
            for (Element e : resources) {
                String type = e.attr("d2l_2p0:material_type");
                if ("content".equals(type)) {
                    String ident = e.attr("identifier");
                    String fpath = e.attr("href");
                    for (Element d : items) {
                        if (d.hasAttr("identifierref")) {
                            String ident1 = d.attr("identifierref");
                            if (ident1.equals(ident) && fpath.contains(".html")) { // Only html gets passed to the html
                                title = d.child(0).ownText();
                                audit.audit(fpath, title);
                                printString += audit.getMetrics();
                            }
                        }
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ExampleAuditor.class.getName()).log(Level.SEVERE, null, ex);

        } catch (IOException ex) {
            Logger.getLogger(ExampleAuditor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
