package esprit.rt.splashscreen;


import esprit.rt.gui.Resto_Tunisie;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SplashScreen1 extends Canvas implements Runnable {

    private final Resto_Tunisie midlet;
    private Image splashImage;
    private volatile boolean dismissed = false;

  public  SplashScreen1(Resto_Tunisie midlet) {
        this.midlet = midlet;
        setFullScreenMode(true);
        splashImage = Resto_Tunisie.createImage("/esprit/rt/image/aok.png");
        new Thread(this).start();
    }

    public void run() {
        synchronized (this) {
            try {
                wait(3000L);   // 3 seconds
            } catch (InterruptedException e) {
                // can't happen in MIDP: no Thread.interrupt method
            }
            dismiss();
        }
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(255, 255, 255);  // white
        g.fillRect(0, 0, width, height);
        g.setColor(40, 40, 40);  // black

        g.drawRect(1, 1, width - 3, height - 3);  // red border one pixel from edge
        g.drawRect(2, 2, width - 3, height - 3);
        g.drawRect(3, 3, width - 3, height - 3);
        g.drawRect(4, 4, width - 3, height - 3);
        g.drawRect(5, 5, width - 3, height - 3);
        g.drawRect(6, 6, width - 3, height - 3);
        g.drawRect(7, 7, width - 3, height - 3);
        g.drawRect(8, 8, width - 3, height - 3);
        
        g.setColor(255, 128, 0);
        g.drawArc(width / 2 - 100, height / 2 - 90, 200, 200, 0, 360);
        g.drawArc(width / 2 - 100, height / 2 - 90, 199, 199, 0, 360);
        g.drawArc(width / 2 - 100, height / 2 - 90, 198, 198, 0, 360);
        g.drawArc(width / 2 - 100, height / 2 - 90, 197, 197, 0, 360);
        g.drawArc(width / 2 - 100, height / 2 - 90, 196, 196, 0, 360);
        g.drawArc(width / 2 - 100, height / 2 - 90, 195, 195, 0, 360);
        g.drawArc(width / 2 - 100, height / 2 - 90, 194, 194, 0, 360);
        if (splashImage != null) {
            g.drawImage(splashImage,
                    width / 2,
                    height / 2,
                    Graphics.VCENTER | Graphics.HCENTER);
            splashImage = null;
        }
    }

    private void dismiss() {
        if (!dismissed) {
            dismissed = true;
            midlet.splashScreenDone1();
        }
    }

}
