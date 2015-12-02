/**
 *
 *
 */
package FixHTML;

import Items.Item;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

/**
 *
 * @author hallm8
 */
public class FixHTML {

    private Item item;
    private String filePath;
    private String bodyText = "";

    /**
     * FIX HTML
     *
     * Will fix HTML. Basically, you run a string as a parameter, and it opens
     * the selected file. It will go through and remove any problems.
     *
     */
    public FixHTML() {

    }

    public void fix() {
        String brainhoneyPath = item.getBrainhoneyPath();
        String fileName = new String();
        String appendedPath = new String();

        for (int i = 0; i < brainhoneyPath.length(); i++) {
            fileName += brainhoneyPath.charAt(i);
            if (brainhoneyPath.charAt(i) == '\\') {
                appendedPath += fileName;
                fileName = new String();
            }
        }
        appendedPath += "resources\\";

        File input = new File(appendedPath + item.getLocation());
        if (input.exists()) {
            try {

                Document doc = Jsoup.parse(input, "UTF-8");

                //First, let's set up things until the body text.
                Element html = doc.getElementsByTag("html").first();
                html.attr("lang", "en-us");

                Element head = doc.getElementsByTag("head").first();

                if (!head.hasText()) {
                    head.append("<meta charset=\"utf-8\">\n"
                            + "	<title>" + item.getName() + "</title>\n"
                            + "	<link rel=\"stylesheet\" href=\"{OrgUnitPath} Web Files/HTML/../css/styles.css\" />\n"
                            + "	<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js\"></script>\n"
                            + "	<script src=\"{OrgUnitPath} Web Files/HTML/../js/online.js\"></script>\n"
                            + "	<script src=\"{OrgUnitPath} Web Files/HTML/../js/course.js\"></script>");
                }
                // Replace bolds
                doc.getElementsByTag("b").tagName("strong");
                // Replace italics.
                doc.getElementsByTag("i").tagName("em");

                // Replace divs with P tags.
                doc.getElementsByTag("div").tagName("p");

                Whitelist white = Whitelist.basicWithImages();
                white.preserveRelativeLinks(true);
                white.removeTags("span");
                String test = Jsoup.clean(doc.body().html(), white);

                // Replacing common variables.  Replacing them to accomodate online
                // classes.  
                test = test.replace("$ITEMNAME$", item.getName());
                test = test.replace("$SAT$", "<strong>Due: Monday, see <a href=\"/d2l/le/calendar/ {OrgUnitId} \" target=\"_blank\">Calendar</a>&nbsp;for times</strong>");
                test = test.replace("$FRI$", "<strong>Due: Friday, see <a href=\"/d2l/le/calendar/ {OrgUnitId} \" target=\"_blank\">Calendar</a>&nbsp;for times</strong>");
                test = test.replace("$THUR$", "<strong>Due: Thursday, see <a href=\"/d2l/le/calendar/ {OrgUnitId} \" target=\"_blank\">Calendar</a>&nbsp;for times</strong>");
                test = test.replace("$WED$", "<strong>Due: Wednesday, see <a href=\"/d2l/le/calendar/ {OrgUnitId} \" target=\"_blank\">Calendar</a>&nbsp;for times</strong>");
                test = test.replace("$TUE$", "<strong>Due: Tuesday, see <a href=\"/d2l/le/calendar/ {OrgUnitId} \" target=\"_blank\">Calendar</a>&nbsp;for times</strong>");
                test = test.replace("$MON$", "<strong>Due: Monday, see <a href=\"/d2l/le/calendar/ {OrgUnitId} \" target=\"_blank\">Calendar</a>&nbsp;for times</strong>");
                
                

                Element body = doc.createElement("body");
                body.append(test);

                Elements emptySearch = body.getAllElements();

                emptySearch.stream().filter((empty) -> (!empty.hasText() && !empty.tagName().equals("head") && !empty.tagName().equals("body"))).forEach((empty) -> {
                    empty.unwrap();
                });

                doc.getElementsByTag("body").remove();
                html.appendChild(body);

                bodyText = doc.html();

            } catch (IOException ex) {
                System.out.println("Error!! Unable to open file!");
            }
        }
    }

    public void writeHTML() {
        PrintWriter writer;
        try {
            writer = new PrintWriter(filePath + "\\content\\" + item.getName() + ".html", "UTF-8");
            writer.println(bodyText);
            writer.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FixHTML.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(FixHTML.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

}
