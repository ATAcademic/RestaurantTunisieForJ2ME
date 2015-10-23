package esprit.rt.controllers;

import esprit.rt.handler.RestaurantHandler1;
import esprit.rt.entities.Restaurant;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;

public class RestaurantGet implements Runnable {

    StringBuffer sb = new StringBuffer();

    public Restaurant[] restaurants;
    public String[] items = {""};
    public ChoiceGroup ch2 = new ChoiceGroup("", List.POPUP, items, null);

    public RestaurantGet() {
      
    }

    public void run() {
        try {
            // this will handle our XML
            RestaurantHandler1 restaurantsHandler = new RestaurantHandler1();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlRestaurants.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, restaurantsHandler);
            // display the result
            restaurants = restaurantsHandler.getRestaurant();

            if (restaurants.length > 0) {
                for (int i = 0; i < restaurants.length; i++) {
                    String a;
                    a = restaurants[i].getNom();
                    ch2.insert(i, a, null);

                }

            }

        } catch (IOException e) {
            System.out.println("Exception:" + e.toString());
        } catch (ParserConfigurationException e) {
            System.out.println("Exception:" + e.toString());
        } catch (SAXException e) {
            System.out.println("Exception:" + e.toString());
        }

    }

    public String showRestaurant(int i) {
        String res = "";
        if (restaurants.length > 0) {
            sb.append("Nom :");
            sb.append(restaurants[i].getNom());
            sb.append("\n");
            sb.append("Adresse : ");
            sb.append(restaurants[i].getAdresse());
            sb.append("\n");
            sb.append("Type : ");
            sb.append(restaurants[i].getType());
            sb.append("\n");
            sb.append("Numéro de Telephone :");
            sb.append(restaurants[i].getNumTel());
            sb.append("\n");
            sb.append("Region : ");
            sb.append(restaurants[i].getRegion());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showRestaurantnom(int i) {
        String res = "";
        if (restaurants.length > 0) {
            sb.append("");
            sb.append(restaurants[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    public ChoiceGroup showRestaurantregion(String str) {
        String res = "";
        for (int i = 0; i < restaurants.length; i++) {
        if (restaurants[i].getRegion().equals(str)) {
            
            sb.append("");
            sb.append(restaurants[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        ch2.insert(i, res, null);
        }
//        res = sb.toString();
        
//        sb = new StringBuffer("");
        return ch2;
    }
    
    
    public String showRestaurantId(int i) {
        String res = "";
        if (restaurants.length > 0) {
            sb.append("");
            sb.append(restaurants[i].getId());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    public String showRestoId(String str) {
        String res = "";
        for (int i = 0; i < restaurants.length; i++) {
            if (restaurants[i].getNom().equals(str)) {
                sb.append("");
                sb.append(restaurants[i].getId());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
}
