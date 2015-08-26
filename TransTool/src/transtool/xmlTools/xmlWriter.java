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

    public void xmlWriter() throws XMLStreamException {
        stringWriter = new StringWriter();
        xMLOutputFactory = XMLOutputFactory.newInstance();
        xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
        xMLStreamWriter.writeStartDocument();

    }

    public void newElement(String newElementName, String newElementAtribute[][], String text) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
        for (int a = 0; a <= newElementAtribute.length; a++) {
            xMLStreamWriter.writeAttribute(newElementAtribute[a][0], newElementAtribute[a][1]);
        }
        xMLStreamWriter.writeCharacters(text);
    }

    public void newElement(String newElementName, String newElementAtribute[][]) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
        for (int a = 0; a <= newElementAtribute.length; a++) {
            xMLStreamWriter.writeAttribute(newElementAtribute[a][0], newElementAtribute[a][1]);
        }
    }

    public void newElement(String newElementName) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
    }

    public void closeElement() throws XMLStreamException {
        xMLStreamWriter.writeEndElement();
    }

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
