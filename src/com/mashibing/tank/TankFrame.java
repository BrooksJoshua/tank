package com.mashibing.tank;


import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

/**
 * @author leewilliam
 */
public class TankFrame extends Frame {
    /**
     * 我方主战坦克
     */
    Tank myTank = new Tank(ResourceMgr.GAME_WIDTH/2, ResourceMgr.GAME_HEIGHT/2, Direction.UP,Group.GOOD, this);
    /**
     * 敌方坦克
     */
    public java.util.List<Tank> tanks = new ArrayList<Tank>();
    /**
     * 子弹
     */
    java.util.List<Bullet> bullets = new ArrayList<Bullet>();
    /**
     * 爆炸
     */
    public java.util.List<Explode> explodes = new ArrayList<Explode>();

    /**
     * 画布宽高
     */
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;
    /**
     * 初始位置
     */
    int x = 200, y = 200;
    /**
     * 移动步长
     */
    private static final int SPEED = 10;
    /**
     * 默认方向为 下 DOWN
     */
    Direction direction = Direction.DOWN;

    /**
     * 无参构造 默认初始化
     */
    public TankFrame() {
        setBackground(Color.BLACK);
        setVisible(true);
        System.out.println();
        /**
         * 赋予游戏界面大小为显示器大小。
         */
        setSize(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
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

    /**
     * 画出所有参与方： 坦克， 子弹， 爆炸等
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("子弹数量:" + bullets.size(), 20, 60);
        g.setColor(Color.RED);
        g.drawString("敌方数量:" + tanks.size(), 100, 60);
        g.drawString("爆炸的数量:" + explodes.size() , 180, 60);
        g.setColor(Color.cyan);
        g.drawString("我方位置:(" + myTank.x + ", " + myTank.y + ")", 300, 60);

        g.drawString("游戏界面尺寸:(" + ResourceMgr.GAME_WIDTH + ", " + ResourceMgr.GAME_HEIGHT + ")", 450, 60);
        g.setColor(color);
        myTank.paint(g);
        //用简易foreach会报错，Exception in thread "AWT-EventQueue-0" java.util.ConcurrentModificationException
        for (int i = 0; i < bullets.size(); i++) {
            this.bullets.get(i).paint(g);
        }

        for (int i = 0; i < explodes.size(); i++) {
            this.explodes.get(i).paint(g);
        }

        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }

        /**
         * 碰撞检测
         */
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
