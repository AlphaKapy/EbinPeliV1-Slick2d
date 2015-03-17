package fi.jamk;

import org.newdawn.slick.Image;

/**
 *
 * @author Matias
 */
public class ShipV2 {
    private Image image;
    private int posX, posY, speed = 1;
    private double angle = 0, rotation = 0, angleSin, angleCos; // Sin for x, cos for y. Because math. That's why.
    private float turnrate = 0.5f;
    private final float MAX_SPEED = 10.0f;
    private int width, height;
    
    public void init(){
        try{
        this.image = new Image("ship.png");
        width =image.getWidth();
        height =image.getHeight();
        //rekt = new Rectangle(x,y,width,height);
        }catch(Exception e) {e.printStackTrace();}
    }
    
    public void draw() {
        image.draw(this.posX, this.posY);
    }
    
    public void turn(String turnDir, int i) {
        if ("CW".equals(turnDir)) {
            this.rotation = turnrate * i;
            this.angle += turnrate * i;
        }
        else if ("CCW".equals(turnDir)) {
            this.rotation = -turnrate * i;
            this.angle -= turnrate * i;
        }
        image.rotate((float)this.rotation);
    }
    
    public void move(String direction, int i) {
        if ("FW".equals(direction)) {
            this.posX += this.speed * getAngleCos() * i;
            this.posY += this.speed * getAngleSin() * i;
        }
        if ("BW".equals(direction)) {
            this.posX -= this.speed * getAngleCos() * i;
            this.posY -= this.speed * getAngleSin() * i;
        }
    }
    
    //Setters
    
    //Getters
    public double getAngleSin() {
        this.angleSin = Math.sin(Math.toRadians(this.angle));
        return this.angleSin;
    }
    
    public double getAngleCos() {
        this.angleCos = Math.cos(Math.toRadians(this.angle));
        return this.angleCos;
    }
    
    public int getX() {
        return this.posX;
    }
    
    public int getY() {
        return this.posY;
    }
    
    public double getAngle() {
        return this.angle;
    }
    
    public double getAngleRad() {
        return Math.toRadians(this.angle);
    }
    
    public double getRotation() {
        return this.rotation;
    }
}
