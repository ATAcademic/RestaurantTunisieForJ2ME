/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.resto.env;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.location.Coordinates;

/**
 *
 * @author Aditsan Kadmus
 */
public class Current {
    public static Vector cords = new Vector();
    public static Vector paths = new Vector();
    public static int clientId = 0;
    public static String url = "";
    public static String nameResto = "";
    public static boolean isPath = false;
    public static Thread mainMapsThread;
    public static Thread mainMapsThreadClone;
    public static Vector restoIds;
    public static Display disp;
    public static Coordinates lastPos = new Coordinates(36.899313 ,10.188750 , 0);
    public static boolean timer = false;
    public static Command lastCmd;
    
}
