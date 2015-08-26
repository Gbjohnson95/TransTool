package transtool.xmlTools;

import java.io.IOException;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class xmlWriter {

    private StringWriter stringWriter;
    private XMLOutputFactory xMLOutputFactory;
    private XMLStreamWriter xMLStreamWriter;

    /**
     * Creates XMLStreamWriter and starts document.
     *
     * @throws XMLStreamException
     */
    public void xmlWriter() throws XMLStreamException {
        stringWriter = new StringWriter();
        xMLOutputFactory = XMLOutputFactory.newInstance();
        xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
        xMLStreamWriter.writeStartDocument();

    }

    /**
     * Creates a new element with an array of Atributes, and some text to put
     * in the element.
     * 
     *
     * @param newElementName
     * @param newElementAtribute
     * @param text
     * @throws XMLStreamException
     */
    public void newElement(String newElementName, String newElementAtribute[][], String text) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
        for (int a = 0; a <= newElementAtribute.length; a++) {
            xMLStreamWriter.writeAttribute(newElementAtribute[a][0], newElementAtribute[a][1]);
        }
        xMLStreamWriter.writeCharacters(text);
    }
    
    /**
     * Creates an element with no atributes, along with some text. 
     *
     * @param newElementName
     * @param text
     * @throws XMLStreamException
     */
    public void newElement(String newElementName, String text) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
        xMLStreamWriter.writeCharacters(text);
    }

    /**
     * Creates a new element with just atributes.
     *
     * @param newElementName
     * @param newElementAtribute
     * @throws XMLStreamException
     */
    public void newElement(String newElementName, String newElementAtribute[][]) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
        for (int a = 0; a <= newElementAtribute.length; a++) {
            xMLStreamWriter.writeAttribute(newElementAtribute[a][0], newElementAtribute[a][1]);
        }
    }

    /**
     * Creates just an element.
     *
     * @param newElementName
     * @throws XMLStreamException
     */
    public void newElement(String newElementName) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
    }

    /**
     * Closes element.
     *
     * @throws XMLStreamException
     */
    public void closeElement() throws XMLStreamException {
        xMLStreamWriter.writeEndElement();
    }

    /**
     * Generates a string that is returned, so that you can write it to a file,
     * as well as cleaning up some stuff. 
     *
     * @return
     */
    public String toStringAndClose() {

        try {
            xMLStreamWriter.flush();
        } catch (XMLStreamException ex) {
            Logger.getLogger(xmlWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            xMLStreamWriter.close();
        } catch (XMLStreamException ex) {
            Logger.getLogger(xmlWriter.class.getName()).log(Level.SEVERE, null, ex);
        }
        String xmlString = stringWriter.getBuffer().toString();
        try {
            stringWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(xmlWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return xmlString;
    }
}
