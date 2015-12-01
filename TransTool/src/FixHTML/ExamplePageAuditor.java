/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FixHTML;


import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author gbjohnson
 */
public class ExamplePageAuditor {

    private Document doc;
    private Element body;
    private String filepath, docTitle, htmlString;
    private Elements bs, is, images, spans, divs, titleE, brs, links;

    public void audit(String filename, String dTitle) throws IOException {
        File input = new File(filename);
        if (input.exists()) {
            doc = Jsoup.parse(input, "UTF-8");
            body = doc.getElementsByTag("body").first();

            // Set elements for program
            links = body.getElementsByTag("a");
            images = body.getElementsByTag("img");
            spans = body.getElementsByTag("span");
            divs = body.getElementsByTag("div");
            is = body.getElementsByTag("i");
            bs = body.getElementsByTag("b");
            brs = body.getElementsByTag("br");
            htmlString = doc.toString();
            titleE = doc.select("title");

            // Remove commas from the titles
            docTitle = dTitle.replace(",", "");
            filepath = filename.replace(",", "");
        }
    }

    /**
     * Returns the string with the metrics.
     *
     * @return
     */
    public String getMetrics() {
        String printString
                = docTitle + ","            // Title
                + getHTMLTitle() + ","      // HTML Title
                + numBHLinks() + ","        // BH Links
                + numBXLinks() + ","        // Box Links
                + numBadTargets() + ","     // Bad Link Targets
                + numEmptyLinks() + ","     // Empty Links
                + numBHImages() + ","       // BH Images
                + numBadImageWidth() + ","  // Image Width
                + numBolds() + ","          // Bolds
                + numSpans() + ","          // Spans
                + numBadTags() + ","        // Bad Tags
                + numDivs() + ","           // Divs
                + numBrs() + ","            // Br
                + countBHVars() + ","       // BHVars
                + mentionsSaturday() + ","  // Mentions Saturday
                + checkHeaders() + ","      // Headers
                + getTemplateName() + ","   // Template
                + checkFilePath() + ",\n";  // File Path
        return printString;
    }

    private String checkFilePath() {
        if (filepath.contains("Course Files")) {
            return "Good: " + filepath;
        } else {
            return "Bad: " + filepath;
        }
    }

    private String getHTMLTitle() {
        if (titleE.isEmpty()) {
            return "ERROR: COULD NOT READ TITLE";
        } else {
            String title = titleE.first().text().replace(",", "");
            if (title == null ? docTitle == null : title.toLowerCase().trim().equals(docTitle.trim().toLowerCase())) {
                return "Matching";
            } else {
                return title;
            }
        }
    }

    private String getTemplateName() {
        String returnString = "";
        for (Element img : images) {
            if (img.attr("alt").toLowerCase().contains("banner")) {
                if (img.attr("src").contains("largeBanner")) {
                    returnString = "Large";
                }
                if (img.attr("src").contains("smallBanner")) {
                    returnString = "Small";
                }
            }
        }
        return returnString;
    }

    private String mentionsSaturday() {
        String returnString = "No";
        Pattern dueSaturday = Pattern.compile("[sS]aturday");
        Matcher m = dueSaturday.matcher(htmlString);
        while (m.find()) {
            returnString = "Yes";
        }
        return returnString;
    }

    private int numBrs() {
        return brs.size();
    }

    private int countBHVars() {
        int BHVarsCounter = 0;
        Pattern findvars = Pattern.compile("\\$[A-Za-z]+\\S\\$");
        Matcher m = findvars.matcher(htmlString);
        while (m.find()) {
            BHVarsCounter++;
        }
        return BHVarsCounter;
    }

    private int numBadTags() {
        return bs.size() + is.size();
    }

    private int numBHLinks() {
        int bhlinksCounter = 0;
        bhlinksCounter = links.stream().map((a) -> a.attr("href")).filter((href) -> (href.toLowerCase().contains("brainhoney"))).map((_item) -> 1).reduce(bhlinksCounter, Integer::sum);
        return bhlinksCounter;
    }

    private int numBHImages() {
        int bhimgesCounter = 0;
        bhimgesCounter = images.stream().map((img) -> img.attr("src")).filter((src) -> (src.toLowerCase().contains("brainhoney"))).map((_item) -> 1).reduce(bhimgesCounter, Integer::sum);
        return bhimgesCounter;
    }

    private int numBXLinks() {
        int bxlinksCounter = 0;
        bxlinksCounter = links.stream().map((a) -> a.attr("href")).filter((href) -> (href.toLowerCase().contains("box.com"))).map((_item) -> 1).reduce(bxlinksCounter, Integer::sum);
        return bxlinksCounter;
    }

    private int numEmptyLinks() {
        int emptyLinks = 0;
        for (Element a : links) {
            boolean noHref = a.attr("href").isEmpty();
            boolean noLinkText = a.text().isEmpty();
            boolean hasHref = a.hasAttr("href");
            if (noHref == true || noLinkText == true || hasHref == false) {
                emptyLinks++;
            }
        }
        return emptyLinks;
    }

    private String checkHeaders() {
        String headers = "";
        if (!body.getElementsByTag("h1").isEmpty()) {
            headers += "1";
        }
        if (!body.getElementsByTag("h2").isEmpty()) {
            headers += "2";
        }
        if (!body.getElementsByTag("h3").isEmpty()) {
            headers += "3";
        }
        if (!body.getElementsByTag("h4").isEmpty()) {
            headers += "4";
        }
        if (!body.getElementsByTag("h5").isEmpty()) {
            headers += "5";
        }
        if (!body.getElementsByTag("h6").isEmpty()) {
            headers += "6";
        }
        if ("123456".indexOf(headers) == 0) {
            return "Good: " + headers;
        } else if ("".equals(headers)) {
            return "Bad: ";
        } else {
            return "Bad: " + headers;
        }
    }

    private int numBadTargets() {
        int tgCounter = 0;
        tgCounter = links.stream().filter((a) -> (!a.attr("target").toLowerCase().contains("_blank".toLowerCase()))).map((_item) -> 1).reduce(tgCounter, Integer::sum);
        return tgCounter;
    }

    private int numBadImageWidth() {
        int imgCounter = 0;
        for (Element img : images) {
            String width = img.attr("width");
            if (!width.toLowerCase().contains("%") && !img.attr("src").toLowerCase().contains("banner")) {
                imgCounter++;
            }
        }
        return imgCounter;
    }

    private int numSpans() {
        return spans.size();
    }

    private int numDivs() {
        int divCounter = 0;
        divCounter = divs.stream().filter((div) -> (!div.hasAttr("id"))).map((_item) -> 1).reduce(divCounter, Integer::sum);
        return divCounter;
    }

    private int numBolds() {
        int bCounter = 0;
        Pattern dueSaturday = Pattern.compile("font-weight\\: bold");
        Matcher m = dueSaturday.matcher(htmlString);
        while (m.find()) {
            bCounter++;
        }
        return bCounter;
    }
}
