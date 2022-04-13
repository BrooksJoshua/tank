package com.mashibing.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    public static final int WIDTH = ResourceMgr.tankD.getWidth(), HEIGHT = ResourceMgr.tankD.getHeight();
    private Random rand = new Random();
    private TankFrame tf;
    /**
     * 坦克分组, 默认是敌方
     */
    private Group group = Group.BAD;
    /**
     * moving 初始状态微静止
     */
    private boolean moving = false;


    /**
     * （x,y）为坦克的初始位置
     */
    public int x, y;
    /**
     * 坦克方向
     */
    private Direction direction;
    /**
     * 每次移动的步长
     */
    private static final int SPEED = 3;
    private boolean alive = true ;

    public Tank(int x, int y, Direction direction,Group group, TankFrame tf) {
        super();
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tf = tf;
        this.group = group;
    }

    /**
     * 刷新画布
     *
     * @param g
     */
    public void paint(Graphics g) {
        if(!alive){
            tf.tanks.remove(this);
            return;
        }
        switch (this.direction) {
            case LEFT:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);
                break;
            default:
                break;

        }
        move();
    }

    /**
     * 只有为非静止状态才开始移动
     */
    private void move() {
        if (!moving) return;
        switch (direction) {
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            case LEFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            default:
                break;
        }
        if(rand.nextInt(10)>8) this.fire();
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

    public void fire() {
        int bx = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int by = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tf.bullets.add(new Bullet(bx, by, this.direction,this.group , this.tf));
    }

    public void die() {
        this.alive = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
