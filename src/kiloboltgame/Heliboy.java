package kiloboltgame;

/**
 * Created by ronald on 09/12/16.
 */
public class Heliboy extends Enemy {

    /**
     * Whenever you create a new Heliboy object you will pass in two params:
     * @param centerX
     * @param centerY
     * they will define the central coordinates of your enemy.
     */

    public Heliboy(int centerX, int centerY){
        setCenterX(centerX);
        setCenterX(centerY);
    }
}

/**
 * You might notice that we never defined update() in the Heliboy class; however the Heliboy class INHERITED the
 * enemy class, calling update() here will automatically call the update() method in the Enemy class THIS IS
 * INHERITANCE in action!!
 **/