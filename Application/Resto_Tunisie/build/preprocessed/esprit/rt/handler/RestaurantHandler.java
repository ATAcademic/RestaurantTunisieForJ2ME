package esprit.rt.handler;

import esprit.rt.entities.Restaurant;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
//Handler sert a faire la liaison entre xml et la classe Personne
public class RestaurantHandler extends DefaultHandler {

    private Vector restaurants;
    String idTag = "close";
    String nomTag = "close";
    String regionTag = "close";
    String adresseTag = "close";
    String typeTag = "close";
    String numTelTag = "close";
    String noteTag = "close";

    public RestaurantHandler() {
        restaurants = new Vector();
    }

    public Restaurant[] getRestaurant() {
        Restaurant[] restaurantss = new Restaurant[restaurants.size()];
        restaurants.copyInto(restaurantss);
        return restaurantss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Restaurant currentRestaurant;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //qName : nom de la balise
        if (qName.equals("personne")) {

            if (currentRestaurant != null) {
                throw new IllegalStateException("already processing a restaurants");
            }
            currentRestaurant = new Restaurant();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("region")) {
            regionTag = "open";
        } else if (qName.equals("adresse")) {
            adresseTag = "open";
        } else if (qName.equals("type")) {
            typeTag = "open";
        } else if (qName.equals("num_tel")) {
            numTelTag = "open";
        }else if (qName.equals("somme")) {
            noteTag = "open";
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("personne")) {
            // we are no longer processing a <reg.../> tag
            restaurants.addElement(currentRestaurant);
            currentRestaurant = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("region")) {
            regionTag = "close";
        } else if (qName.equals("adresse")) {
            adresseTag = "close";
        } else if (qName.equals("type")) {
            typeTag = "close";
        } else if (qName.equals("num_tel")) {
            numTelTag = "close";
        }else if (qName.equals("somme")) {
            noteTag = "close";
        }
        

    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        //La méthode characters , endElement et startElement sont des  méthodes définis par SAXException

        // we're only interested in this inside a <phone.../> tag
        if (currentRestaurant != null) {
            // don't forget to trim excess spaces from the ends of the string

            if (regionTag.equals("open")) {
                String region = new String(ch, start, length).trim();
                currentRestaurant.setRegion(region);
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentRestaurant.setNom(nom);
            } else if (adresseTag.equals("open")) {
                String adresse = new String(ch, start, length).trim();
                currentRestaurant.setAdresse(adresse);
            } else if (typeTag.equals("open")) {
                String type = new String(ch, start, length).trim();
                currentRestaurant.setType(type);
            } else if (numTelTag.equals("open")) {
                String numTel = new String(ch, start, length).trim();
                currentRestaurant.setNumTel(numTel);
            } else if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentRestaurant.setId(id);
            }else if (noteTag.equals("open")) {
                String note = new String(ch, start, length).trim();
                currentRestaurant.setNote(note);
            }
        }
    }

}
