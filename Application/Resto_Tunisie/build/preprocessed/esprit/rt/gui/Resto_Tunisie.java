/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esprit.rt.gui;

import esprit.rt.controllers.*;
import esprit.rt.handler.RestaurantHandler;
import esprit.rt.entities.Restaurant;
import esprit.rt.splashscreen.*;
import java.io.*;
import java.util.*;
import javax.microedition.io.*;
import javax.microedition.lcdui.*;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.GUIControl;
import javax.microedition.midlet.*;
import javax.xml.parsers.*;
import tn.resto.env.Current;
import tn.resto.maps.MapCanvas;
import tn.resto.xml.CordonneeHandler;
import tn.resto.xml.RestaurantHandlerOuss;

/**
 * @author pc casino
 */
public class Resto_Tunisie extends MIDlet implements CommandListener, Runnable, ItemStateListener {

    public Display disp = Display.getDisplay(this);

    //maj
    Form f = new Form("Resto Tunisie");
    Form confirm = new Form("Information Personnel ");
    Form auth = new Form("Authentification Resto Tunisie");
    Form resultat = new Form("Simulation de couts");
    StringBuffer b = new StringBuffer();
    StringBuffer id = new StringBuffer();
    StringBuffer reg = new StringBuffer();
    Spacer cc = new Spacer(100, 10);
    Command exit = new Command("Exit", Command.EXIT, 0);
    Command cmdMaps = new Command("Map",Command.SCREEN,0);
    Ticker t = new Ticker("Resto Tunisie");
    public Ticker tickererreur = new Ticker("Erreur !!!");
    Ticker loginTicker = new Ticker("Login Resto ");

    Image img;
    ImageItem imgt;
    Image imgg;
    ImageItem imgtt;
    Image imgp;
    ImageItem imgtp;
    StringItem region = new StringItem("      Veilleuez choisissez une region : ", "");
    StringItem resto = new StringItem("       Veilleuez choisissez un restaurant : ", "");
    StringItem restoo = new StringItem("       Veilleuez choisissez un restaurant : ", "");
    public String[] itemsb = {""};
    public ChoiceGroup ch3 = new ChoiceGroup("", List.POPUP, itemsb, null);
    String[] itemss = {""};
    public ChoiceGroup ch4 = new ChoiceGroup("", List.MULTIPLE, itemss, null);
    Spacer sp1 = new Spacer(100, 10);
    Spacer sp11 = new Spacer(100, 10);
    Spacer sp22 = new Spacer(100, 10);
    Spacer sp2 = new Spacer(100, 10);
    Spacer sp3 = new Spacer(100, 10);
    Spacer sp33 = new Spacer(100, 10);
    Spacer sp4 = new Spacer(100, 10);
    Spacer sp44 = new Spacer(100, 10);
    Spacer sp5 = new Spacer(100, 10);
    Spacer sp55 = new Spacer(100, 10);
    Command login1 = new Command("Suivant", Command.SCREEN, 0);
    Command reserv = new Command("Reservez", Command.SCREEN, 0);
    Command login = new Command("Login", Command.SCREEN, 0);
    Command reserv1 = new Command("Reservez", Command.SCREEN, 0);
    Command cmdBack0 = new Command("Précèdent", Command.BACK, 0);
    Command cmdBack3 = new Command("Précèdent", Command.BACK, 0);
    Command cmdBack1 = new Command("Précèdent", Command.BACK, 0);
    Command reserv2 = new Command("Suivant", Command.SCREEN, 0);
    Command reserv3 = new Command("Suivant", Command.SCREEN, 0);
    Command valider = new Command("Valider", Command.SCREEN, 0);
    Command valider1 = new Command("Valider", Command.SCREEN, 0);
    Command EXIT = new Command("Quitter", Command.BACK, 0);
    Command EXIT1 = new Command("Quitter", Command.BACK, 0);
    Form form = new Form("Infos Restaurant");
    public int indexOfSI = -1;
    Form reserve = new Form("Reservation");

    StringBuffer sbbb = new StringBuffer();

    TextField nbplace = new TextField("Nombre de places *", null, 30, TextField.NUMERIC);
    TextField nbplats = new TextField("Nombre de plats *", null, 30, TextField.NUMERIC);
    public TextField log = new TextField("Nom d'utilisateur *", null, 30, TextField.ANY);
    public TextField pwd = new TextField("Mot de passe *", null, 30, TextField.PASSWORD);
    TextField prix = new TextField("", null, 30, TextField.NUMERIC);
    Spacer space = new Spacer(20, 10);
    Spacer space1 = new Spacer(20, 10);
    Spacer space2 = new Spacer(20, 10);
    Spacer space3 = new Spacer(20, 10);
    Spacer space4 = new Spacer(20, 10);
    Spacer space5 = new Spacer(20, 30);
    Spacer space6 = new Spacer(20, 15);
    Spacer space7 = new Spacer(20, 60);
    Alert erreur1 = new Alert("ERROR", "Selectionnez une ville et un restaurant !  ", null, AlertType.ERROR);
    Alert erreur = new Alert("ERROR", "Remplir les champs vide !  ", null, AlertType.ERROR);
    Alert erreur2 = new Alert("ERROR", "Erreur lors d'insertion  !  ", null, AlertType.ERROR);
    public Alert erreur3 = new Alert("ERROR", "Login ou mot de passe incorrect !  ", null, AlertType.ERROR);
    public DateField date
            = new DateField("Selectionnez une date :",
                    DateField.DATE_TIME,
                    TimeZone.getDefault());
    Calendar cal = Calendar.getInstance();
    StringItem smenu = new StringItem("Selectionner un menu :", "");
    StringItem splat = new StringItem("Choisir un plat :", "");
    StringItem s0 = new StringItem("Resrvation dans le restaurant :", "");
    StringItem sss = new StringItem("Date :", "");
    StringItem s1 = new StringItem("Nombre de places :", "");
    StringItem s2 = new StringItem("Menu :", "");
    StringItem s3 = new StringItem("Plat :", "");
    StringItem s4 = new StringItem("Nombre de plats :", "");
    StringItem s5 = new StringItem("Cout finale :", "");
    StringItem places = new StringItem("Place N° :", "");
    StringItem bb = new StringItem("Bienvenue Client :", "");
    StringItem wlcom = new StringItem("Welcom :", "");

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1/PIDevJ2ME/insert.php";
    StringBuffer sbb = new StringBuffer();
    int ch;
    ClienttGet client = new ClienttGet();

    Thread threadreserve;

    //
    private int choiceGroupIndex;
    private int stringitemnameIndex;

    Restaurant[] restaurants;
    public String[] t1;
    StringBuffer sb = new StringBuffer();

    Form f1 = new Form("Acceuil");
    Form f2 = new Form("TOP Restaurants");
    Form f3 = new Form("Restaurants par région");
    Form f4 = new Form("Les statistiques");
    Form f5 = new Form("Evaluation");
    Form f6 = new Form("Commentaire");

    Image imgRegion;
    Image imgRegionR;
    Image imgTopResto;
    Image imgTopRestoR;
    Image stat1, note1, res1, com1;
    Image stat11, note11, res11, com11;
    Image imgFork;
    Image imgAlertResto;
    ImageItem imageItemRegion;
    ImageItem imageItemRegionR;
    ImageItem imageItemTopResto;
    ImageItem imageItemTopRestoR;
    ImageItem imgstat1, imgnote1, imgres1, imgcom1;
    ImageItem imgstat11, imgnote11, imgres11, imgcom11;
    ImageItem imageItemFork;
    ImageItem imageItemimgAlertResto;

    Command cmdLog = new Command("Login", Command.SCREEN, 0);

