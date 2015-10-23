/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.resto.tests;

import java.io.DataInputStream;
import java.util.Timer;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import tn.resto.env.Current;
import tn.resto.maps.MapCanvas;
import tn.resto.xml.CordonneeHandler;
import tn.resto.xml.RestaurantHandlerOuss;

/**
 * @author Aditsan Kadmus
 */
public class Midlet extends MIDlet implements Runnable, CommandListener {

    Display disp = Display.getDisplay(this);
    Command cmdRestos = new Command("Restaurant(s)", Command.BACK, 0);
    Command cmdSearch = new Command("Rechercher un restaurant", Command.SCREEN, 0);
    Command cmdOk = new Command("OK", Command.SCREEN, 0);
    Command cmdCancel = new Command("Cancel", Command.BACK, 0);
    Command cmdLocation = new Command("Restaurer!", Command.SCREEN, 1);
    Command cmdBackOuss = new Command("Quitter", Command.SCREEN, 2);
    Player player = null;
    Timer tOuss;
    Command cmdSkip = new Command("Passer la pub", Command.SCREEN, 0);
    TextField tf = new TextField("Nom : ", "", 50, TextField.ANY);
    Form formSearch = new Form("Rechercher un restaurant");
    Form formVideo = new Form("");
    List tmpF;

