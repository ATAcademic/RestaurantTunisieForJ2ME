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
public class RestaurantHandler1 extends DefaultHandler {

    private Vector restaurants;
    String id = "close";
    String nom = "close";
    String adresse = "close";
    String cordonnee = "close";
    String num_tel = "close";
    String description = "close";
    String url = "close";
    String type = "close";
    String id_restaurateur = "close";
    String region = "close";
    
  
    
    
    
    public RestaurantHandler1() {
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
        if (qName.equals("restaurant")) {

            if (currentRestaurant != null) {
                throw new IllegalStateException("already processing a restaurants");
            }
            currentRestaurant = new Restaurant();
        } else if (qName.equals("id")) {
            id = "open";
        } else if (qName.equals("nom")) {
            nom = "open";
        } else if (qName.equals("adresse")) {
            adresse = "open";
        } else if (qName.equals("cordonnee")) {
            cordonnee = "open";
        } else if (qName.equals("num_tel")) {
            num_tel = "open";
        } else if (qName.equals("description")) {
            description = "open";
        }
        else if (qName.equals("url")) {
            url = "open";
        }
        else if (qName.equals("type")) {
            type = "open";
        }
        else if (qName.equals("id_restaurateur")) {
            id_restaurateur = "open";
        }
        else if (qName.equals("region")) {
            region = "open";
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("restaurant")) {
            // we are no longer processing a <reg.../> tag
            restaurants.addElement(currentRestaurant);
            currentRestaurant = null;
        } else if (qName.equals("id")) {
            id = "close";
        } else if (qName.equals("nom")) {
            nom = "close";
        } else if (qName.equals("adresse")) {
            adresse = "close";
        } else if (qName.equals("cordonnee")) {
            cordonnee = "close";
        } else if (qName.equals("num_tel")) {
            num_tel = "close";
        } else if (qName.equals("description")) {
            description = "close";
        }
        else if (qName.equals("url")) {
            url = "close";
        }
        else if (qName.equals("type")) {
            type = "close";
        }
        else if (qName.equals("id_restaurateur")) {
            id_restaurateur = "close";
        }
        else if (qName.equals("region")) {
            region = "close";
        }

    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        //La méthode characters , endElement et startElement sont des  méthodes définis par SAXException

        // we're only interested in this inside a <phone.../> tag
        if (currentRestaurant != null) {
            // don't forget to trim excess spaces from the ends of the string

            if (id.equals("open")) {
                String idd = new String(ch, start, length).trim();
                currentRestaurant.setId(Integer.parseInt(idd));
            } else if (nom.equals("open")) {
                String noma = new String(ch, start, length).trim();
                currentRestaurant.setNom(noma);
            } else if (adresse.equals("open")) {
                String adressea = new String(ch, start, length).trim();
                currentRestaurant.setAdresse(adressea);
            } else if (cordonnee.equals("open")) {
                String cordonneea = new String(ch, start, length).trim();
                currentRestaurant.setCordonnee(cordonneea);
            } else if (num_tel.equals("open")) {
                String num_tela = new String(ch, start, length).trim();
                currentRestaurant.setNumTel(Integer.parseInt(num_tela));
            } else if (description.equals("open")) {
                String descriptiona = new String(ch, start, length).trim();
                currentRestaurant.setDescription(descriptiona);
            }
            else if (url.equals("open")) {
                String urla = new String(ch, start, length).trim();
                currentRestaurant.setUrl(urla);
            } else if (type.equals("open")) {
                String typea = new String(ch, start, length).trim();
                currentRestaurant.setType(typea);
            }
            else if (id_restaurateur.equals("open")) {
                String id_restaurateura = new String(ch, start, length).trim();
                currentRestaurant.setId_restaurateur(Integer.parseInt(id_restaurateura));
            } else if (region.equals("open")) {
                String regiona = new String(ch, start, length).trim();
                currentRestaurant.setRegion(regiona);
            }
        }
    }

}
