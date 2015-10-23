package tn.resto.xml;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import tn.resto.map.entities.Restaurant;

/**
 *
 * @author Wael Mallek
 */
public class RestaurantHandlerOuss extends DefaultHandler{
    private Vector restaurants;
    String idTag = "close";
    String nomTag = "close";
    String noteTag = "close";

    public RestaurantHandlerOuss() {
        restaurants = new Vector();
    }

    public Vector getRestaurant() {
        return restaurants;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Restaurant currentRestaurant;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElemepartnt is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("restaurant")) {

            if (currentRestaurant != null) {
                throw new IllegalStateException("already processing a personnes");
            }
            currentRestaurant = new Restaurant();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            noteTag = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("restaurant")) {
            // we are no longer processing a <reg.../> tag
            restaurants.addElement(currentRestaurant);
            currentRestaurant = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            noteTag = "close";
        }
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentRestaurant != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentRestaurant.setId(Integer.parseInt(id));
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentRestaurant.setNom(nom);
            } else
                    if (noteTag.equals("open")) {
                String note = new String(ch, start, length).trim();
                currentRestaurant.setNote(Double.parseDouble(note));
            }
        }
    }
    
}
