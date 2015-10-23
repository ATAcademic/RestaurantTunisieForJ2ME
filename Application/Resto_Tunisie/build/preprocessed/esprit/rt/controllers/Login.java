package esprit.rt.controllers;

import esprit.rt.gui.Resto_Tunisie;
import esprit.rt.gui.Resto_Tunisie;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;

public class Login implements Runnable {

    private final Resto_Tunisie midlet;
    private volatile boolean dismissed = false;

    public Login(Resto_Tunisie midlet) {
        this.midlet = midlet;
        new Thread(this).start();
    }
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1/PIDevJ2ME/login.php";
    StringBuffer sbb = new StringBuffer();
    int ch;

    public void run() {
        try {
            hc = (HttpConnection) Connector.open(url + "?login=" + midlet.log.getString() + "&password=" + midlet.pwd.getString());
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sbb.append((char) ch);
            }
            if ("successfully".equalsIgnoreCase(sbb.toString().trim())) {
                try {
                     ClienttGet clienttGet = new ClienttGet();
                     ClienttGet.res_logClient = midlet.log.getString().trim();
                     clienttGet.getIdClient();
                     
                    dismiss();

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                midlet.erreur3.setTimeout(Alert.FOREVER);
                midlet.erreur3.setTicker(midlet.tickererreur);
                midlet.erreur3.setTimeout(3000);

                midlet.disp.setCurrent(midlet.erreur3);
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    private void dismiss() {
        if (!dismissed) {
            dismissed = true;
            midlet.InfoPerson();
        }
    }

}
