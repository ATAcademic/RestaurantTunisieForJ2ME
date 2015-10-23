/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.controllers;

import esprit.rt.handler.PlatHandler;
import esprit.rt.entities.Plat;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author pc casino
 */
public class ThreadPlatbyMenu implements Runnable {

    Plat[] plats;
     StringBuffer sb = new StringBuffer();


    public ChoiceGroup ch4 = new ChoiceGroup("", List.EXCLUSIVE, new String[]{}, null);
    String c = "";
    
   public StringItem resultRegion = new StringItem("", "");


    public ThreadPlatbyMenu(String idmenu) {
        c = idmenu;
    }

    public ThreadPlatbyMenu() {
        
        
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
            PlatHandler platHandler = new PlatHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            
            c = AchyReplace(c, " ", "%20");
            
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlPlat.php?idmenu="+c.trim());
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, platHandler);
            // display the result

            plats = platHandler.getPlat();

            if (plats.length > 0) {
                if (plats.length > 0) {
                    for (int i = 0; i < plats.length; i++) {
                        ch4.insert(i, plats[i].getNom(), null);
                        System.out.println("les plats ="+plats[i].getNom());
                    }

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }

    }
     public String showRestaurant(int i) {
        String res = "";
        if (plats.length > 0) {
            sb.append("Nom :");
            sb.append(plats[i].getNom());
            sb.append("\n");
            sb.append("Menu : ");
            sb.append(plats[i].getMenu());
            sb.append("\n");
            sb.append("Type : ");
            sb.append(plats[i].getType());
            sb.append("\n");
            sb.append("Description : ");
            sb.append(plats[i].getDescription());
            sb.append("\n");
            sb.append("Prix :");
            sb.append(plats[i].getPrix());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showRestaurantnom(int i) {
        String res = "";
        if (plats.length > 0) {
            sb.append("");
            sb.append(plats[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    public ChoiceGroup showRestaurantregion(String str) {
        String res = "";
        for (int i = 0; i < plats.length; i++) {
        if (plats[i].getMenu().equals(str)) {
            
            sb.append("");
            sb.append(plats[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        ch4.insert(i, res, null);
        }
//        res = sb.toString();
        
//        sb = new StringBuffer("");
        return ch4;
    }
    
    
public float showPlatprix(int i) {
        String res = "";
        if (plats.length > 0) {
            sb.append("");
            sb.append(plats[i].getPrix());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        float a=Float.parseFloat(res);
        return a;
    }
    
    public String showPlatId(String str) {
        String res = "";
        for (int i = 0; i < plats.length; i++) {
            if (plats[i].getNom().equals(str)) {
                sb.append("");
                sb.append(plats[i].getId());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }


}
