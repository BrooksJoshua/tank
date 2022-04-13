package com.mashibing.tank;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ResourceMgr {
    public static BufferedImage tankL, tankR, tankU, tankD;
    public static BufferedImage bulletL, bulletR, bulletU, bulletD;
    public static BufferedImage[] explosions = new BufferedImage[16];
    /**
     * 游戏界面宽度 Toolkit.getDefaultToolkit().getScreenSize().width;
     */
    public static int GAME_WIDTH = 800;
    /**
     * 游戏界面高度 Toolkit.getDefaultToolkit().getScreenSize().height;
     */
    public static int GAME_HEIGHT = 600;

    /**
     * 若加载错误
     */
    static {
        try {
            /**
             * 坦克Image
             */
            tankU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png")));
            tankL = ImageUtil.rotateImage(tankU,-90);
            tankR = ImageUtil.rotateImage(tankU,90);
            tankD = ImageUtil.rotateImage(tankU,180);
            /**
             * 子弹Image
             */
            bulletU = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png")));
            bulletL = ImageUtil.rotateImage(bulletU,-90);
            bulletR = ImageUtil.rotateImage(bulletU,90);
            bulletD = ImageUtil.rotateImage(bulletU,180);
            /**
             * 爆炸💥Image
             */
            for (int i = 0; i < 16; i++) {
                String explosionPath = "images/e"+(i+1)+".gif";
                explosions[i] = ImageIO.read(Objects.requireNonNull(ResourceMgr.class.getClassLoader().getResourceAsStream(explosionPath)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
