package kiloboltgame;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.URL;
import java.util.ArrayList;
import kiloboltgame.framework.Animation;

/**
 * Created by ronald on 18/09/16.
 */
public class StartingClass extends Applet implements Runnable, KeyListener {

    private Robot robot;
    private Heliboy hb, hb2;
    private Image image, currentSprite, character, character2, character3, characterDown,
            characterJumped, background, heliboy, heliboy2, heliboy3, heliboy4, heliboy5;
    public static Image tiledirt, tileocean;
    private Graphics second;
    private URL base;
    private static Background bg1, bg2;
    private Animation anim, hanim;


    private ArrayList<Tile> tilearray = new ArrayList<>();

    @Override
    public void init() {
        setSize(800, 480);
        setBackground(Color.BLACK);
        setFocusable(true);
        addKeyListener(this);

        Frame frame = (Frame) this.getParent().getParent();
        frame.setTitle("Q-Bot Alpha");

        try {
            base = StartingClass.class.getResource("/data/background.png");
        } catch (Exception e) {


        }
        //Image setups
        character = getImage(base, "character.png");
        character2 = getImage(base, "character2.png");
        character3 = getImage(base, "character3.png");

        characterDown = getImage(base, "down.png");
        characterJumped = getImage(base, "jumped.png");

        heliboy = getImage(base, "heliboy.png");
        heliboy2 = getImage(base, "heliboy2.png");
        heliboy3 = getImage(base, "heliboy3.png");
        heliboy4 = getImage(base, "heliboy4.png");
        heliboy5 = getImage(base, "heliboy5.png");

        tiledirt = getImage(base, "tiledirt.png");
        tileocean = getImage(base, "tileocean.png");

        background = getImage(base, "background.png");

        anim = new Animation();
        anim.addFrame(character, 1250);
        anim.addFrame(character2, 50);
        anim.addFrame(character3, 50);
        anim.addFrame(character2, 50);

        hanim = new Animation();
        hanim.addFrame(heliboy, 100);
        hanim.addFrame(heliboy2, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy5, 100);
        hanim.addFrame(heliboy4, 100);
        hanim.addFrame(heliboy3, 100);
        hanim.addFrame(heliboy2, 100);

        currentSprite = anim.getImage();

    }

