import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.TimeUnit;

public class T {
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        while (true) {
                TimeUnit.MILLISECONDS.sleep(50);
                tf.repaint();
        }
    }
}