    Command cmdNext = new Command("Suivant", Command.SCREEN, 0);
    Command cmdStat = new Command("Statistiques", Command.SCREEN, 0);
    Command cmHist = new Command("Histogramme", Command.SCREEN, 0);
    Command cmCam = new Command("Camembert", Command.SCREEN, 0);
    Command cmdEvaluation = new Command("Evaluation", Command.SCREEN, 0);
    Command cmdEvaluer = new Command("Evaluer", Command.SCREEN, 0);
    Command cmdCommentaire = new Command("Commentaire", Command.SCREEN, 0);
    Command cmdCommenter = new Command("Commenter", Command.SCREEN, 0);
    Command cmdCanvasback = new Command("Précédent", Command.EXIT, 0);
    Command cmdBack = new Command("Précédent", Command.EXIT, 0);
    Command next = new Command("Suivant", Command.SCREEN, 0);
    Alert alrt;
    Alert alrtComment;
    Alert alrtIdRestoVide;
    Alert alrtNoteVide;
    Alert alrtAjoutEvaluation;
    Alert alrtProblemAjoutEvaluation;
    Alert alrtAjoutComment;
    Alert alrtProblemAjoutComment;

    ChoiceGroup cg1 = new ChoiceGroup("Selectionner une région :", List.POPUP, new String[]{}, null);

    public ChoiceGroup cg2m = new ChoiceGroup("", List.POPUP, new String[]{}, null);
    public ChoiceGroup cg3m = new ChoiceGroup("", List.POPUP, new String[]{}, null);

    StringItem resultRegionm_name = new StringItem("", "");
    StringItem resultRegionm_type = new StringItem(null, null);
    StringItem resultRegionm_adresse = new StringItem(null, null);
    StringItem resultRegionm_numTel = new StringItem(null, null);
    public String resultRegionm_id = "hi";

    StringItem resultRegionm_description = new StringItem(null, null);
    StringItem resultRegionm_dateDebut = new StringItem(null, null);
    StringItem resultRegionm_dateFin = new StringItem(null, null);

    StringItem result_statistique = new StringItem("", "");

    TextField commentaire = new TextField("Ajouter un commentaire :", null, 100, TextField.ANY);
    private int[] indexes;


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



    public Resto_Tunisie() {

        try {
            imgRegion = Image.createImage("/esprit/rt/image/region.png");
            imgTopResto = Image.createImage("/esprit/rt/image/topResto.png");
            imgRegionR = Image.createImage("/esprit/rt/image/regionR.png");
            imgTopRestoR = Image.createImage("/esprit/rt/image/topRestoR.png");

            stat1 = Image.createImage("/esprit/rt/image/STAT (Copier).png");
            note1 = Image.createImage("/esprit/rt/image/edit-6-128.png");
            res1 = Image.createImage("/esprit/rt/image/reserve~3.png");
            com1 = Image.createImage("/esprit/rt/image/speech-bubble-128 (1).png");

            stat11 = Image.createImage("/esprit/rt/image/STATF.png");
            note11 = Image.createImage("/esprit/rt/image/edit-6-128 (1).png");
            res11 = Image.createImage("/esprit/rt/image/reserve~1.png");
            com11 = Image.createImage("/esprit/rt/image/speech-bubble-128.png");

            imgFork = Image.createImage("/esprit/rt/image/fork.png");
            imgAlertResto = Image.createImage("/esprit/rt/image/alertResto.png");

        } catch (IOException e) {
            e.printStackTrace();
        }

        imageItemRegion = new ImageItem("imgRegion", imgRegion, ImageItem.PLAIN, "null");
        imageItemTopResto = new ImageItem("imgTopResto", imgTopResto, ImageItem.PLAIN, "null");
        imageItemRegionR = new ImageItem("imgRegionR", imgRegionR, ImageItem.PLAIN, "null");
        imageItemTopRestoR = new ImageItem("imgTopRestoR", imgTopRestoR, ImageItem.PLAIN, "null");
        imgstat1 = new ImageItem("imgRegion", stat1, ImageItem.PLAIN, "null");
        imgnote1 = new ImageItem("imgTopResto", note1, ImageItem.PLAIN, "null");
        imgres1 = new ImageItem("imgRegionR", res1, ImageItem.PLAIN, "null");
        imgcom1 = new ImageItem("imgTopRestoR", com1, ImageItem.PLAIN, "null");

        imgstat11 = new ImageItem("imgRegion", stat11, ImageItem.PLAIN, "null");
        imgnote11 = new ImageItem("imgTopResto", note11, ImageItem.PLAIN, "null");
        imgres11 = new ImageItem("imgRegionR", res11, ImageItem.PLAIN, "null");
        imgcom11 = new ImageItem("imgTopRestoR", com11, ImageItem.PLAIN, "null");

        imageItemFork = new ImageItem("imgTopResto", imgFork, ImageItem.PLAIN, "null");
        imageItemimgAlertResto = new ImageItem("imgTopResto", imgAlertResto, ImageItem.LAYOUT_CENTER, "null");

        alrt = new Alert("ERREUR", "                 Veuillez choisir une option !", imgAlertResto, AlertType.ERROR);
        alrtComment = new Alert("ERREUR", "                 Veuillez ecrire un commentaire   !", imgAlertResto, AlertType.ERROR);
        alrtIdRestoVide = new Alert("ERREUR", "                Veuillez choisir un restaurant   !", imgAlertResto, AlertType.ERROR);
        alrtNoteVide = new Alert("ERREUR", "           Veuillez donner une note tout d'abord !", imgAlertResto, AlertType.ERROR);
        alrtAjoutEvaluation = new Alert("SUCCESSFUL", "        Votre Evaluation a été ajoutée          avec succès !", imgAlertResto, AlertType.INFO);
        alrtProblemAjoutEvaluation = new Alert("ERREUR", "désolé Votre evaluation a écoué a cause des problèmes administrative !", imgAlertResto, AlertType.ERROR);
        alrtAjoutComment = new Alert("SUCCESSFUL", "        Votre Commentaire a été ajoutée            avec succès !", imgAlertResto, AlertType.INFO);
        alrtProblemAjoutEvaluation = new Alert("ERREUR", "désolé Votre commentaire n'est pas ajouté a cause des problèmes administrative !", imgAlertResto, AlertType.ERROR);
    }

