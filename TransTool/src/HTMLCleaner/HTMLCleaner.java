/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HTMLCleaner;

import Items.Item;
import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

/**
 *
 * @author hallm8
 */
public class HTMLCleaner {

    Item item;

    public HTMLCleaner() {

        HtmlCleaner htmlCleaner = new HtmlCleaner();
        CleanerProperties properties = htmlCleaner.getProperties();

        TagNode root = htmlCleaner.clean(item.getLocation());
        
    }

}
