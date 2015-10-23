package esprit.rt.controllers;

import esprit.rt.handler.ClientHandler;
import esprit.rt.entities.Client;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.*;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.List;
import javax.xml.parsers.*;
import org.xml.sax.SAXException;

public class ClienttGet implements Runnable {

    StringBuffer sb = new StringBuffer();

    Client[] clients;
    public String[] items = {"Name"};
    public ChoiceGroup em = new ChoiceGroup("", List.POPUP, items, null);
    public String[] itemss = {"Pass"};
    public ChoiceGroup pas = new ChoiceGroup("", List.POPUP, items, null);
    public static String res_idClient ;
    public static String res_logClient ;
    public ClienttGet() {

    }
    public void getIdClient()
    {
        
        Thread th = new Thread(){
            public void run()
            {
                
                ClientHandler clientsHandler= new ClientHandler();
                try
        {
        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlUtilisateur.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, clientsHandler);
        }
        catch (Exception e)
        {
            System.out.println("+++++");
        }
         Client[] clients = new Client[1000];
         clients = clientsHandler.getClient();
            for (int i = 0; i < clients.length; i++)
        {
           if ((clients[i].getEmail() == null ? res_logClient == null : clients[i].getEmail().equals(res_logClient)) || (clients[i].getNom().equals(res_logClient)))
                ClienttGet.res_idClient= clients[i].getId()+"";
        }
            }
            
        };
        
        th.run();
    }
    public void run() {
        try {
            // this will handle our XML
            ClientHandler clientsHandler = new ClientHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlUtilisateur.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, clientsHandler);
            // display the result
            clients = clientsHandler.getClient();

            if (clients.length > 0) {
                for (int i = 0; i < clients.length; i++) {
                    String a;
                    a = clients[i].getNom();
                    em.insert(i, a, null);

                    String b;
                    b = clients[i].getPassword();
                    pas.insert(i, a, null);

                    res_idClient = Integer.toString(clients[i].getId());

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

    public String showClientNom(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(str)) {
                sb.append("");
                sb.append(clients[i].getNom());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientPassword(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getPassword().equals(str)) {
                sb.append("");
                sb.append(clients[i].getPassword());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientNomId(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(str)) {
                sb.append("");
                sb.append(clients[i].getId());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientImage(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(str)) {
                sb.append("");
                sb.append(clients[i].getImage());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientPrenon(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(str)) {
                sb.append("");
                sb.append(clients[i].getPrenom());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientEmailId(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getEmail().equals(str)) {
                sb.append("");
                sb.append(clients[i].getId());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientEtat(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getNom().equals(str)) {
                sb.append("");
                sb.append(clients[i].getEstBloque());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientEmailEtat(String str) {
        String res = "";
        for (int i = 0; i < clients.length; i++) {
            if (clients[i].getEmail().equals(str)) {
                sb.append("");
                sb.append(clients[i].getEstBloque());
                sb.append("\n");
            }
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }

    public String showClientid(int i) {
        String res = "";
        if (clients.length > 0) {
            sb.append("");
            sb.append(clients[i].getId());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