    public void startApp() {
        
        //maj
        try {
            Display.getDisplay(this).setCurrent(new SplashScreen(this));
            Display.getDisplay(this).setCurrent(new SplashScreen2(this));
            Display.getDisplay(this).setCurrent(new SplashScreen3(this));
            Display.getDisplay(this).setCurrent(new SplashScreen4(this));
            Display.getDisplay(this).setCurrent(new SplashScreen5(this));
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        //
        //Form2
        f2.setTicker(t);
        f2.append(sp11);
        try {
            img = Image.createImage("/esprit/rt/image/img_lounge.png");
        } catch (IOException ex) {
        }

        imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
        f2.append(imgt);
        f2.append(sp4);
        f2.addCommand(cmdBack);
        
        f2.addCommand(cmdMaps);
        f2.addCommand(next);
//        f2.addCommand(cmdStat);
//        f2.addCommand(cmdEvaluation);
//        f2.addCommand(cmdCommentaire);
//        f2.addCommand(reserv1);
        f2.setCommandListener(this);
        f2.setItemStateListener(this);
        ThreadTopResto threadTopResto = new ThreadTopResto();
        cg3m = threadTopResto.cg3;
        f2.append(cg3m);
        //Form3
        f3.setTicker(t);
        f3.append(sp1);
        try {
            img = Image.createImage("/esprit/rt/image/img_lounge.png");
        } catch (IOException ex) {
        }

        imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
        f3.append(imgt);
        f3.append(cc);
        f3.addCommand(next);
        f3.addCommand(cmdBack);
//        f3.addCommand(cmdStat);
//        f3.addCommand(cmdEvaluation);
//        f3.addCommand(cmdCommentaire);
//        f3.addCommand(reserv1);
        f3.setCommandListener(this);
        f3.append(cg1);
        f3.setItemStateListener(this);

        choiceGroupIndex = f3.append(cg2m);
        //stringitemnameIndex = f3.append(resultRegionm_name);
        //Form4
        f4.addCommand(cmCam);
        f4.addCommand(cmHist);
        f4.addCommand(cmdBack);
        f4.setCommandListener(this);
        //Form6

        f6.append(commentaire);
        f6.addCommand(cmdBack);
        f6.addCommand(cmdCommenter);
        f6.setCommandListener(this);

        //disp.setCurrent(f1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    //
    public Vector sort(String[] e) {
        Vector v = new Vector();
        for (int count = 0; count < e.length; count++) {
            String s = e[count];
            int i = 0;
            for (i = 0; i < v.size(); i++) {
                int c = s.compareTo((String) v.elementAt(i));
                if (c < 0) {
                    v.insertElementAt(s, i);
                    break;
                } else if (c == 0) {
                    break;
                }
            }
            if (i >= v.size()) {
                v.addElement(s);
            }
        }
        return v;
    }

    //
    public void commandAction(Command c, Displayable d) {
        if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
        if (c == next && d == f2) {
            disp.setCurrent(new RestoCanvas2());
        }
        if (c == next && d == f3) {
            disp.setCurrent(new RestoCanvas1());
        }
        if (c == cmdStat) {
//            ThreadStatRestaurant threadStatRestaurant = new ThreadStatRestaurant();
//            result_statistique = threadStatRestaurant.info_stat;
//            f4.deleteAll();
//            f4.append(result_statistique);
//            disp.setCurrent(f4);

        }
        if (c == cmHist) {
            disp.setCurrent(new drawHistogramme());
        }

        if (c == cmCam) {
            disp.setCurrent(new drawCamember());
        }
        if (c == cmdEvaluation) {
            ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();
            String err = threadInfoResotByName.info_id;
            if (err == null) {
                //r

                disp.setCurrent(alrtIdRestoVide, f3);
            } else {
                disp.setCurrent(new SetEvaluaion());
            }
        }

        if (c == cmdCommentaire) {
            ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();
            String err = threadInfoResotByName.info_id;
            if (err == null) {
                disp.setCurrent(alrtIdRestoVide, f3);
            } else {
                disp.setCurrent(f6);
            }
        }

        if (c == cmdCommenter) {
            f3.deleteAll();
            f3.append(cg1);
            f3.append(cg2m);
            if (commentaire.getString().equals("")) {
                disp.setCurrent(alrtComment, f6);
            } else {
                ThreadCommentaire threadCommentaire = new ThreadCommentaire(commentaire.getString());
                //ici
                for (int i = 0; i <= 1; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }

                }

                if (threadCommentaire.reponse.equals("succes")) {
                    disp.setCurrent(alrtAjoutComment, f3);
                } else if (threadCommentaire.reponse.equals("problem")) {
                    disp.setCurrent(alrtProblemAjoutEvaluation, f6);
                }
            }
        }
        if ((c == cmdBack) && (d == f2)) {
            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);
            disp.setCurrent(new RestoCanvas());
        }
        if ((c == cmdBack) && (d == f3)) {
            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);

            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            disp.setCurrent(new RestoCanvas());
        }
        if ((c == cmdBack) && (d == f5)) {

            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);

            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            disp.setCurrent(new RestoCanvas1());
        }
        if ((c == cmdBack) && (d == f4)) {

            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);

            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            disp.setCurrent(new RestoCanvas1());
        }
        if ((c == cmdBack) && (d == f6)) {

            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);

            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            disp.setCurrent(new RestoCanvas1());
        }

        if ((c == cmdBack) && (d == reserve)) {

            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);

            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            disp.setCurrent(new RestoCanvas1());
        }

        //***maj
        if (c == login && ("".equals(log.getString()) || "".equals(pwd.getString()))) {
            erreur.setTimeout(Alert.FOREVER);
            erreur.setTicker(tickererreur);
            erreur.setTimeout(3000);

            disp.setCurrent(erreur);
        }

        if (c == login) {
            Login l = new Login(this);
        }
        if (c == cmdBack0) {
            confirm.deleteAll();
            disp.setCurrent(auth);
        }
        if (c == login1) {
            disp.setCurrent(new RestoCanvas());
        }
        if (c == reserv1 && (d == f3)) {
            f3.deleteAll();
            Display.getDisplay(this).setCurrent(new SplashScreen1(this));
        }
        if (c == reserv1 && (d == f2)) {
            f2.deleteAll();
            Display.getDisplay(this).setCurrent(new SplashScreen11(this));
        }

        if (c == reserv2 && ("".equals(nbplace.getString()) || "".equals(nbplats.getString()))) {
            erreur.setTimeout(Alert.FOREVER);
            erreur.setTicker(tickererreur);
            erreur.setTimeout(3000);

            disp.setCurrent(erreur);

        } else if (c == reserv2 && (d == reserve)) {
            reserve.deleteAll();
            termine();
        } else if (c == reserv3) {
            termine1();
        }
        if (c == EXIT) {
            resultat.deleteAll();
            disp.setCurrent(new RestoCanvas());
        }
        if (c == valider) {
            Thread th = new Thread(this);
            th.start();
        }

        if (c == valider1) {
            threadreserve = new Thread(new Runnable() {

                public void run() {

                    if (Thread.currentThread() == Current.mainMapsThread) {
            rHandlerMaps();
            Current.mainMapsThread.interrupt();
            return;
        }



                    try {
                        String dat = dateToString(date.getDate().getTime());
                        String tim = timeToString(date.getDate().getTime());
                        RestaurantGet rest = new RestaurantGet();
                        rest.run();
                        String idresto = rest.showRestoId(cg3m.getString(cg3m.getSelectedIndex()));
                        String id_cl = getid_client(log.getString());
                        String et = getetat(log.getString());
                        System.out.println("?date=" + dat + "%20" + tim + "&place=" + nbplace.getString().trim() + "&prix=" + s5.getText().trim() + "&id=" + idresto.trim() + "&id_cl=" + id_cl.trim() + "&etat=" + et.trim());
                        hc = (HttpConnection) Connector.open(url + "?date=" + dat + "%20" + tim + "&place=" + nbplace.getString().trim() + "&prix=" + s5.getText().trim() + "&id=" + idresto.trim() + "&id_cl=" + id_cl.trim() + "&etat=" + et.trim());
                        dis = new DataInputStream(hc.openDataInputStream());
                        while ((ch = dis.read()) != -1) {
                            sbb.append((char) ch);
                        }
                        if ("successfully added".equalsIgnoreCase(sbb.toString().trim())) {
                            try {

                                System.out.println("Succé");
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        } else if ("erreur".equalsIgnoreCase(sbb.toString().trim())) {
                            erreur2.setTimeout(Alert.FOREVER);
                            erreur2.setTicker(tickererreur);
                            erreur2.setTimeout(3000);

                            disp.setCurrent(erreur2);
                        }
                    } catch (IOException ex) {
                        ex.printStackTrace();

                    }

                }

            });
            threadreserve.start();
        }
    }

    public void run() {

        try {
            String dat = dateToString(date.getDate().getTime());
            String tim = timeToString(date.getDate().getTime());
            RestaurantGet rest = new RestaurantGet();
            rest.run();
            String idresto = rest.showRestoId(cg2m.getString(cg2m.getSelectedIndex()));
           
            String id_cl = getid_client(log.getString());
            String et = getetat(log.getString());
            System.out.println("?date=" + dat + "%20" + tim + "&place=" + nbplace.getString().trim() + "&prix=" + s5.getText().trim() + "&id=" + idresto.trim() + "&id_cl=" + id_cl.trim() + "&etat=" + et.trim());
            hc = (HttpConnection) Connector.open(url + "?date=" + dat + "%20" + tim + "&place=" + nbplace.getString().trim() + "&prix=" + s5.getText().trim() + "&id=" + idresto.trim() + "&id_cl=" + id_cl.trim() + "&etat=" + et.trim());
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sbb.append((char) ch);
            }
            if ("successfully added".equalsIgnoreCase(sbb.toString().trim())) {
                try {
                    Display.getDisplay(this).setCurrent(new SplashSuccee(this));

                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            } else if ("erreur".equalsIgnoreCase(sbb.toString().trim())) {
                erreur2.setTimeout(Alert.FOREVER);
                erreur2.setTicker(tickererreur);
                erreur2.setTimeout(3000);

                disp.setCurrent(erreur2);
            }
        } catch (IOException ex) {
            ex.printStackTrace();

        }
    }

    public void itemStateChanged(Item item) {

        //maj
        if (item == ch3) {
            if (ch3.isSelected(ch3.getSelectedIndex())) {
                MenuGet1 menu = new MenuGet1();
                menu.run();
                String m = menu.showMenuId(ch3.getString(ch3.getSelectedIndex()));
//                System.out.println("id"+m);

                ThreadPlatbyMenu platbymenu = new ThreadPlatbyMenu(m);
                platbymenu.run();

                ch4 = platbymenu.ch4;
                reserve.append(splat);
                reserve.append(ch4);
                reserve.append(space4);
            }
        }
        if (item == ch4) {

            reserve.append(nbplats);

        }

//
        if (item == cg1) {
            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            f3.append(cg2m);
            ThreadResotByRegion restobyregion = new ThreadResotByRegion(cg1.getString(cg1.getSelectedIndex()));
            f3.delete(choiceGroupIndex);
            cg2m = restobyregion.cg2;
            f3.append(cg2m);
        }

        if (item == cg2m) {

            f3.deleteAll();
            f3.setTicker(t);
            f3.append(sp1);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f3.append(imgt);
            f3.append(cc);
            f3.append(cg1);
            f3.append(cg2m);

            ThreadResotByRegion restobyregion = new ThreadResotByRegion(cg1.getString(cg1.getSelectedIndex()));
            ThreadInfoResotByName inforestobyname = new ThreadInfoResotByName(cg2m.getString(cg2m.getSelectedIndex()));

            resultRegionm_name = inforestobyname.info_name;
            resultRegionm_type = inforestobyname.info_type;
            resultRegionm_adresse = inforestobyname.info_adresse;
            resultRegionm_numTel = inforestobyname.info_numTel;

            f3.append(resultRegionm_name);
            f3.append(resultRegionm_type);
            f3.append(resultRegionm_adresse);
            f3.append(resultRegionm_numTel);
            //indexOfSI = 1;
            for (int i = 0; i <= 1; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }

            //System.out.println(inforestobyname.info_id);
            ThreadBonPlanResraurant threadBonPlanResraurant = new ThreadBonPlanResraurant(inforestobyname.info_id);

            for (int i = 0; i <= 1; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
//            System.out.println(threadBonPlanResraurant.res_description + " hi");

            String err = threadBonPlanResraurant.res_description;

            if (err != null) {

                resultRegionm_description = threadBonPlanResraurant.info_description;
                resultRegionm_dateDebut = threadBonPlanResraurant.info_dateDebut;
                resultRegionm_dateFin = threadBonPlanResraurant.info_dateFin;

                f3.append(resultRegionm_description);
                f3.append(resultRegionm_dateDebut);
                f3.append(resultRegionm_dateFin);
            }
            threadBonPlanResraurant.res_description = null;

//            else if (err == null) {
//                resultRegionm_description.setText("rien pour ce moment");
//                resultRegionm_dateDebut.setText("rien pour ce moment");
//                resultRegionm_dateFin.setText("rien pour ce moment");
//
//                f3.append(resultRegionm_description);
//                f3.append(resultRegionm_dateDebut);
//                f3.append(resultRegionm_dateFin);
//
//            }
        }

        if (item == cg3m) {

            f2.deleteAll();
            f2.setTicker(t);
            f2.append(sp11);
            try {
                img = Image.createImage("/esprit/rt/image/img_lounge.png");
            } catch (IOException ex) {
            }

            imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
            f2.append(imgt);
            f2.append(sp4);
            f2.append(cg3m);

            ThreadInfoResotByName inforestobyname1 = new ThreadInfoResotByName(cg3m.getString(cg3m.getSelectedIndex()));

            resultRegionm_name = inforestobyname1.info_name;
            resultRegionm_type = inforestobyname1.info_type;
            resultRegionm_adresse = inforestobyname1.info_adresse;
            resultRegionm_numTel = inforestobyname1.info_numTel;

            f2.append(resultRegionm_name);
            f2.append(resultRegionm_type);
            f2.append(resultRegionm_adresse);
            f2.append(resultRegionm_numTel);

            for (int i = 0; i <= 1; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

//                System.out.println(i);
            }

            //System.out.println(inforestobyname.info_id);
            ThreadBonPlanResraurant threadBonPlanResraurant = new ThreadBonPlanResraurant(inforestobyname1.info_id);

            for (int i = 0; i <= 1; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
//            System.out.println(threadBonPlanResraurant.res_description + " hi");

            String err = threadBonPlanResraurant.res_description;

            if (err != null) {

                resultRegionm_description = threadBonPlanResraurant.info_description;
                resultRegionm_dateDebut = threadBonPlanResraurant.info_dateDebut;
                resultRegionm_dateFin = threadBonPlanResraurant.info_dateFin;

                f2.append(resultRegionm_description);
                f2.append(resultRegionm_dateDebut);
                f2.append(resultRegionm_dateFin);
            }
            threadBonPlanResraurant.res_description = null;

        }

    }

    public class RestoCanvas extends Canvas implements CommandListener, Runnable {

        int w = getWidth();
        int h = getHeight();

        int x = w / 3 + 15;
        int y = h / 2 - 10;

        int xtprsto = w + 50;
        int ytprsto = w - 50;

        int xregion = w + 100;
        int yregion = w - 100;

        protected void paint(Graphics g) {

            g.setColor(96, 205, 203);
            g.fillRect(0, 0, w, h);

            g.setColor(255, 255, 255);
            g.drawString("Afficher par :", w / 2 - 30, h / 2 - 140, Graphics.TOP | Graphics.LEFT);
            g.drawString("TOP restaurant", w / 2 + 90, h / 2 - 70, Graphics.TOP | Graphics.RIGHT);
            g.drawString("région", w / 2 - 40, h / 2 + 100, Graphics.TOP | Graphics.LEFT);

            g.drawLine(0, h / 2 + 100, w, h / 3);

            g.drawImage(imgTopResto, w / 2 - 90, h / 2 - 110, Graphics.TOP | Graphics.LEFT);
            g.drawImage(imgRegion, w / 2 + 80, h / 2, Graphics.TOP | Graphics.RIGHT);

            g.drawImage(imgTopRestoR, xtprsto, ytprsto, Graphics.TOP | Graphics.LEFT);
            g.drawImage(imgRegionR, xregion, yregion, Graphics.TOP | Graphics.RIGHT);

            g.drawImage(imgFork, x, y, Graphics.TOP | Graphics.LEFT);

            this.addCommand(cmdNext);
            this.addCommand(cmdCanvasback);
            this.setCommandListener(this);

        }

        protected void keyPressed(int keyCode) {

            switch (getGameAction(keyCode)) {

                case UP: {

                    x = w / 2 - 47;
                    y = h / 2 - 120;

                    xtprsto = w / 2 - 90;
                    ytprsto = h / 2 - 110;

                    xregion = w + 100;
                    yregion = w - 100;

                    break;
                }

                case DOWN: {

                    x = w / 2 + 30;
                    y = h / 2 + 35;

                    xregion = w / 2 + 80;
                    yregion = h / 2;

                    xtprsto = w + 50;
                    ytprsto = w - 50;

                    break;
                }

            }

            repaint();

        }

        public void commandAction(Command c, Displayable d) {
            if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
            if ((c == cmdNext) && (x == ((w / 2) - 47)) && (y == ((h / 2) - 120))) {

                ThreadTopResto threadTopResto = new ThreadTopResto();
                cg3m = threadTopResto.cg3;
                f2.deleteAll();
                f2.setTicker(t);
                f2.append(sp11);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f2.append(imgt);
                f2.append(sp4);

                f2.append(cg3m);
                disp.setCurrent(f2);
            } else if ((c == cmdNext) && (x == ((w / 2) + 30)) && (y == ((h / 2) + 35))) {

                Thread th = new Thread(this);
                th.start();

                disp.setCurrent(f3);
            } else {

                disp.setCurrent(alrt, new RestoCanvas());

            }

            if (c == cmdCanvasback) {
                disp.setCurrent(auth);
            }
        }

        public void run() {
            try {
                // this will handle our XML
                RestaurantHandler restaurantHandler = new RestaurantHandler();
                // get a parser object
                SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                // get an InputStream from somewhere (could be HttpConnection, for example)
                HttpConnection hc = (HttpConnection) Connector.open("http://127.0.0.1/PIDevJ2ME/getXmlRestaurants3.php");
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                parser.parse(dis, restaurantHandler);
                // display the result
                restaurants = restaurantHandler.getRestaurant();

                int i = 0;

                if (restaurants.length > 0) {
                    for (i = 0; i < restaurants.length; i++) {

                        cg1.insert(i, restaurants[i].getRegion(), null);
                    }

                }

            } catch (Exception e) {
                System.out.println("Exception:" + e.toString());

            }

        }

//        
    }

    public class drawHistogramme extends Canvas implements CommandListener {

        int w = getWidth();
        int h = getHeight();
        int pourcentage;
        int k;
        int l;
        int j;

        public drawHistogramme() {
            addCommand(cmdBack);
            setCommandListener(this);
        }

        protected void paint(Graphics g) {
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, w);
            ThreadStatRestaurant threadStatRestaurant = new ThreadStatRestaurant();
            int s = 0;
            int[] ach = new int[threadStatRestaurant.r.length];

            int[] c1 = {255, 255, 255, 0, 172, 255, 126};
            int[] c2 = {0, 255, 0, 255, 5, 90, 40};
            int[] c3 = {0, 0, 255, 0, 125, 0, 0};

            for (int i = 0; i < threadStatRestaurant.r.length; i++) {
                //pourcentage = h * 30 / 100;
                //System.out.println(threadStatRestaurant.r[i]);
                ach[i] = threadStatRestaurant.r[i];
                pourcentage = (h * (threadStatRestaurant.r[i])) / 40;
//                k = k + 50;
//                l = l + 25;
//                j = j + 40;

//                k = new Random().nextInt(100) + 50 ;
//                l = k+25 ;
//                j = j+25 ;
                g.setColor(c1[i], c2[i], c3[i]);

                if (i == 0) {
                    s = 0;

                } else {
                    s = (w / (threadStatRestaurant.r.length) * i);

                }

                g.fillRect(s, h - pourcentage, w / (threadStatRestaurant.r.length), pourcentage);
                g.drawString(threadStatRestaurant.r2[i], s, h - (pourcentage + 1), Graphics.BASELINE | Graphics.LEFT);

            }
//            g.setColor(0, 0, 0);
//            for (int f = 0; f < threadStatRestaurant.r2.length; f++) {
//                if (f == 0) {
//                    s = 0;
//
//                } else {
//                    s = (w / (threadStatRestaurant.r.length) * f);
//
//                }
//
//                g.drawString(threadStatRestaurant.r2[f], s, h - (pourcentage + 1), Graphics.BASELINE | Graphics.LEFT);
//            }

        }

        public void commandAction(Command c, Displayable d) {
            if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
            if (c == cmdBack) {
                f2.deleteAll();
                f2.setTicker(t);
                f2.append(sp11);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f2.append(imgt);
                f2.append(sp4);
                f2.append(cg3m);

                f3.deleteAll();
                f3.setTicker(t);
                f3.append(sp1);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f3.append(imgt);
                f3.append(cc);
                f3.append(cg1);
                disp.setCurrent(new RestoCanvas1());

            }
        }
    }

    //**
    public class SetEvaluaion extends Canvas implements CommandListener {

        int w = getWidth();
        int h = getHeight();
        Image img;
        Image img2;
        int i = 0;

        public SetEvaluaion() {
            try {
                img = Image.createImage("/esprit/rt/image/staroun2.jpg");
                img2 = Image.createImage("/esprit/rt/image/staroun.jpg");
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

        protected void paint(Graphics g) {
            int note = 0;
            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, h);

            g.drawImage(img, 20, h / 2, Graphics.HCENTER | Graphics.VCENTER);
            g.drawImage(img, 60, h / 2, Graphics.HCENTER | Graphics.VCENTER);
            g.drawImage(img, 100, h / 2, Graphics.HCENTER | Graphics.VCENTER);
            g.drawImage(img, 140, h / 2, Graphics.HCENTER | Graphics.VCENTER);
            g.drawImage(img, 180, h / 2, Graphics.HCENTER | Graphics.VCENTER);

            if (i == 1) {
                g.drawImage(img2, 20, h / 2, Graphics.HCENTER | Graphics.VCENTER);

            }
            if (i == 2) {
                g.drawImage(img2, 20, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 60, h / 2, Graphics.HCENTER | Graphics.VCENTER);

            }
            if (i == 3) {
                g.drawImage(img2, 20, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 60, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 100, h / 2, Graphics.HCENTER | Graphics.VCENTER);

            }
            if (i == 4) {
                g.drawImage(img2, 20, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 60, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 100, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 140, h / 2, Graphics.HCENTER | Graphics.VCENTER);

            }
            if (i == 5) {
                g.drawImage(img2, 20, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 60, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 100, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 140, h / 2, Graphics.HCENTER | Graphics.VCENTER);
                g.drawImage(img2, 180, h / 2, Graphics.HCENTER | Graphics.VCENTER);

            }

            g.setColor(255, 0, 0);
            note = i;
            g.drawString("Choisissez une note sur 5 :", w / 2 - 50, h / 2 - 70, Graphics.TOP | Graphics.LEFT);
            g.drawString("Note : " + note + "/5", w / 2 - 20, h / 2 - 50, Graphics.TOP | Graphics.LEFT);

            this.addCommand(cmdEvaluer);
            this.addCommand(cmdBack);
            this.setCommandListener(this);
        }

        protected void keyPressed(int keyCode) {

            switch (getGameAction(keyCode)) {

                case LEFT:
                    if (i == 5) {
                        i -= 1;
                    } else if (i == 4) {
                        i -= 1;
                    } else if (i == 3) {
                        i -= 1;
                    } else if (i == 2) {
                        i -= 1;
                    } else if (i == 1) {
                        i -= 1;
                    }

                    break;
                case RIGHT:
                    if (i == 0) {
                        i += 1;
                    } else if (i == 1) {
                        i += 1;
                    } else if (i == 2) {
                        i += 1;
                    } else if (i == 3) {
                        i += 1;
                    } else if (i == 4) {
                        i += 1;
                    }
                    break;
            }
            repaint();

        }

        //commandListner setEvaluation
        public void commandAction(Command c, Displayable d) {
            if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
            f3.deleteAll();
            f3.append(cg1);
            f3.append(cg2m);

            if (c == cmdEvaluer) {
                if (i == 0) {
                    disp.setCurrent(alrtNoteVide, new SetEvaluaion());
                } else {
                    ThreadEvaluationResto threadEvaluationResto = new ThreadEvaluationResto(i);

                    //ici
                    for (int i = 0; i <= 1; i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }

                    }

                    if (threadEvaluationResto.reponse.equals("succes")) {
                        disp.setCurrent(alrtAjoutEvaluation, f3);
                    } else if (threadEvaluationResto.reponse.equals("problem")) {
                        disp.setCurrent(alrtAjoutEvaluation, new SetEvaluaion());
                    }
                }
            }

            if (c == cmdBack) {

                f2.deleteAll();
                f2.setTicker(t);
                f2.append(sp11);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f2.append(imgt);
                f2.append(sp4);
                f2.append(cg3m);

                f3.deleteAll();
                f3.setTicker(t);
                f3.append(sp1);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f3.append(imgt);
                f3.append(cc);
                f3.append(cg1);

                disp.setCurrent(new RestoCanvas1());
            }

        }

    }

    //***
    public class drawCamember extends Canvas implements CommandListener {

        int w = getWidth();
        int h = getHeight();
        int p;
        int j = 0;
        int k, l;
        int res = 0;

        public drawCamember() {
            addCommand(cmdBack);
            setCommandListener(this);

        }

        protected void paint(Graphics g) {

            g.setColor(255, 255, 255);
            g.fillRect(0, 0, w, h);

            ThreadStatRestaurant threadStatRestaurant = new ThreadStatRestaurant();
            int s = 0;
            int[] ach = new int[(threadStatRestaurant.r.length) + 1];

            int[] c1 = {255, 255, 255, 0, 172, 255, 126};
            int[] c2 = {0, 255, 0, 255, 5, 90, 40};
            int[] c3 = {0, 0, 255, 0, 125, 0, 0};

            ach[0] = 0;
            for (int i = 0; i < (threadStatRestaurant.r.length); i++) {
                j++;
                ach[j] = (int) ((threadStatRestaurant.r[i]) * (18));
                System.out.println(ach[i] + " " + ach[j]);
                res += ach[i];

                g.setColor(c1[i], c2[i], c3[i]);
                g.fillArc(w / 2 - 50, h / 2 - 50, 100, 100, res, ach[j]);
                g.drawString(threadStatRestaurant.r2[i], w / 2 - 56, h / 2 - (i * 25), Graphics.TOP | Graphics.RIGHT);
            }
//            int u =0;
//            for (int i = 0; i < ach.length; i++) {
////                k = k + 30;
////                l = l + 30;
//                //j = j + i;
//                u++;
//                System.out.println(ach[i]);
//                res += ach[i];
//                g.setColor(c1[i], c2[i], c3[i]);
//                g.fillArc(w / 2 - 50, h / 2 - 50, 100, 100, res, ach[u]);
//                //g.drawString(NostraCasa.getLabel(), w, 0, Graphics.TOP | Graphics.RIGHT);
//
//            }

        }

        public void commandAction(Command c, Displayable d) {
            if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
            if (c == cmdBack) {

                f2.deleteAll();
                f2.setTicker(t);
                f2.append(sp11);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f2.append(imgt);
                f2.append(sp4);
                f2.append(cg3m);

                f3.deleteAll();
                f3.setTicker(t);
                f3.append(sp1);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f3.append(imgt);
                f3.append(cc);
                f3.append(cg1);
                disp.setCurrent(new RestoCanvas1());

            }
        }
    }
//**maj methode

    public static Image createImage(String filename) {
        Image image = null;
        try {
            image = Image.createImage(filename);

        } catch (java.io.IOException ex) {
        }
        return image;
    }

    public void LoginDone() {
        auth.setTicker(loginTicker);
        bb.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        bb.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_VCENTER);
        auth.append(bb);
        try {
            imgg = Image.createImage("/esprit/rt/image/log.png");
        } catch (IOException ex) {
        }

        imgtt = new ImageItem("", imgg, ImageItem.LAYOUT_CENTER, null);
        auth.append(imgtt);

        auth.append(space5);
        auth.append(log);
        auth.append(space6);
        auth.append(pwd);
        auth.addCommand(login);
        auth.addCommand(EXIT1);
        auth.setCommandListener(this);
        disp.setCurrent(auth);
    }

    public void InfoPerson() {
        Ticker logint = new Ticker("Welcom : " + log.getString());
        confirm.setTicker(logint);
        wlcom.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        wlcom.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_VCENTER);
        wlcom.setText(log.getString());
        confirm.append(space7);
        confirm.append(wlcom);
        confirm.addCommand(login1);
        confirm.addCommand(cmdBack0);
        confirm.setCommandListener(this);
        disp.setCurrent(confirm);
    }

    public static String timeToString(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int h = c.get(Calendar.HOUR_OF_DAY);
        int m = c.get(Calendar.MINUTE);
        int s = c.get(Calendar.SECOND);
        String t = (h < 10 ? "0" : "") + "" + h + ":" + (m < 10 ? "0" : "") + m + ":" + (s < 10
                ? "0" : "") + s;
        return t;
    }

    public static String dateToString(long date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(date));
        int y = c.get(Calendar.YEAR);
        int m = c.get(Calendar.MONTH) + 1;
        int d = c.get(Calendar.DATE);
        String t = (y < 10 ? "0" : "") + y + "-" + (m < 10 ? "0" : "") + m + "-" + (d < 10
                ? "0" : "") + d;
        return t;
    }

    public void termine() {
        String s = cg1.getString(cg1.getSelectedIndex());
        Ticker tt = new Ticker("Votre reservation ");
        resultat.setTicker(tt);
        resultat.addCommand(valider);
        resultat.addCommand(EXIT);
        s0.setText(s);
        s0.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        sss.setText(date.getDate().toString());
        sss.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        s1.setText(nbplace.getString());
        s1.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        String menuu = "";
        for (int i = 0; i < ch3.size(); i++) {
            if (ch3.isSelected(i)) {
                menuu += ch3.getString(i);
                menuu += "\n";
            }
        }
        s2.setText(menuu);
        s2.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        String platt = "";
        for (int i = 0; i < ch4.size(); i++) {

            if (ch4.isSelected(i)) {
                platt += ch4.getString(i);
                platt += "\n";
            }

        }
        s3.setText(platt);
        s3.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        s4.setText(nbplats.getString());
        s4.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        int nbplac = Integer.parseInt(nbplace.getString());
        int nbpla = Integer.parseInt(nbplats.getString());
        float somme = nbpla * nbplac;
        String som = Float.toString(somme);
        s5.setText(som);
        resultat.append(s0);
        resultat.append(sss);
        resultat.append(s1);
        resultat.append(s2);
        resultat.append(s3);
        resultat.append(s4);
        resultat.append(s5);
        disp.setCurrent(resultat);
        resultat.setCommandListener(this);
    }

    public void termine1() {
        String s = cg3m.getString(cg3m.getSelectedIndex());
        Ticker tt = new Ticker("Votre reservation ");
        resultat.setTicker(tt);
        resultat.addCommand(valider);
        resultat.addCommand(EXIT);
        s0.setText(s);
        s0.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        sss.setText(date.getDate().toString());
        sss.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        s1.setText(nbplace.getString());
        s1.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        String menuu = "";
        for (int i = 0; i < ch3.size(); i++) {
            if (ch3.isSelected(i)) {
                menuu += ch3.getString(i);
                menuu += "\n";
            }
        }
        s2.setText(menuu);
        s2.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        String platt = "";
        for (int i = 0; i < ch4.size(); i++) {

            if (ch4.isSelected(i)) {
                platt += ch4.getString(i);
                platt += "\n";
            }

        }
        s3.setText(platt);
        s3.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        s4.setText(nbplats.getString());
        s4.setFont(Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, 16));
        int nbplac = Integer.parseInt(nbplace.getString());
        int nbpla = Integer.parseInt(nbplats.getString());

