/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.handler;

import esprit.rt.entities.BonPlan;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author pc casino
 */
public class BonPlanHandler extends DefaultHandler{

    private Vector bonplans;
    String idTag = "close";
    String descriptionTag = "close";
    String dateDebutTag = "close";
    String dateFinTag = "close";

    public BonPlanHandler() {
        bonplans = new Vector();
    }

    public BonPlan[] getBonPlan() {
        BonPlan[] bonplanss = new BonPlan[bonplans.size()];
        bonplans.copyInto(bonplanss);
        return bonplanss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private BonPlan currentBonPlan;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //qName : nom de la balise
        if (qName.equals("personne")) {

            if (currentBonPlan != null) {
                throw new IllegalStateException("already processing a restaurants");
            }
            currentBonPlan = new BonPlan();
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } else if (qName.equals("date_debut")) {
            dateDebutTag = "open";
        } else if (qName.equals("date_fin")) {
            dateFinTag = "open";
        }

    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("personne")) {
            // we are no longer processing a <reg.../> tag
            bonplans.addElement(currentBonPlan);
            currentBonPlan = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        } else if (qName.equals("date_debut")) {
            dateDebutTag = "close";
        } else if (qName.equals("date_fin")) {
            dateFinTag = "close";
        }

    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        //La méthode characters , endElement et startElement sont des  méthodes définis par SAXException

        // we're only interested in this inside a <phone.../> tag
        if (currentBonPlan != null) {
            // don't forget to trim excess spaces from the ends of the string

            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentBonPlan.setId(id);
            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentBonPlan.setDescription(description);
            } else if (dateDebutTag.equals("open")) {
                String date_debut = new String(ch, start, length).trim();
                currentBonPlan.setDateDebut(date_debut);
            } else if (dateFinTag.equals("open")) {
                String date_fin = new String(ch, start, length).trim();
                currentBonPlan.setDateFin(date_fin);
            } 
        }
    }
}
