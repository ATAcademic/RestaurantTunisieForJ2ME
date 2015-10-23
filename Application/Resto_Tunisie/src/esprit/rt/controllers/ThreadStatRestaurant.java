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
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author pc casino
 */
public class ThreadStatRestaurant implements Runnable {

    Restaurant[] restaurants;
    public StringItem info_stat = new StringItem("", "");
    public static String stringstatinfo = "";
    public static String stringstatnote = "";
    public static int[] r;
    public static  String[] r2;

    public ThreadStatRestaurant() {
    stringstatinfo="";
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
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlStatRestaurants.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, restaurantHandler);
            // display the result
            restaurants = restaurantHandler.getRestaurant();

            if (restaurants.length > 0) {
                r = new int[restaurants.length];
                r2 = new String[restaurants.length];
                for (int i = 0; i < restaurants.length; i++) {

                    stringstatinfo += restaurants[i].getNom() + " : " + Integer.toString(restaurants[i].getNote()) + "\n";
                    stringstatnote = Integer.toString(restaurants[i].getNote());
                    r[i] = restaurants[i].getNote();
                    r2[i] = restaurants[i].getNom();
                }
                info_stat.setText(stringstatinfo);
//                for(int k=0;k<r2.length;k++)
//                {System.out.println(r2[k]);}
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());

        }

    }

}