//        PlatGet plat=new PlatGet();
//        plat.run();
//        String id_plat=plat.showPlatId(ch4.getString(ch4.getSelectedIndex()));
//        float prixx=plat.showPlatprix(Integer.parseInt(id_plat));
        float prixx = 10;

        float somme = (nbpla * prixx);
        String som = Float.toString(somme);
        s5.setText(som);
        resultat.append(s0);
        resultat.append(sss);
        resultat.append(s1);
        resultat.append(s2);
        resultat.append(s3);
        resultat.append(s4);
        resultat.append(s5);

        disp.setCurrent(resultat);
        resultat.setCommandListener(this);
    }

    public void splashScreenDone1() {
        String s = cg2m.getString(cg2m.getSelectedIndex());
        Ticker yy = new Ticker(s);
        reserve.setTicker(yy);
        reserve.append(space);
        date.setDate(new Date());
        reserve.append(date);
        reserve.append(space1);
        reserve.append(nbplace);
        reserve.append(space2);
        reserve.append(smenu);
        MenuGet men = new MenuGet();
        men.run();
        ch3 = men.ch3;
        reserve.append(ch3);
        reserve.append(space3);
        reserve.setItemStateListener(this);
        reserve.addCommand(reserv2);
        reserve.addCommand(cmdBack);
        reserve.setCommandListener(this);
        disp.setCurrent(reserve);
    }

    public void splashScreenDone2() {
        String s = cg3m.getString(cg3m.getSelectedIndex());
        Ticker yy = new Ticker(s);
        reserve.setTicker(yy);
        reserve.append(space);
        date.setDate(new Date());
        reserve.append(date);
        reserve.append(space1);
        reserve.append(nbplace);
        reserve.append(space2);
        reserve.append(smenu);
        MenuGet men = new MenuGet();
        men.run();
        ch3 = men.ch3;
        reserve.append(ch3);
        reserve.append(space3);
        reserve.setItemStateListener(this);
        reserve.addCommand(reserv3);
        reserve.addCommand(cmdBack);
        reserve.setCommandListener(this);
        disp.setCurrent(reserve);
    }

    public String getid_client(String str) {
        client.run();
        String id_client = "";

        for (int i = 0; i < log.getString().length(); i++) {
            if (log.getString().charAt(i) == '@') {
                id_client = (client.showClientEmailId(log.getString()));
            } else {
                id_client = (client.showClientNomId(log.getString()));

            }
        }
        return id_client;

    }

    public String getetat(String str) {
        client.run();
        String etat = "";

        for (int i = 0; i < log.getString().length(); i++) {

            if (log.getString().charAt(i) == '@') {
                etat = (client.showClientEmailEtat(log.getString()));
            } else {
                etat = (client.showClientEtat(log.getString()));
            }
        }
        return etat;
    }

    public class RestoCanvas1 extends Canvas implements CommandListener, Runnable {

        int w = getWidth();
        int h = getHeight();

        int xstat = 1000;
        int ystat;

        int xnote = 1000;
        int ynote;

        int xres = 1000;
        int yres;

        int xcom = 1000;
        int ycom;

        protected void paint(Graphics g) {

            g.setColor(96, 205, 113);
            g.fillRect(0, 0, w, h);

            g.setColor(255, 255, 255);
            g.drawString("Selectionnez :", w / 2 - 30, h / 2 - 140, Graphics.TOP | Graphics.LEFT);
            g.drawString("Statistique ", w / 2 + 100, h / 2 - 70, Graphics.TOP | Graphics.RIGHT);
            g.drawString("Reservez ", w / 2 - 100, h / 2 + 80, Graphics.TOP | Graphics.LEFT);
            g.drawString("Commentez ", w / 2 + 110, h / 2 + 30, Graphics.TOP | Graphics.RIGHT);
            g.drawString("Notez ", w / 2 - 110, h / 2, Graphics.TOP | Graphics.LEFT);
//            g.drawLine(0, h / 2 + 100, w, h / 3);

            g.drawImage(stat1, w / 2 + 40, h / 2 - 120, Graphics.TOP | Graphics.RIGHT);
            g.drawImage(note1, w / 2 - 40, h / 2 - 30, Graphics.TOP | Graphics.RIGHT);
            g.drawImage(res1, w / 2 - 40, h / 2 + 60, Graphics.TOP | Graphics.LEFT);
            g.drawImage(com1, w / 2 + 20, h / 2 - 30, Graphics.TOP | Graphics.LEFT);

            g.drawImage(stat11, xstat, ystat, Graphics.TOP | Graphics.RIGHT);
            g.drawImage(note11, xnote, ynote, Graphics.TOP | Graphics.RIGHT);

            g.drawImage(res11, xres, yres, Graphics.TOP | Graphics.LEFT);
            g.drawImage(com11, xcom, ycom, Graphics.TOP | Graphics.LEFT);

//            g.drawImage(imgFork, x, y, Graphics.TOP | Graphics.LEFT);
            this.addCommand(cmdNext);
            this.addCommand(cmdCanvasback);
            this.setCommandListener(this);

        }

        protected void keyPressed(int keyCode) {

            switch (getGameAction(keyCode)) {

                case UP: {

                    xstat = w / 2 + 40;
                    ystat = h / 2 - 120;

                    xres = 1000;
                    xnote = 1000;
                    xcom = 1000;
                    break;
                }

                case DOWN: {

                    xres = w / 2 - 40;
                    yres = h / 2 + 60;

                    xstat = 1000;
                    xnote = 1000;
                    xcom = 1000;
                    break;
                }
                case LEFT: {

                    xnote = w / 2 - 40;
                    ynote = h / 2 - 30;

                    xstat = 1000;
                    xres = 1000;

                    xcom = 1000;

                    break;
                }
                case RIGHT: {

                    xcom = w / 2 + 20;
                    ycom = h / 2 - 30;

                    xstat = 1000;
                    xres = 1000;
                    xnote = 1000;

                    break;
                }

            }

            repaint();

        }

        public void commandAction(Command c, Displayable d) {
            if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }

            if ((c == cmdNext) && (xstat == (w / 2 + 40)) && (ystat == (h / 2 - 120))) {
                ThreadStatRestaurant threadStatRestaurant = new ThreadStatRestaurant();
                result_statistique = threadStatRestaurant.info_stat;
                f4.deleteAll();
                f4.append(result_statistique);
                disp.setCurrent(f4);
                f2.deleteAll();
                f2.setTicker(t);
                f2.append(sp11);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f2.append(imgt);
                f2.append(sp4);

                f2.append(cg3m);
//                disp.setCurrent(f2);
            } else if ((c == cmdNext) && (xres == (w / 2 - 40)) && (yres == (h / 2 + 60))) {
                if (cg3m.getSelectedIndex() != 0) {
                    splashScreenDone2();

                } else {
                    alrtIdRestoVide.setTimeout(Alert.FOREVER);
                    alrtIdRestoVide.setTicker(tickererreur);
                    alrtIdRestoVide.setTimeout(3000);

                    disp.setCurrent(alrtIdRestoVide);
                }
            } else if ((c == cmdNext) && (xnote == (w / 2 - 40)) && (ynote == (h / 2 - 30))) {
                ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();
                String err = threadInfoResotByName.info_id;
                if (err == null) {
                    //r

                    disp.setCurrent(alrtIdRestoVide, f3);
                } else {
                    disp.setCurrent(new SetEvaluaion());
                }

            } else if ((c == cmdNext) && (xcom == (w / 2 + 20)) && (ycom == (h / 2 - 30))) {

                ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();
                String err = threadInfoResotByName.info_id;
                if (err == null) {
                    disp.setCurrent(alrtIdRestoVide, f3);
                } else {
                    disp.setCurrent(f6);
                }

            } else {

                disp.setCurrent(alrt, new RestoCanvas());

            }

            if (c == cmdCanvasback) {
                disp.setCurrent(new RestoCanvas());
            }
        }

