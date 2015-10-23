/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tn.resto.maps;

import com.jappit.midmaps.googlemaps.GoogleMaps;
import com.jappit.midmaps.googlemaps.GoogleMapsCoordinates;
import com.jappit.midmaps.googlemaps.GoogleMapsMarker;
import com.jappit.midmaps.googlemaps.GoogleMapsPath;
import com.jappit.midmaps.googlemaps.GoogleStaticMap;
import com.jappit.midmaps.googlemaps.GoogleStaticMapHandler;
import java.util.Vector;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.location.Coordinates;
import javax.microedition.location.Criteria;
import javax.microedition.location.Location;
import javax.microedition.location.LocationException;
import javax.microedition.location.LocationProvider;
import javax.microedition.midlet.MIDlet;
import tn.resto.env.Current;

/**
 *
 * @author Aditsan Kadmus
 */
public class MapCanvas extends MainMapCanvas implements GoogleStaticMapHandler {

    GoogleMaps gMaps = null;
    GoogleStaticMap map = null;
    GoogleMapsCoordinates gmc;

    public MapCanvas( Vector paths, Vector markers) throws LocationException, InterruptedException{
        super();
        Criteria cr = new Criteria();
        cr.setHorizontalAccuracy(500);
        Location loc;
        LocationProvider lp;
//        try {
//            lp = LocationProvider.getInstance(cr);
//             if (lp.getState()==LocationProvider.AVAILABLE)
//             {
//                    loc = lp.getLocation(30);
//                    Coordinates c = loc.getQualifiedCoordinates();
//
//                    double lat = c.getLatitude();
//                    double lng = c.getLongitude();
//                    if (lat == 0.0)
//                    {
//                        lat=36.899313;
//                    }
//                    if (lng == 0.0)
//                    {
//                        lng=10.188750;
//                    }
                    gmc = new GoogleMapsCoordinates(Current.lastPos.getLatitude(), Current.lastPos.getLongitude());
//                }
//             else
//             {
//                    gmc = new GoogleMapsCoordinates(36.899313, 10.188750);
//             }
//        } catch (LocationException ex) {
//           gmc = new GoogleMapsCoordinates(36.899313, 10.188750);
//        }
        

        gMaps = new GoogleMaps();

        map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);

        map.setHandler(this);
        

        map.setCenter(gmc);

        map.setZoom(15);

        

        GoogleMapsMarker redMarker = new GoogleMapsMarker(gmc);
	redMarker.setColor(GoogleStaticMap.COLOR_RED);
	redMarker.setSize(GoogleMapsMarker.SIZE_MID);

	map.addMarker(redMarker);
        System.out.println(markers.elementAt(0).toString());
        if (markers != null)
        for (int i=0; i<markers.size(); i++)
        {
            System.out.println("i = "+i);
            System.out.println("size = "+markers.size());
//            if (i == 0)
//                drawMarker(markers.elementAt(i).toString(),GoogleStaticMap.COLOR_GREEN);
//            else
                drawMarker(markers.elementAt(i).toString(),GoogleStaticMap.COLOR_YELLOW);
            if (Current.isPath)
        {
           paths.addElement(markers.elementAt(i).toString()+"|"+gmc.latitude+":"+gmc.longitude);
        }
        }
        
        if (paths != null)
        for (int i=0; i<paths.size(); i++)
        {
            drawPath(paths.elementAt(i).toString());
        }
        
