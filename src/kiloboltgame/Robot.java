package kiloboltgame;

import java.util.ArrayList;

/**
 * Created by ronald on 18/09/16.
 */
public class Robot {

    //Constants are here

    final int JUMPEDSPEED = -15;
    final int MOVESPEED = 5;
    final int GROUND = 382;

    private int centerX = 100;
    private int centerY = GROUND;
    private boolean jumped = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
    private boolean ducked = false;

    private static Background bg1 = StartingClass.getBg1();
    private static Background bg2 = StartingClass.getBg2();

    private int speedX = 0;
    private int speedY = 1;


    /**
     * It would be extremely time consuming and inefficient if we were to manually create bullets each time that the
     * player pressed "shoot. So we will be implementing a type of List called an ArrayList, which will allow us to store
     * our Projectile objects in a "container" of increasing size.
     */
    private ArrayList<Projectile> projectiles = new ArrayList<>();


    public void update() {

        //Moves character or scrolls background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        }

        if (speedX == 0 || speedX < 0) {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
        }
        if (centerX <= 200 && speedX > 0) {
            centerX += speedX;
        }
        if (speedX > 0 && centerX > 200) {
            bg1.setSpeedX(-MOVESPEED/5);
            bg2.setSpeedX(-MOVESPEED/5);
        }

        //Updates Y position.
        centerY += speedY;
        if (centerY + speedY >= GROUND) {
            centerY = GROUND;
        }

        //Handles jumping.
        if (jumped == true) {
            speedY += 1;
            if (centerY + speedY >= GROUND) {
                centerY = GROUND;
                speedY = 0;
                jumped = false;
            }
        }

        //Prevents going beyond X coordinate of 0
        if (centerX + speedX <= 60) {
            centerX = 61;
        }
    }

    public void moveRight() {
        if (ducked == false) {
            speedX = MOVESPEED;
        }
    }

    public void moveLeft() {
        if (ducked == false) {
            speedX = -MOVESPEED;
        }
    }

    public void stopRight() {
        setMovingRight(false);
        stop();
    }

    public void stopLeft() {
        setMovingLeft(false);
        stop();
    }

    private void stop() {
        if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }
        if (isMovingRight() == false && isMovingRight() == true) {
            moveLeft();
        }
        if (isMovingRight() == true && isMovingLeft() == false) {
        }
    }

    public void jump() {
        if (jumped == false) {
            speedY = JUMPEDSPEED;
            jumped = true;

        }
    }

    /**
     * This method simply creates a new Projectile, labels it "p", and adds it to the projectiles Arraylist.
     * We create this 50 pixels to the right and 25 pixels above the center of the robot, which is where the gun is.
     */
    public void shoot(){
        Projectile p = new Projectile(centerX + 50, centerY - 25);
        projectiles.add(p);
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isJumped() {
        return jumped;
    }

    public int getSpeedX() {
        return speedX;
    }

    public int getSpeedY() {
        return speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setJumped(boolean jumped) {
        this.jumped = jumped;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked){
        this.ducked = ducked;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * This Arraylist getter method will allow us to reference this newly created Arraylist from the other classes.
     */
    public ArrayList getProjectiles(){
        return projectiles;
    }
}
