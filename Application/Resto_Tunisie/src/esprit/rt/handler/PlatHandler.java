package esprit.rt.handler;



import esprit.rt.entities.Plat;
import java.util.Vector;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
public class PlatHandler extends DefaultHandler {

    private final Vector plat;
    private String id = "close";
    private String nom = "close";
    private String description = "close";
    private String type = "close";
    private String prix = "close";

    public PlatHandler() {
        plat = new Vector();
    }

    public Plat[] getPlat() {
        Plat[] platt = new Plat[plat.size()];
        plat.copyInto(platt);
        return platt;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Plat currentPlat;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("plat")) {

            if (currentPlat != null) {
                throw new IllegalStateException("already processing a Plats");
            }
            currentPlat = new Plat();
        } else if (qName.equals("id")) {
            id = "open";
        } else if (qName.equals("nom")) {
            nom = "open";
        } else if (qName.equals("description")) {
            description = "open";
        } else if (qName.equals("type")) {
            type = "open";
        } else if (qName.equals("prix")) {
            prix = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("plat")) {
            // we are no longer processing a <reg.../> tag
            plat.addElement(currentPlat);
            currentPlat = null;
        } else if (qName.equals("id")) {
            id = "close";
        } else if (qName.equals("nom")) {
            nom = "close";
        } else if (qName.equals("description")) {
            description = "close";
        } else if (qName.equals("type")) {
            type = "close";
        } else if (qName.equals("prix")) {
            prix = "close";
        }

    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPlat != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (id.equals("open")) {
                String idd = new String(ch, start, length).trim();
                currentPlat.setId(Integer.parseInt(idd));
            } else if (nom.equals("open")) {
                String noma = new String(ch, start, length).trim();
                currentPlat.setNom(noma);
            }
        } else if (description.equals("open")) {
            String descriptiona = new String(ch, start, length).trim();
            currentPlat.setDescription(descriptiona);
        } else if (type.equals("open")) {
            String typee = new String(ch, start, length).trim();
            currentPlat.setType(typee);
        } else if (prix.equals("open")) {
            String prixx = new String(ch, start, length).trim();
            currentPlat.setPrix(Integer.parseInt(prixx));
        }
    }
}
