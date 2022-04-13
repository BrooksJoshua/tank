package com.mashibing.tank;

import java.awt.*;

public class Bullet {
    private static final int SPEED = 10;
    public static final int WIDTH = ResourceMgr.bulletD.getWidth(), HEIGHT = ResourceMgr.bulletD.getHeight();
    private boolean alive = true;
    /**
     * 坦克分组, 默认是敌方
     */
    private Group group = Group.BAD;
    TankFrame tf = null;
    /**
     * （x,y）为子弹的初始位置
     */
    private int x, y;

    /**
     * 子弹方向
     */
    private Direction direction;

    public Bullet(int x, int y, Direction direction,Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.tf = tf;
        this.group = group;
    }

    void paint(Graphics g) {
        if (!alive) {
            tf.bullets.remove(this);
        }
        //删除非存活子弹
        if (!alive) tf.bullets.remove(this);
        switch (this.direction) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;

        }
        move(g);

    }

    private void move(Graphics g) {
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
        if (this.x < 0 || this.x > TankFrame.GAME_WIDTH
                || this.y < 0 || this.y > TankFrame.GAME_HEIGHT) {
            this.alive = false;
        }
    }

    public void collidesWith(Tank tank) {
        Rectangle bulletRect = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle tankRect = new Rectangle(tank.x, tank.y, Tank.WIDTH, Tank.HEIGHT);
        boolean intersects = bulletRect.intersects(tankRect);
        if(this.getGroup() == tank.getGroup()) {return ;}
        /**
         * 碰撞到了则做对应处理: 从集合中remove
         */

        if (intersects) {
            tank.die();
            this.die();
        }
        /*
        if (tf.tanks.size() == 0) {
            System.out.println("胜利！");
            System.exit(0);
        }
         */
    }

    private void die() {
        alive = false;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
