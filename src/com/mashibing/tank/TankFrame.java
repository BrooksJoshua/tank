package com.mashibing.tank;

import java.awt.*;
import java.awt.event.*;

/**
 * @author leewilliam
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200,200, Direction.RIGHT);
    Bullet  myBullet  = new Bullet(300,300,Direction.DOWN);
    int x=200, y =200;
    private static final int SPEED = 10;
    Direction direction = Direction.DOWN;
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
        myTank.paint(g);
        myBullet.paint(g);
        System.out.println("当前位置: ("+x+", "+y+")");

    }
    class MyKeyAdapter extends KeyAdapter {

        boolean UP = false;
        boolean DW = false;
        boolean LT = false;
        boolean RT = false;


        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                   LT = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    RT = true;
                    break;
                case KeyEvent.VK_UP:
                    UP = true;
                    break;
                case KeyEvent.VK_DOWN:
                     DW = true;
                    break;
                default:
                    break;
            }

            setMainTankDirection();
            System.out.println("key pressed: "+e.getKeyCode());
           // repaint();
        }

        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    LT = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    RT = false;
                    break;
                case KeyEvent.VK_UP:
                    UP = false;
                    break;
                case KeyEvent.VK_DOWN:
                    DW = false;
                    break;
                default:
                    break;
            }
            setMainTankDirection();
        }
        void setMainTankDirection(){
            if(!UP && !DW && !LT && !RT) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if(UP) myTank.setDirection(Direction.UP);
                if(DW) myTank.setDirection(Direction.DOWN);
                if(LT) myTank.setDirection(Direction.LEFT);
                if(RT) myTank.setDirection(Direction.RIGHT);
            }

        }
    }

}