    public void startApp() {

        initMaps();

    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void run() {

        if (Thread.currentThread() == Current.mainMapsThread) {
            rHandlerMaps();
            Current.mainMapsThread.interrupt();
            return;
        }

    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
    }

    /*
     *
     *
     *
     *
     *  Maps Initializer
     *
     *
     *
     *
     *
     */
    public void initMaps() {
        formSearch.append(tf);
        formSearch.addCommand(cmdOk);
        formSearch.addCommand(cmdCancel);
        formSearch.setCommandListener(this);
        formSearch.addCommand(cmdCancel);
        Current.url = "http://127.0.0.1/restoTn/getXmlCordonnees.php";
        disp.setCurrent(new Form("Loading"));
        formVideo.setTicker(new Ticker("Publicite!"));
        formVideo.addCommand(cmdSkip);
        formVideo.setCommandListener(this);

        try {
            loadPlayer();
            GUIControl guiControl = (GUIControl) player.getControl("javax.microedition.media.control.GUIControl");

            if (guiControl == null) {
                throw new Exception("No GUIControl!!");
            }


            Item videoItem = (Item) guiControl.initDisplayMode(GUIControl.USE_GUI_PRIMITIVE, null);
            formVideo.append(new Spacer(30, 170));
            formVideo.append(videoItem);

            disp.setCurrent(formVideo);
            player.start();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        disp.setCurrent(formVideo);

//        Current.mainMapsThread = new Thread(this);
//        Current.mainMapsThread.start();
    }

    /*
     *
     *
     *
     *
     *  Maps RUN Handler
     *
     *
     *
     *
     *
     */
    public void rHandlerMaps() {
        Current.cords = new Vector();
        Current.paths = new Vector();
        Current.isPath = false;
        try {
            System.out.println("aaaa " + Current.url);
            // this will handle our XML
            CordonneeHandler cordsHandler = new CordonneeHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/restoTn/getXmlCordonnees.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, cordsHandler);
            // display the result
            Current.cords = cordsHandler.getCords();
            System.out.println("aaaa");
//            System.out.println(Current.cords.elementAt(0));
            //System.out.println(Current.cords.elementAt(1));
            MapCanvas mp = new MapCanvas(Current.paths, Current.cords);
            
            mp.addCommand(cmdSearch);
            mp.addCommand(cmdLocation);
            mp.addCommand(cmdBackOuss);
            mp.addCommand(cmdRestos);
            disp.setCurrent(mp);
            mp.setCommandListener(Midlet.this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /*
     *
     *
     *
     *
     *  Maps COMMAND ACTION Handler
     *
     *
     *
     *
     *
     */
    public void cAHandlerMaps(Command c, Displayable d) {
        if (tmpF == d && cmdCancel != c) {
            //code here
            //temchi lel restau
        }
        if (cmdRestos == c) {
            System.out.println("sqsq86554546466");
            tmpF = new List("Liste des restaurants", List.IMPLICIT);
            tmpF.addCommand(cmdCancel);
            tmpF.setCommandListener(this);
            tmpF.append("sddss", null);
            Thread thtmp = new Thread() {

                public void run() {
                    //code here
                    //parsi w jib ID + NOM + NOTE + IMAGE
                    try {
            // this will handle our XML
            RestaurantHandlerOuss restaurantHandler = new RestaurantHandlerOuss();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            String tmpURL;
            if (Current.url == "http://127.0.0.1/restoTn/getXmlCordonnees.php")
                tmpURL = "http://127.0.0.1/restoTn/getXmlRestos.php";
            else
                tmpURL = "http://127.0.0.1/restoTn/getXmlRestosByName.php?name"+Current.nameResto;
            HttpConnection hc = (HttpConnection) Connector.open(tmpURL);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, restaurantHandler);
            // display the result
            Vector restaurants = restaurantHandler.getRestaurant();

            if (restaurants.size() > 0) {
                for (int i = 0; i < restaurants.size(); i++) {
                    tmpF.append(restaurants.elementAt(i).toString(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
                    System.out.println("++/*++/+/+/");
                    disp.setCurrent(tmpF);
                }
            };
            thtmp.start();
            
            return;
        }
        if (cmdBackOuss == c) {
            //code here
            //temchi lel menu eli 9balha
            return;
        }
        if (!cmdSkip.equals(c)) {
            if (Current.mainMapsThread != null) {
                Current.mainMapsThread.interrupt();
            }
        } else {
            try {
                player.stop();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
        }

        Current.url = "http://127.0.0.1/restoTn/getXmlCordonnees.php";
        Current.cords = new Vector();
        Current.paths = new Vector();
        if (c == cmdSearch) {
            System.out.println("dsds");
            disp.setCurrent(formSearch);
            return;
        }



        if (c == cmdOk && d == formSearch) {
            Current.url = "http://127.0.0.1/restoTn/getXmlCordonneeBN.php?name=" + tf.getString().trim();
            Current.isPath = true;
        }
        if (c == cmdCancel) {
            Current.url = "http://127.0.0.1/restoTn/getXmlCordonnees.php";
            Current.isPath = false;
        }
        if (c == cmdLocation) {
            Current.url = "http://127.0.0.1/restoTn/getXmlCordonnees.php";
            Current.isPath = false;
        }
        new Thread() {

            public void run() {

                Current.cords = new Vector();
                Current.paths = new Vector();

                try {

                    // this will handle our XML
                    CordonneeHandler cordsHandler = new CordonneeHandler();
                    // get a parser object
                    SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                    // get an InputStream from somewhere (could be HttpConnection, for example)
                    HttpConnection hc = (HttpConnection) Connector.open(Current.url);
                    DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                    parser.parse(dis, cordsHandler);
                    // display the result
                    Current.cords = cordsHandler.getCords();

//            System.out.println(Current.cords.elementAt(0));
                    //System.out.println(Current.cords.elementAt(1));
                    System.out.println("ssssss");
                    MapCanvas mp = new MapCanvas(Current.paths, Current.cords);
                    
                    mp.addCommand(cmdSearch);
                    mp.addCommand(cmdLocation);
                    mp.addCommand(cmdBackOuss);
                    mp.addCommand(cmdRestos);
                    disp.setCurrent(mp);
                    mp.setCommandListener(Midlet.this);
                } catch (Exception e) {
                    Alert atmp = new Alert("Erreur", "Pas de restaurants !", null, AlertType.INFO);
                    atmp.setTimeout(2000);
                    disp.setCurrent(atmp);
                }
            }
        }.start();
    }

    /*
     *
     *
     *
     * VIDEO
     *
     *
     *
     */
    private void loadPlayer() throws Exception {
        player = Manager.createPlayer(getClass().getResourceAsStream("/tn/resto/ads/ad.mpg"), "video/mpeg");
        player.realize();
    }
}
