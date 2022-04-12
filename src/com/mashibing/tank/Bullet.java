package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 3 ;
    private static final int WIDTH = 15, HEIGHT = 15;
    /**
     * （x,y）为子弹的初始位置
     */
    private int x, y;

    /**
     * 子弹方向
     */
    private Direction direction ;

    public Bullet(int x, int y, Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

   void paint(Graphics g){
       Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(color);
        move(g);

   }

    private void move(Graphics g) {
        switch (direction){
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            default:
                break;
        }
        System.out.println("\n");
    }
}
