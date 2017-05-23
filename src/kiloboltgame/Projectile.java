package kiloboltgame;

/**
 * Created by ronald on 09/12/16.
 */
public class Projectile {
    private int x, y, speedX;
    private boolean visible;

    /**
     * We want the constructor to take two params, a starting X coordinate and a starting Y coordinate, which will
     * represent the top left corner of each painted bullet.
     * @param startX
     * @param StartY
     */
    public Projectile( int startX, int StartY) {
        x = startX;
        y = StartY;
        speedX = 7;
        visible = true;
        /**
         * To set StartX and Y variable into the class wide x and y variables, and to set the speedX of the bullet
         * and to initialize the visible boolean, we declare the following four statements inside the constructor.
         */

    }

    /**
     * The update method in this class, as in the other classes , will be called with each update of the game.
     * Naturally, the, we should change the location of the bullet here with respect to spreed, and react when the
     * bullet collides ir goes off the screen (we won't handle collision just yet).
     */
    public void update(){
         x += speedX; // this statement will continually update the x coordinate by adding to it the speed in the x -direction
        if (x > 800){ // this statement checks if the bullet is off the screen, and makes it visible. In the other classes we
            visible = false;  // remove these bullets so they do not take up unnecessary.
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

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
