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
public class ThreadEvaluationResto implements Runnable {

    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1/PIDevJ2ME/AddNote.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    int n;

    String res_note = "";
    public static String reponse = "zero";

    public ThreadEvaluationResto(int i) {
        Thread th = new Thread(this);
        th.start();
        res_note = Integer.toString(i);
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

            res_note = AchyReplace(res_note, " ", "%20");

            hc = (HttpConnection) Connector.open(url + "?note=" + res_note + "&resto=" + threadInfoResotByName.info_id + "&client=9");
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
