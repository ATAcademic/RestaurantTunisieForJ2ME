package esprit.rt.splashscreen;


import esprit.rt.gui.Resto_Tunisie;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class SplashScreen3 extends Canvas implements Runnable {

    private final Resto_Tunisie midlet;
    private Image splashImage;
    private volatile boolean dismissed = false;

   public SplashScreen3(Resto_Tunisie midlet) throws InterruptedException {
        this.midlet = midlet;
        setFullScreenMode(true);
        splashImage = Resto_Tunisie.createImage("/esprit/rt/image/splash2.png");
        new Thread(this).start();
        Thread.sleep(1000);
    }

    public void run() {
        synchronized (this) {
            try {
                wait(3000);   // 3 seconds
            } catch (InterruptedException e) {
                // can't happen in MIDP: no Thread.interrupt method
            }
//            dismiss();
        }
    }

    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        g.setColor(0, 0, 0);  // white
        g.fillRect(0, 0, width, height);
        g.setColor(0, 0, 0);  // black
        g.drawRect(1, 1, width - 3, height - 3);  // red border one pixel from edge
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
//            midlet.splashScreenDone();
        }
    }

}