        map.update();
    }





    public MapCanvas() {
        super();
//        Criteria cr = new Criteria();
//        cr.setHorizontalAccuracy(500);
//        Location loc;
//        LocationProvider lp;
//        try {
//            lp = LocationProvider.getInstance(cr);
//             if (lp.getState()==LocationProvider.AVAILABLE)
//             {
//                    loc = lp.getLocation(30);
//                    Coordinates c = loc.getQualifiedCoordinates();
//
//                    double lat = c.getLatitude();
//                    double lng = c.getLongitude();
//                    if (lat == 0.0)
//                    {
//                        lat=36.899313;
//                    }
//                    if (lng == 0.0)
//                    {
//                        lng=10.188750;
//                    }
                    gmc = new GoogleMapsCoordinates(Current.lastPos.getLatitude(), Current.lastPos.getLongitude());
//                }
//             else
//             {
//                    gmc = new GoogleMapsCoordinates(36.899313, 10.188750);
//             }
//        } catch (LocationException ex) {
//           gmc = new GoogleMapsCoordinates(36.899313, 10.188750);
//        }


        gMaps = new GoogleMaps();
        
        map = gMaps.createMap(getWidth(), getHeight(), GoogleStaticMap.FORMAT_PNG);

        map.setHandler(new MapCanvas());
        
        map.setCenter(gmc);

        map.setZoom(15);



        GoogleMapsMarker redMarker = new GoogleMapsMarker(gmc);
	redMarker.setColor(GoogleStaticMap.COLOR_RED);
	redMarker.setSize(GoogleMapsMarker.SIZE_MID);

	map.addMarker(redMarker);

        map.update();
    }
















    public void drawPath (String path)
    {
        String position1= path.substring(0, path.indexOf("|")).trim();
        String position2= path.substring(path.indexOf("|")+1).trim();
        GoogleMapsCoordinates gc1 = new GoogleMapsCoordinates(Double.parseDouble(position1.substring(0, position1.indexOf(":")).trim()), Double.parseDouble(position1.substring(position1.indexOf(":")+1)));
        GoogleMapsCoordinates gc2 = new GoogleMapsCoordinates(Double.parseDouble(position2.substring(0, position2.indexOf(":")).trim()), Double.parseDouble(position2.substring(position2.indexOf(":")+1)));
        GoogleMapsPath thepath = new GoogleMapsPath();
        thepath.addPoint(gc1);
        thepath.addPoint(gc2);
        thepath.setColor(GoogleStaticMap.COLOR_RED);
        thepath.setWeight(10);
        map.addPath(thepath);
    }


    public void drawMarker(String position, String color)
    {
        System.out.println("***"+position);
        double x = Double.parseDouble(position.substring(0, position.indexOf(":")).trim());
        double y = Double.parseDouble(position.substring(position.indexOf(":")+1));
        GoogleMapsCoordinates gc = new GoogleMapsCoordinates(x, y);
        
        GoogleMapsMarker greenMarker = new GoogleMapsMarker(gc);
	greenMarker.setColor(color);
	greenMarker.setSize(GoogleMapsMarker.SIZE_SMALL);
        System.out.println(x+":"+y);
	map.addMarker(greenMarker);
    }
    protected void paint(Graphics g) {
        System.out.println("W = "+ this.getWidth() + " H = "+ this.getHeight());this.getWidth();
        map.draw(g, 0, 0, Graphics.TOP | Graphics.LEFT);
        
    }

    public void GoogleStaticMapUpdateError(GoogleStaticMap map, int errorCode, String errorMessage) {
        showError("map error: " + errorCode + ", " + errorMessage);
    }

    public void GoogleStaticMapUpdated(GoogleStaticMap map) {
        repaint();
    }

    protected void keyPressed(int key) {
        int gameAction = getGameAction(key);

        if (gameAction == Canvas.UP || gameAction == Canvas.RIGHT || gameAction == Canvas.DOWN || gameAction == Canvas.LEFT) {
            map.move(gameAction);
        }
    }

    protected void pointerPressed(int x, int y) {
        double tmpX=-99990, tmpY=-99990;
        for (int i=0; i<Current.cords.size(); i++)
        {
            tmpX = Double.parseDouble(((String) Current.cords.elementAt(i)).substring(0, ((String) Current.cords.elementAt(i)).indexOf(":")).trim());
            tmpX *= (tmpX*getWidth())/360;
            tmpY = Double.parseDouble(((String) Current.cords.elementAt(i)).substring(((String)Current.cords.elementAt(i)).indexOf(":")+1));
            tmpY *= ((tmpY*getHeight())/180);
            System.out.println(tmpX+"#####"+tmpY+"\n"+x+"******"+y);
            if ((tmpX >= x-10) && (tmpX <= x+10))
            {
                if ((tmpY >= y-10) && (tmpY <= y+10))
                {
                    System.out.println("BINGO!");
                    return;
                }
            }
        }
        
        super.pointerPressed(x, y);
        System.out.println(x+" "+y);
        
        
    }
    
}

