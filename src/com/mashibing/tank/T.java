package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class T {
    public static void main(String[] args) throws InterruptedException, IOException {
        TankFrame tf = new TankFrame();
        /**
         * 初始化敌方坦克
         */
        initEnemy(tf,10);

        while (true) {
                TimeUnit.MILLISECONDS.sleep(50);
                tf.repaint();
        }
    }

    private static void initEnemy(TankFrame tankFrame, int enemySize) {
        for (int i = 0; i < enemySize; i++) {
            tankFrame.tanks.add(new Tank(100+50*i,200, Direction.DOWN,Group.BAD, tankFrame));
        }
    }
}
