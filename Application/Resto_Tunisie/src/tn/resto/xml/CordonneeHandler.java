package tn.resto.xml;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
public class CordonneeHandler extends DefaultHandler{
    private Vector cordonnees;
    String cordsTag = "close";

    public CordonneeHandler() {
        cordonnees = new Vector();
    }

    public Vector getCords() {
        System.out.println(cordonnees.size());
        if (cordonnees.size() == 0 )
            return null;
        return cordonnees;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private String currentCord ;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElemepartnt is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

            if (qName.equals("big")) {

            if (currentCord != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            currentCord = "";
        } else if (qName.equals("cords")) {
            cordsTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("big")) {
            // we are no longer processing a <reg.../> tag
            System.out.println(currentCord);
            
            currentCord = null;
        } else if (qName.equals("cords")) {
            cordsTag = "close";
        } 
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentCord != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (cordsTag.equals("open")) {
                currentCord = new String(ch, start, length).trim();
                cordonnees.addElement(currentCord);
            } 
        }
    }
    
}
