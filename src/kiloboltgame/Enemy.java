package kiloboltgame;

/**
 * Created by ronald on 09/12/16.
 */
public class Enemy {

    private int maxHealth, currentHealth, power, speedX, centerX, centerY;

    /**
     * Whenever the background scrolls, the enemy should move in the same direction. So, we will create a REFERENCE
     * to the bg1 OBJECT in StartingClass.
     */
    private Background bg = StartingClass.getBg1();

    //Behavioural related Methods

    /**
     * THe update method, is a method that is constantly running
     */
    public void update(){
        centerX += speedX;
        speedX = bg.getSpeedX()*5;
    }
    public void die(){

    }

    public void attack(){

    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public int getSpeedX() {
        return speedX;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public int getCenterX() {
        return centerX;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public int getCenterY() {
        return centerY + 350; // making heliboy set a little lower.
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public Background getBg() {
        return bg;
    }

    public void setBg(Background bg) {
        this.bg = bg;
    }
}
