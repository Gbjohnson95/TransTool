/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Items;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Discussion Board
 *  Takes in information and outputs a discussion board with that information.
 * @author hallm8
 */
public class DiscussionBoard extends Item {

    private String did;
    private String dident;

    @Override
    public void writeItem() {
        itemType = "Discussion";
        materialType = "d2ldiscussion";
        try {

            // Standard DOM procedures
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder;

            docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();

            Element discussion = doc.createElement("discussion");
            Element forum = doc.createElement("forum");

            doc.appendChild(discussion);
            discussion.appendChild(forum);

            forum.setAttribute("id", itemID);
            forum.setAttribute("resource_code", "byui_produ-" + ident);

            // Since Brainhoney doesn't use Topics like I-Learn 3.0 does, we will always only have one topic.
            Element properties = doc.createElement("properties");
            Element content = doc.createElement("content");
            Element topics = doc.createElement("topics");

            forum.appendChild(properties);
            forum.appendChild(content);
            forum.appendChild(topics);

            Element allow_anon = doc.createElement("allow_anon");
            Element display_order = doc.createElement("display_order");
            Element is_hidden = doc.createElement("is_hidden");
            Element requires_approval = doc.createElement("requires_approval");
            Element is_locked = doc.createElement("is_locked");
            Element must_post = doc.createElement("must_post_to_participate");

            properties.appendChild(allow_anon);
            properties.appendChild(display_order);
            properties.appendChild(is_hidden);
            properties.appendChild(requires_approval);
            properties.appendChild(is_locked);
            properties.appendChild(must_post);

            allow_anon.setTextContent("False");
            display_order.setTextContent("1");
            is_hidden.setTextContent("False");
            requires_approval.setTextContent("False");
            is_locked.setTextContent("False");
            must_post.setTextContent("False");

            Element title = doc.createElement("title");
            content.appendChild(title);
            title.setTextContent(name);

            Element topic = doc.createElement("topic");
            topics.appendChild(topic);

            forum.setAttribute("id", did);
            forum.setAttribute("resource_code", "byui_produ-" + dident);

            Element properties2 = doc.createElement("properties");
            Element allow_anon2 = doc.createElement("allow_anon");
            Element display_order2 = doc.createElement("display_order");
            Element is_hidden2 = doc.createElement("is_hidden");
            Element requires_approval2 = doc.createElement("requires_approval");
            Element is_locked2 = doc.createElement("is_locked");
            Element must_post2 = doc.createElement("must_post_to_participate");
            if (gradeable.equals("true")){
                Element gItem = doc.createElement("grade_item_id");
                Element nonInclude = doc.createElement("include_nonscored_values");
                
                properties2.appendChild(gItem);
                properties2.appendChild(nonInclude);
                
                gItem.setTextContent(gradeAssociation);
                nonInclude.setTextContent("False");
            }

            topic.appendChild(properties2);
            properties2.appendChild(allow_anon2);
            properties2.appendChild(display_order2);
            properties2.appendChild(is_hidden2);
            properties2.appendChild(requires_approval2);
            properties2.appendChild(is_locked2);
            properties2.appendChild(must_post2);

            allow_anon2.setTextContent("False");
            display_order2.setTextContent("1");
            is_hidden2.setTextContent("False");
            requires_approval2.setTextContent("False");
            is_locked2.setTextContent("False");
            must_post2.setTextContent("False");

            Element content2 = doc.createElement("content");
            Element title2 = doc.createElement("title");
            topic.appendChild(content2);
            content2.appendChild(title2);
            title2.setTextContent(name);

            itemID = did;
            ident = dident;

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            href = "discussion_d2l_" + itemID + ".xml";
            
            pathAndName = savePath + "\\discussion_d2l_" + itemID + ".xml";
            StreamResult result = new StreamResult(new File(pathAndName));

            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException ex) {
            System.out.println("Error!!! Unable to save file! Something wrong!!");
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(QuizItem.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TransformerException ex) {
            Logger.getLogger(QuizItem.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * D ID
     *  Identification number of the Discussion Board
     * @return 
     */
    public String getDid() {
        return did;
    }

    /**
     * D ID
     *  Identification number of the Discussion Board
     * @param did 
     */
    public void setDid(String did) {
        this.did = did;
    }

    /**
     * D Ident
     *  Each item is attached to an ID# and an Identification.  I don't know why.
     * But each one is different, so this is the reference number for it.
     * @return 
     */
    public String getDident() {
        return dident;
    }

    /**
     * D Ident
     *  Each item is attached to an ID# and an Identification.  I don't know why.
     * But each one is different, so this is the reference number for it.
     * @param dident 
     */
    public void setDident(String dident) {
        this.dident = dident;
    }
    
    
    
}
