import java.awt.*;
import java.awt.event.*;

/**
 * @author leewilliam
 */
public class TankFrame extends Frame {
    int x=200, y =200;
    public TankFrame() {
        setVisible(true);
        setSize(1000,800);
        setResizable(true);
        setTitle( "坦克大战");
        addKeyListener(new MyKeyAdapter());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g){
        System.out.println("当前位置: ("+x+", "+y+")");
        g.setColor(Color.BLACK);
        g.fillRect(x,y,200,200);
        x+=10;
        y+=10;
    }
    class MyKeyAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            x+=20;
            System.out.println("key pressed: "+e.getKeyCode());
           // repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            System.out.println("key released: "+e.getKeyCode() );
        }
    }

}
