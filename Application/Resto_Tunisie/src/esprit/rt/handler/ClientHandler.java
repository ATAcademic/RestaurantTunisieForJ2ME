package esprit.rt.handler;

import esprit.rt.entities.Client;
import java.lang.*;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Wael Mallek
 */
public class ClientHandler extends DefaultHandler {

    private Vector clients;
    private String id = "close";
    private String nom = "close";
    private String prenom = "close";
    private String email = "close";
    private String password = "close";
    private String date_naissance = "close";
    private String num_tel = "close";
    private String adresse = "close";
    private String region = "close";
    private String image = "close";
    private String sexe = "close";
    private String question_securite = "close";
    private String reponse_securite = "close";
    private String description = "close";
    private String estbloque = "close";

    public ClientHandler() {
        clients = new Vector();
    }

    public Client[] getClient() {
        Client[] clientss = new Client[clients.size()];
        clients.copyInto(clientss);
        return clientss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Client currentclient;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("utilisateur")) {

            if (currentclient != null) {
                throw new IllegalStateException("already processing a Clients");
            }
            currentclient = new Client();
        } else if (qName.equals("id")) {
            id = "open";
        } else if (qName.equals("nom")) {
            nom = "open";
        } else if (qName.equals("prenom")) {
            prenom = "open";
        } else if (qName.equals("email")) {
            email = "open";
        } else if (qName.equals("password")) {
            password = "open";
        } else if (qName.equals("date_naissance")) {
            date_naissance = "open";
        } else if (qName.equals("num_tel")) {
            num_tel = "open";
        } else if (qName.equals("adresse")) {
            adresse = "open";
        } else if (qName.equals("region")) {
            region = "open";
        } else if (qName.equals("image")) {
            image = "open";
        } else if (qName.equals("sexe")) {
            sexe = "open";
        } else if (qName.equals("question_securite")) {
            question_securite = "open";
        } else if (qName.equals("reponse_securite")) {
            reponse_securite = "open";
        } else if (qName.equals("description")) {
            description = "open";
        } else if (qName.equals("estbloque")) {
            estbloque = "open";
        }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("utilisateur")) {
            // we are no longer processing a <reg.../> tag
            clients.addElement(currentclient);
            currentclient = null;
        } else if (qName.equals("id")) {
            id = "close";
        } else if (qName.equals("nom")) {
            nom = "close";
        } else if (qName.equals("prenom")) {
            prenom = "close";
        } else if (qName.equals("email")) {
            email = "close";
        } else if (qName.equals("password")) {
            password = "close";
        } else if (qName.equals("date_naissance")) {
            date_naissance = "close";
        } else if (qName.equals("num_tel")) {
            num_tel = "close";
        } else if (qName.equals("adresse")) {
            adresse = "close";
        } else if (qName.equals("region")) {
            region = "close";
        } else if (qName.equals("image")) {
            image = "close";
        } else if (qName.equals("sexe")) {
            sexe = "close";
        } else if (qName.equals("question_securite")) {
            question_securite = "close";
        } else if (qName.equals("reponse_securite")) {
            reponse_securite = "close";
        } else if (qName.equals("description")) {
            description = "close";
        } else if (qName.equals("estbloque")) {
            estbloque = "close";
        }
    }
    // "characters" are the text inbetween tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentclient != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (id.equals("open")) {
                String idd = new String(ch, start, length).trim();
                currentclient.setId(Integer.parseInt(idd));
            } else if (nom.equals("open")) {
                String noma = new String(ch, start, length).trim();
                currentclient.setNom(noma);
            } else if (prenom.equals("open")) {
                String prenoma = new String(ch, start, length).trim();
                currentclient.setPrenom(prenoma);
            } else if (email.equals("open")) {
                String emaila = new String(ch, start, length).trim();
                currentclient.setEmail(emaila);
            } else if (password.equals("open")) {
                String passworda = new String(ch, start, length).trim();
                currentclient.setPassword(passworda);
            } else if (date_naissance.equals("open")) {
                String date_naissancea = new String(ch, start, length).trim();

                currentclient.setDate_naiss(date_naissancea);
            } else if (num_tel.equals("open")) {
                String num_tela = new String(ch, start, length).trim();
                currentclient.setNum_tel(Integer.parseInt(num_tela));
            } else if (adresse.equals("open")) {
                String adressea = new String(ch, start, length).trim();
                currentclient.setAdresse(adressea);
            } else if (region.equals("open")) {
                String regiona = new String(ch, start, length).trim();
                currentclient.setRegion(regiona);
            } else if (image.equals("open")) {
                String imagea = new String(ch, start, length).trim();
                currentclient.setImage(imagea);
            } else if (sexe.equals("open")) {
                String sexea = new String(ch, start, length).trim();
                currentclient.setSexe(sexea.charAt(0));
            } else if (question_securite.equals("open")) {
                String question_securitea = new String(ch, start, length).trim();
                currentclient.setQuestion_securite(question_securitea);
            } else if (reponse_securite.equals("open")) {
                String reponse_securitea = new String(ch, start, length).trim();
                currentclient.setReponse_securite(reponse_securitea);
            } else if (description.equals("open")) {
                String descriptiona = new String(ch, start, length).trim();
                currentclient.setDescription(descriptiona);
            } else if (estbloque.equals("open")) {
                String estbloquea = new String(ch, start, length).trim();
                currentclient.setEstBloque(estbloquea.charAt(0));
            }
        }
    }
}
