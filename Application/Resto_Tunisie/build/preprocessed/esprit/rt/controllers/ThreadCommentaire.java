/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.controllers;


import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;

/**
 *
 * @author pc casino
 */
public class ThreadCommentaire implements Runnable {

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1/PIDevJ2ME/ajout.php";
    StringBuffer sb = new StringBuffer();
    String res_comment = "";

    int ch;
    int n;
    public static String reponse = "zero";

    public ThreadCommentaire(String comment) {
        Thread th = new Thread(this);
        th.start();
        res_comment = comment;
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

    ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();

    public void run() {
        try {

            res_comment = AchyReplace(res_comment, " ", "%20");

            ClienttGet clienttGet = new ClienttGet();
     
            
            System.out.println(clienttGet.res_idClient);
            hc = (HttpConnection) Connector.open(url + "?com=" + res_comment + "&resto=" + threadInfoResotByName.info_id + "&client="+clienttGet.res_idClient);
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("successfully added".equalsIgnoreCase(sb.toString().trim())) {
                reponse = "succes";
            } else {
                reponse = "problem";
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
