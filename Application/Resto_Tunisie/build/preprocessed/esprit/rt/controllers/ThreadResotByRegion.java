/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.controllers;

import esprit.rt.handler.RestaurantHandler;
import esprit.rt.entities.Restaurant;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;

/**
 *
 * @author pc casino
 */
public class ThreadResotByRegion implements Runnable {

    Restaurant[] restaurants;

    public ChoiceGroup cg2 = new ChoiceGroup("Selectionner un restaurant :", List.POPUP, new String[]{}, null);
    String c = "";
    
    StringItem resultRegion = new StringItem("", "");

    public ThreadResotByRegion(String region) {
        Thread th = new Thread(this);
        th.start();
        c = region;
    }
    
    //Méthode pour remplacer les espaces dans l'url avec %20
    public static String AchyReplace(String originalText, String subStringToFind, String subStringToReplaceWith) {
        int s = 0;
        int e = 0;

        StringBuffer newText = new StringBuffer();

        while ((e = originalText.indexOf(subStringToFind, s)) >= 0) {

            newText.append(originalText.substring(s, e));
            newText.append(subStringToReplaceWith);
            s = e + subStringToFind.length();

        }

        newText.append(originalText.substring(s));
        return newText.toString();

    }

    public void run() {
        try {
            RestaurantHandler restaurantHandler = new RestaurantHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            
            c = AchyReplace(c, " ", "%20");
            
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlRegionRestaurants.php?region="+c);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, restaurantHandler);
            // display the result

            restaurants = restaurantHandler.getRestaurant();

            if (restaurants.length > 0) {
                int i = 0;

                if (restaurants.length > 0) {
                    for (i = 0; i < restaurants.length; i++) {

                        cg2.insert(i, restaurants[i].getNom(), null);
                        resultRegion.setText(restaurants[i].getAdresse());
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }

    }

}
