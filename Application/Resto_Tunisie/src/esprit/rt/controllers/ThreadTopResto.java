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
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.List;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author pc casino
 */
public class ThreadTopResto implements Runnable {
    
    Restaurant[] restaurants;
    public ChoiceGroup cg3 = new ChoiceGroup("Selectionner un restaurant :", List.POPUP, new String[]{}, null);

    public ThreadTopResto() {
        Thread th = new Thread(this);
        th.start();
    }

    public void run() {

        
              try {
                // this will handle our XML
                RestaurantHandler restaurantHandler = new RestaurantHandler();
                // get a parser object
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                // get an InputStream from somewhere (could be HttpConnection, for example)
                HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlTopRestaurants.php");
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                parser.parse(dis, restaurantHandler);
                // display the result
                restaurants = restaurantHandler.getRestaurant();

                int i = 0;

                if (restaurants.length > 0) {
                    for (i = 0; i < restaurants.length; i++) {

                        cg3.insert(i, restaurants[i].getNom(), null);
                    }

                }

            } catch (Exception e) {
                System.out.println("Exception:" + e.toString());

            }
        
    }

}