    @Override
    public void start() {
        bg1 = new Background(0, 0);
        bg2 = new Background(2160, 0);


        //Initialize the tiles
        /**
         * We utilize a double for loop, representing the x index of the tiles as i , and y index as j.
         * The numbers i < 200 creates 2400 possible locations for tiles. Of those, we fill 400 (i = 200 , j = 2)
         */

        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 12; j++) {

                if (j == 11) {
                    Tile t = new Tile(i, j, 2);
                    tilearray.add(t);
                }
                if (j == 10) {
                    Tile t = new Tile(i, j, 1);
                    tilearray.add(t);
                }
            }
        }

        //this will create two heliboy objects
        hb = new Heliboy(0, 360);
        hb2 = new Heliboy(0, 660);

        robot = new Robot();

        Thread tread = new Thread(this);
        tread.start();
    }

    @Override
    public void stop() {
    }

    @Override
    public void destroy() {
    }

    @Override
    public void run() {
        while (true) {
            robot.update();
            if (robot.isJumped()) {
                currentSprite = characterJumped;
            } else if (robot.isJumped() == false && robot.isDucked() == false) {
                currentSprite = anim.getImage();

            }
            /**
             * We create a new ArrayList called projectiles and give it the value of the projectiles ArrayList in Robot.
             * We then create a for loop, which runs as long as the index i is lesser than the size of the Projectiles ArrayList.
             * (which is the number of Projectile objects stored in it).
             * Them we check which Projectile object is in the i-th (4th, 5th, ect.) place in the ArrayList (the Projectile
             * object with an index of i).
             *
             * To illustrate indexes, check this out:
             * List = [1,2,3,5,7];
             * The indexes of each of these are 0,1,2,3 and 4. This means that the for loop will go through each object in the
             * arrayList of Projectiles and set it equal to p.
             *
             * After that it checks if this p is on the screen (is visible). If true, it updates it. ELse, it removes this p
             * by removing th i-th index from the projectiles ArrayList.
             */

            ArrayList projectiles = robot.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                Projectile p = (Projectile) projectiles.get(i);

                if (p.isVisible() == true) {
                    p.update();
                } else {
                    projectiles.remove(i);
                }
            }

            updateTiles();
            hb.update(); // heliboy
            hb2.update(); // heliboy
            bg1.update();
            bg2.update();
            animate();
            repaint();
            try {
                Thread.sleep(17);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    public void animate(){
    anim.update(10);
    hanim.update(50);
    }

    @Override
    public void update(Graphics g) {
        if (image == null) {
            image = createImage(this.getWidth(), this.getHeight());
            second = image.getGraphics();
        }
        second.setColor(getBackground());
        second.fillRect(0, 0, getWidth(), getHeight());
        second.setColor(getForeground());
        paint(second);
        g.drawImage(image, 0, 0, this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(background, bg1.getBgX(), bg1.getBgY(), this);
        g.drawImage(background, bg2.getBgX(), bg2.getBgY(), this);
        paintTiles(g);

        /**
         * We create an ArrayList called projectiles (the one from update() method is only usabel by the update() method.
         * We use a for loop and paint each Projectile object called "p", which represents th i-th Projectile in the ArrayList
         * at the current iteration of the for loop, and we draw a yellow rectangel with width 10 and height 5 at x and y
         * coordinate of the "p" Projectile.
         */
        ArrayList projectiles = robot.getProjectiles();
        for (int i = 0; i < projectiles.size(); i++) {
            Projectile p = (Projectile) projectiles.get(i);

            g.setColor(Color.YELLOW);
            g.fillRect(p.getX(), p.getY(), 10, 5);
        }

        g.drawImage(currentSprite, robot.getCenterX() - 61, robot.getCenterY() - 63, this);

        // We have to make the newly created Heliboy objects appear on the screen!
        g.drawImage(heliboy, hb.getCenterX() - 48, hb.getCenterY() - 48, this);
        g.drawImage(heliboy, hb2.getCenterX() - 48, hb2.getCenterY() - 48, this);
    }

    public void updateTiles(){
        for (int i = 0; i < tilearray.size(); i++){
            Tile t =(Tile) tilearray.get(i);
            t.update();
        }
    }
    private void paintTiles(Graphics g) {
        for (int i = 0; i < tilearray.size(); i++) {
            Tile t = (Tile) tilearray.get(i);
            g.drawImage(t.getTileImage(), t.getTileX(), t.getTileY(), this);
        }
    }


        @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Move up");
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = characterDown;
                if (robot.isJumped() == false) {
                    robot.setDucked(true);
                    robot.setSpeedX(0);
                }
                break;
            case KeyEvent.VK_LEFT:
                robot.moveLeft();
                robot.setMovingLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                robot.moveRight();
                robot.setMovingRight(true);
                break;
            case KeyEvent.VK_SPACE:
                robot.jump();
                break;


            /**
             * We want to use the CTRL button to shoot our bullets.
             */
            case KeyEvent.VK_CONTROL:
                if (robot.isDucked() == false && robot.isJumped() == false) {
                    robot.shoot();
                }
                break;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                System.out.println("Stop moving up");
                break;
            case KeyEvent.VK_DOWN:
                currentSprite = character;
                robot.setDucked(false);
                break;
            case KeyEvent.VK_LEFT:
                robot.stopLeft();
                break;
            case KeyEvent.VK_RIGHT:
                robot.stopRight();
                break;
            case KeyEvent.VK_SPACE:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    public static Background getBg1() {
        return bg1;

    }

    public static Background getBg2() {
        return bg2;
    }
}
