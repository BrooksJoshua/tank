package com.mashibing.tank;

import java.awt.*;

public class Explode {
    private static final int WIDTH = ResourceMgr.explosions[0].getWidth(), HEIGHT =  ResourceMgr.explosions[0].getHeight();
    private int x , y;
    private boolean living = true;
    private TankFrame tf = null;
    /**
     * 用来记录每次加载哪张图片
     */
    private int step = 0 ;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Audio("audio/explode.wav").play();
    }

    public void paint(Graphics g){
        g.drawImage(ResourceMgr.explosions[step++], x, y, null);
        if(step >= ResourceMgr.explosions.length)  {
            /**
             * 爆炸结束后要从list移除
             */
            tf.explodes.remove(this);
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