//        
        public void run() {

        }
    }
    
    
    
     public class RestoCanvas2 extends Canvas implements CommandListener, Runnable {

        int w = getWidth();
        int h = getHeight();

        int xstat = 1000;
        int ystat;

        int xnote = 1000;
        int ynote;

        int xres = 1000;
        int yres;

        int xcom = 1000;
        int ycom;

        protected void paint(Graphics g) {

            g.setColor(96, 205, 113);
            g.fillRect(0, 0, w, h);

            g.setColor(255, 255, 255);
            g.drawString("Selectionnez :", w / 2 - 30, h / 2 - 140, Graphics.TOP | Graphics.LEFT);
            g.drawString("Statistique ", w / 2 + 100, h / 2 - 70, Graphics.TOP | Graphics.RIGHT);
            g.drawString("Reservez ", w / 2 - 100, h / 2 + 80, Graphics.TOP | Graphics.LEFT);
            g.drawString("Commentez ", w / 2 + 110, h / 2 + 30, Graphics.TOP | Graphics.RIGHT);
            g.drawString("Notez ", w / 2 - 110, h / 2, Graphics.TOP | Graphics.LEFT);
//            g.drawLine(0, h / 2 + 100, w, h / 3);

            g.drawImage(stat1, w / 2 + 40, h / 2 - 120, Graphics.TOP | Graphics.RIGHT);
            g.drawImage(note1, w / 2 - 40, h / 2 - 30, Graphics.TOP | Graphics.RIGHT);
            g.drawImage(res1, w / 2 - 40, h / 2 + 60, Graphics.TOP | Graphics.LEFT);
            g.drawImage(com1, w / 2 + 20, h / 2 - 30, Graphics.TOP | Graphics.LEFT);

            g.drawImage(stat11, xstat, ystat, Graphics.TOP | Graphics.RIGHT);
            g.drawImage(note11, xnote, ynote, Graphics.TOP | Graphics.RIGHT);

            g.drawImage(res11, xres, yres, Graphics.TOP | Graphics.LEFT);
            g.drawImage(com11, xcom, ycom, Graphics.TOP | Graphics.LEFT);

//            g.drawImage(imgFork, x, y, Graphics.TOP | Graphics.LEFT);
            this.addCommand(cmdNext);
            this.addCommand(cmdCanvasback);
            this.setCommandListener(this);

        }

        protected void keyPressed(int keyCode) {

            switch (getGameAction(keyCode)) {

                case UP: {

                    xstat = w / 2 + 40;
                    ystat = h / 2 - 120;

                    xres = 1000;
                    xnote = 1000;
                    xcom = 1000;
                    break;
                }

                case DOWN: {

                    xres = w / 2 - 40;
                    yres = h / 2 + 60;

                    xstat = 1000;
                    xnote = 1000;
                    xcom = 1000;
                    break;
                }
                case LEFT: {

                    xnote = w / 2 - 40;
                    ynote = h / 2 - 30;

                    xstat = 1000;
                    xres = 1000;

                    xcom = 1000;

                    break;
                }
                case RIGHT: {

                    xcom = w / 2 + 20;
                    ycom = h / 2 - 30;

                    xstat = 1000;
                    xres = 1000;
                    xnote = 1000;

                    break;
                }

            }

            repaint();

        }

        public void commandAction(Command c, Displayable d) {
if (c == cmdMaps)
                    {
                initMaps();
                return;
                    }
            if (c == cmdSearch || c == cmdLocation || c == cmdCancel || c == cmdOk || c == cmdSkip || c == cmdBackOuss || c == cmdRestos || c == cmdBackOuss) {
            cAHandlerMaps(c, d);
        }
            if ((c == cmdNext) && (xstat == (w / 2 + 40)) && (ystat == (h / 2 - 120))) {
                ThreadStatRestaurant threadStatRestaurant = new ThreadStatRestaurant();
                result_statistique = threadStatRestaurant.info_stat;
                f4.deleteAll();
                f4.append(result_statistique);
                disp.setCurrent(f4);
                f2.deleteAll();
                f2.setTicker(t);
                f2.append(sp11);
                try {
                    img = Image.createImage("/esprit/rt/image/img_lounge.png");
                } catch (IOException ex) {
                }

                imgt = new ImageItem("", img, ImageItem.LAYOUT_CENTER, null);
                f2.append(imgt);
                f2.append(sp4);

                f2.append(cg3m);
//                disp.setCurrent(f2);
            } else if ((c == cmdNext) && (xres == (w / 2 - 40)) && (yres == (h / 2 + 60))) {
                if (cg2m.getSelectedIndex() != 0) {
                    splashScreenDone1();

                } else {
                    alrtIdRestoVide.setTimeout(Alert.FOREVER);
                    alrtIdRestoVide.setTicker(tickererreur);
                    alrtIdRestoVide.setTimeout(3000);

                    disp.setCurrent(alrtIdRestoVide);
                }
            } else if ((c == cmdNext) && (xnote == (w / 2 - 40)) && (ynote == (h / 2 - 30))) {
                ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();
                String err = threadInfoResotByName.info_id;
                if (err == null) {
                    //r

                    disp.setCurrent(alrtIdRestoVide, f3);
                } else {
                    disp.setCurrent(new SetEvaluaion());
                }

            } else if ((c == cmdNext) && (xcom == (w / 2 + 20)) && (ycom == (h / 2 - 30))) {

                ThreadInfoResotByName threadInfoResotByName = new ThreadInfoResotByName();
                String err = threadInfoResotByName.info_id;
                if (err == null) {
                    disp.setCurrent(alrtIdRestoVide, f3);
                } else {
                    disp.setCurrent(f6);
                }

            } else {

                disp.setCurrent(alrt, new RestoCanvas());

            }

            if (c == cmdCanvasback) {
                disp.setCurrent(new RestoCanvas());
            }
        }

//        
        public void run() {

        }
    }


//END


















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
            mp.setCommandListener(Resto_Tunisie.this);
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
            disp.setCurrent(reserve);
            //temchi lel restau
        }
        if (cmdRestos == c) {
            System.out.println("sqsq86554546466");
            tmpF = new List("Liste des restaurants", List.IMPLICIT);
            tmpF.addCommand(cmdCancel);
            tmpF.setCommandListener(this);
            Thread thtmp = new Thread() {

                public void run() {
                    //code here
                    //parsi w jib ID + NOM + NOTE + IMAGE
                    try {
            // this will handle our XML
            RestaurantHandlerOuss restaurantHandler= new RestaurantHandlerOuss();
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
            disp.setCurrent(f2);
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
                    mp.setCommandListener(Resto_Tunisie.this);
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
