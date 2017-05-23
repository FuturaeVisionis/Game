package kiloboltgame;

import java.awt.*;

/**
 * Created by ronald on 16/12/16.
 */
public class Tile {
    /**
     * tileX = x coordinate of the tile.
     * tileY = y ccordinate of the tile.
     * speedX = speed of the tile.
     * type = is the tile an ocean tile or a dirt tile?
     */
    private int tileX, tileY, speedX, type;
    public Image tileImage;


    /**
     * Create a background object called "bg", and point it to the "bg1" object in StartingClass.
     */
    private Background bg = StartingClass.getBg1();


    /**
     * Create a constructor which holds 3 parameters: x and y coordinate and the tile type
     * Each tile will have a value of 40 pixels.
     */
    public Tile(int x, int y, int typeInt) {
        tileX = x * 40;
        tileY = y * 40;
        type = typeInt;

        if (type == 1) {
            tileImage = StartingClass.tileocean;
        } else if (type == 2) {
            tileImage = StartingClass.tiledirt;
        }
    }

    /**
     * Create an update method. As of those of other classes, this update method will run on every loop (of the game loop).
     * Type 1 is the ocean tile. We want it to move slowly in the background; however, when the background is scrolling, we
     * want the ocean to scroll faster to accommodate movement. Also, you will notice that this ocean scrolls slower than
     * our character moves. This makes sense, because the character is moving right in front of the camera.
     */

    public void update() {
        if (type == 1) {

            if (bg.getSpeedX() == 0) {
                speedX = -1;

            } else {
                speedX = -2;
            }
        } else {
            speedX = bg.getSpeedX() * 5;
        }
        tileX += speedX;
    }

    public int getTileX() {
        return tileX;
    }

    public void setTileX(int tileX) {
        this.tileX = tileX;
    }

    public int getTileY() {
        return tileY;
    }

    public void setTileY(int tileY) {
        this.tileY = tileY;
    }

    public Image getTileImage() {
        return tileImage;
    }

    public void setTileImage(Image tileImage) {
        this.tileImage = tileImage;
    }
}

