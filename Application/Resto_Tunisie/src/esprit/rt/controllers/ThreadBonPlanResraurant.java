/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.controllers;

import esprit.rt.handler.BonPlanHandler;
import esprit.rt.entities.BonPlan;
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
public class ThreadBonPlanResraurant implements Runnable {

    BonPlan[] bonPlans;

    public StringItem info_description = new StringItem("Déscription : ", "");
    public StringItem info_dateDebut = new StringItem("Date Début : ", "");
    public StringItem info_dateFin = new StringItem("Date Fin : ", "");
    String c = "";
    public static String res_description = null;
    public static String res_datedebut = null;
    public static String res_datedefin = null;

    public ThreadBonPlanResraurant(String id) {
        Thread th = new Thread(this);
        th.start();
        c = id;

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
            BonPlanHandler bonPlanHandler = new BonPlanHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)

            c = AchyReplace(c, " ", "%20");

            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlBonPlanRestaurants.php?id=" + c);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, bonPlanHandler);
            // display the result

            bonPlans = bonPlanHandler.getBonPlan();

            if (bonPlans.length > 0) {
                //  int i =0;

                if (bonPlans.length > 0) {
                    for (int i = 0; i < bonPlans.length; i++) {
                        info_description.setText(bonPlans[i].getDescription());
                        info_dateDebut.setText(bonPlans[i].getDateDebut());
                        info_dateFin.setText(bonPlans[i].getDateFin());
                        res_description = bonPlans[i].getDescription();
                        res_datedebut = bonPlans[i].getDateDebut();
                        res_datedefin = bonPlans[i].getDateFin();
                    }
                    

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }

    }

}
