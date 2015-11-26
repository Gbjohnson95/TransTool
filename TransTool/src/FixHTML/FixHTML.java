/**
 *
 *
 */
package FixHTML;

import Items.Item;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jsoup.Jsoup;
import org.w3c.dom.Document;

/**
 *
 * @author hallm8
 */
public class FixHTML {

    private Item item;

    /**
     * FIX HTML
     *
     * Will fix HTML. Basically, you run a string as a parameter, and it opens
     * the selected file. It will go through and remove any problems.
     *
     * The assumption is, and I still haven't delved into the HTML cleaner just
     * yet, is that it will parse through the file, similar to
     *
     * Not bad work here, I'm impressed. I may work on this some.....
     * engine.eval(new java.io.FileReader("welcome.js"));
     */
    public FixHTML() {

    }

    public void fixHTML() {
        File input = new File(item.getLocation());
        try {
            Document doc = (Document) Jsoup.parse(input, "UTF-8");
        } catch (IOException ex) {
            System.out.println("Error!! Unable to open file!");
        }

    }

}
