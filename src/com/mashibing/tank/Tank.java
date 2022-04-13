package com.mashibing.tank;

import com.mashibing.tank.ResourceMgr;

import java.awt.*;
import java.util.Random;

public class Tank {
    public static final int WIDTH = ResourceMgr.goodTankD.getWidth(), HEIGHT = ResourceMgr.goodTankD.getHeight();
    private Random rand = new Random();
    private TankFrame tf;
    /**
     * 坦克分组, 默认是敌方
     */
    private Group group = Group.BAD;
    /**
     * moving 初始状态微静止
     */
    private boolean moving = true;


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
        /**
         * 根据类型加载不同图片
         */
        switch (this.direction) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankL:ResourceMgr.badTankL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankR:ResourceMgr.badTankR, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankU:ResourceMgr.badTankU, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD?ResourceMgr.goodTankD:ResourceMgr.badTankD, x, y, null);
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
        /**
         * 只有敌方的子弹才是随机发的。
         */
        if(this.group == Group.BAD && rand.nextInt(100)>95) this.fire();
        /**
         * 敌方坦克随机改变方向
         */
        if(this.group == Group.BAD&& rand.nextInt(100)>90)
            randChangeDir();
    }

    private void randChangeDir() {
        this.direction = Direction.values()[rand.nextInt(4)];
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
