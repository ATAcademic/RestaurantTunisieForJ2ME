package tn.resto.maps;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;

/**
 *
 *
 * @author Pingouins du désert
 * 
 *
 */

abstract class MainMapCanvas extends Canvas 
{
    
        Command back;
	Displayable testListScreen;
	MIDlet midlet;

	public MainMapCanvas()
	{
            
		

//		addCommand(back = new Command("Back", Command.BACK, 1));

//		setCommandListener(this);
	}

//	public void commandAction(Command c, Displayable d)
//	{
//		if(c == back)
//		{
//			Display.getDisplay(midlet).setCurrent(testListScreen);
//		}
//	}
	void showError(String message)
	{
		Alert error = new Alert("Error", message, null, AlertType.ERROR);

		Display.getDisplay(midlet).setCurrent(error, this);
                
	}
    
}
