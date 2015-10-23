package esprit.rt.controllers;



import esprit.rt.handler.MenuHandler;
import esprit.rt.entities.Menu;
import java.io.DataInputStream;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.xml.parsers.*;

public class MenuGet implements  Runnable {
    public static StringBuffer sb = new StringBuffer();
  
    Menu[] menu;
    public String[] items = {"Select Menu"};
    public ChoiceGroup ch3 = new ChoiceGroup("", List.POPUP, items, null);
    public MenuGet()
    {
    }

    public void run() {
    try {
            // this will handle our XML
            MenuHandler menuHandler = new MenuHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlMenu.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, menuHandler);
            // display the result
            menu = menuHandler.getMenu();

            if (menu.length > 0) {
                for (int i = 0; i < menu.length; i++) {
                    String a;
                    a = menu[i].getNom();
                    ch3.insert(i, a, null); 
                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }

    }

   

   public String showMenunom(int i) {
        String res = "";
        if (menu.length > 0) {
            sb.append("");
            sb.append(menu[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
   
   
   public String showMenuId(String str) {
        String res = "";
        for (int i = 0; i < menu.length; i++) {
            if (menu[i].getNom().equals(str)) {
                sb.append("");
                sb.append(menu[i].getId());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
}
