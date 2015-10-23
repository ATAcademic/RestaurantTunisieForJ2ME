package esprit.rt.handler;


import esprit.rt.entities.Menu;
import java.util.Vector;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
public class MenuHandler extends DefaultHandler {

    private final Vector menu;
    private String id = "close";
    private String nom = "close";
    private String description = "close";

    public MenuHandler() {
        menu = new Vector();
    }

    public Menu[] getMenu() {
        Menu[] menuu = new Menu[menu.size()];
        menu.copyInto(menuu);
        return menuu;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Menu currentMenu;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("menu")) {

            if (currentMenu != null) {
                throw new IllegalStateException("already processing a Menus");
            }
            currentMenu = new Menu();
        } else if (qName.equals("id")) {
            id = "open";
        } else if (qName.equals("nom")) {
            nom = "open";
        } else if (qName.equals("description")) {
            description = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("menu")) {
            // we are no longer processing a <reg.../> tag
            menu.addElement(currentMenu);
            currentMenu = null;
        } else if (qName.equals("id")) {
            id = "close";
        } else if (qName.equals("nom")) {
            nom = "close";
        } else if (qName.equals("description")) {
            description = "close";
        } 
        
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentMenu != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (id.equals("open")) {
                String idd = new String(ch, start, length).trim();
                currentMenu.setId(Integer.parseInt(idd));
            } else if (nom.equals("open")) {
                String noma = new String(ch, start, length).trim();
                currentMenu.setNom(noma);
            } 
            } else if (description.equals("open")) {
                String descriptiona = new String(ch, start, length).trim();
                currentMenu.setDescription(descriptiona);
            } 
        }
    }


