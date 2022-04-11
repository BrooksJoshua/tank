import java.awt.*;

public class Tank {
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
    private TankDirections direction ;
    /**
     * 每次移动的步长
     */
    private static final int SPEED = 10;
    public Tank (int x, int y, TankDirections direction){
        super();
        this.x = x;
        this.y = y;
        this.direction = direction;
    }

    /**
     * 刷新画布
     * @param g
     */
    public void paint(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(x,y,50,50);
        move(g);
    }

    /**
     * 只有为非静止状态才开始移动
     * @param g
     */
    private void move(Graphics g) {
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

    public TankDirections getDirection() {
        return direction;
    }

    public void setDirection(TankDirections direction) {
        this.direction = direction;
    }
    public boolean getMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }
}
