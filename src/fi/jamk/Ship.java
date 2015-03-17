/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.jamk;

import static java.lang.Math.sin;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author H8535
 */
public class Ship {
    private Image image;
    private int x=100,y=100;
    private int width,height;
    private double angleSin, angleCos;
    private double speed=0f;
    private double angle;
    private double angleRad;
    private final double MAX_SPEED = 10f;
    private Shape rekt;
    private String name="Ebin alus";
    
    public void init(){
        try{
        this.image = new Image("ship.png");
        width =image.getWidth();
        height =image.getHeight();
        //rekt = new Rectangle(x,y,width,height);
        }catch(Exception e) {e.printStackTrace();}
    }
    public void draw(){
        image.draw(x, y);
    }
    
    public void move()
    {
        this.x *= this.speed*getAngleSin();
        this.y *= this.speed*getAngleCos();
    }
    
    //Speed and angle getters and setters
    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed += speed;
        if(this.speed > MAX_SPEED) this.speed = MAX_SPEED;
        if(this.speed < -MAX_SPEED) this.speed = -MAX_SPEED;
        //this.y -=this.speed;
        
    }
    public void incSpeed(double speed){
        this.speed += speed;
        if(this.speed > MAX_SPEED) this.speed = MAX_SPEED;
        if(this.speed < -MAX_SPEED) this.speed = -MAX_SPEED;
        move();
    }

    public double getAngle() {
        return angle;
    }
    public double getAngleRad(){
        angleRad = Math.toRadians(angle);
        return angleRad;
    }
    
    public double getAngleSin()
    {
        angleSin = Math.sin(angleRad);
        return angleSin;
    }
    
    public double getAngleCos()
    {
        angleCos = Math.cos(angleRad);
        return angleCos;
    }

    public void setAngle(double angle) {
        this.angle += angle;
        image.rotate((float)angle);
        rotate(angle);
        angleRad = Math.toRadians(angle);
        
    }
    public void rotate(double angle){
        //handles the ship's rotation
        //no idea how
    }
    

    //end of speed and angle getters and setters

        public void checkBounds(){
        if(x > test.WIDTH-width)
            x = test.WIDTH-width-5;
        if(x < 0)
            x = 0;
        if(y > test.HEIGHT-height)
            y=test.HEIGHT-height-5;
        if(y < 0)
            y=0;
    }
        
    //Location setters and getters
    public int getX() {
        return x;
    }

    public void setX(int x) {
        
        this.x = (int) (sin(angle)*x);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y += y;
    }
    
    //End of setters and getters
    
    public String getName(){
        return name;
    }
}
