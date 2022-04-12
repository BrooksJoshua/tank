package com.mashibing.tank;

import java.awt.*;

public class Tank {
    private TankFrame tf;
    /**
     *  moving 初始状态微静止
     */
    private boolean moving = false;


    /**
     * （x,y）为坦克的初始位置
      */
    private int x, y;
    /**
     * 坦克方向
     */
    private Direction direction ;
    /**
     * 每次移动的步长
     */
    private static final int SPEED = 5;
    public Tank (int x, int y, Direction direction,TankFrame tf){
        super();
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tf = tf;
    }

    /**
     * 刷新画布
     * @param g
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,50,50);
        g.setColor(color);
        move();
    }

    /**
     * 只有为非静止状态才开始移动
     */
    private void move() {
        if(!moving) return ;
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
        System.out.println("移动后: ("+x+", "+y+")");
        System.out.println("\n");
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public void fire(){
        tf.myBullet = new Bullet(this.x, this.y, this.direction);
    }
}
