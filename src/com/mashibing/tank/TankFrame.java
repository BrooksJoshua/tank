package com.mashibing.tank;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author leewilliam
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(100, 250, Direction.UP,Group.GOOD, this);
    public java.util.List<Tank> tanks = new ArrayList<Tank>();
    java.util.List<Bullet> bullets = new ArrayList<Bullet>();
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    int x = 200, y = 200;
    private static final int SPEED = 10;
    Direction direction = Direction.DOWN;

    public TankFrame() {
        setBackground(Color.BLACK);
        setVisible(true);
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(true);
        setTitle("坦克大战");
        addKeyListener(new MyKeyAdapter());
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    Image offScreenImage = null;

    /**
     * 处理双缓冲， 原封不动copy到这里即可
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color color = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(color);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("子弹数量:" + bullets.size(), 20, 60);
        g.setColor(Color.RED);
        g.drawString("敌方数量:" + tanks.size(), 100, 60);
        g.setColor(Color.cyan);
        g.drawString("我方位置:(" + myTank.x + ", " + myTank.y + ")", 180, 60);
        g.setColor(color);
        myTank.paint(g);
        //用简易foreach会报错，Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
        for (int i = 0; i < bullets.size(); i++) {
            this.bullets.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collidesWith(tanks.get(j));
            }
        }

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
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDirection();
        }

        void setMainTankDirection() {
            if (!UP && !DW && !LT && !RT) myTank.setMoving(false);
            else {
                myTank.setMoving(true);
                if (UP) myTank.setDirection(Direction.UP);
                if (DW) myTank.setDirection(Direction.DOWN);
                if (LT) myTank.setDirection(Direction.LEFT);
                if (RT) myTank.setDirection(Direction.RIGHT);
            }

        }
    }

}
