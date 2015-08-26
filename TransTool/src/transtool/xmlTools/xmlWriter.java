package transtool.xmlTools;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class xmlWriter {
    // TO-DO
    // Create virtual doc & and make root element
    //      - newXmlDoc()
    // Create new element such as quiz question
    //      - newElement(element, atributes[])
    // Write to file
    //      - writeDoc(new File("C:\\file.xml")

    private StringWriter stringWriter;
    private XMLOutputFactory xMLOutputFactory;
    private XMLStreamWriter xMLStreamWriter;

    public void xmlWriter() throws XMLStreamException {
        stringWriter = new StringWriter();
        xMLOutputFactory = XMLOutputFactory.newInstance();
        xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
        xMLStreamWriter.writeStartDocument();

    }

    public void newElement(String newElementName, String newElementAtribute[][]) throws XMLStreamException {
        xMLStreamWriter.writeStartElement(newElementName);
        for (int a = 0; a <= newElementAtribute.length; a++) {
            xMLStreamWriter.writeAttribute(newElementAtribute[a][0], newElementAtribute[a][1]);
        }
    }

    public void childElement(String newElement) {
        //Code
    }
}
