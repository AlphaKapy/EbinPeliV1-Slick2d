package fi.jamk;

import java.text.DecimalFormat;
import org.newdawn.slick.Image;

/**
* Any object that needs to be affected by
* physics. Ships, projectiles, etc.
* <p>
* @author Matias
*/
public class PhysicObject {
    // Constants which are configurable
     private float
            acceleration,
            turnrate,
            mass,
            aerodynamy,
            lift;
    
    // Map specific, configurable constants, here for just prototyping
    private float
            gravity = 0.001f,
            drag = 0.5f;
    
    // Physic variables
    private float forceX, forceY;
    
    // Object variables, here for just prototyping
    private float posX, posY, dRotation, cRotation;
    private Image image;
    private int width, height;
    private final float MAX_DROTATION = 5.0f;

    /**
     *
     * @param acceleration
     * @param turnrate
     * @param aerodynamy
     * @param lift
     * @param posX
     * @param posY
     * @param cRotation
     * @param width
     * @param height
     */
    public PhysicObject(float acceleration, float turnrate, float aerodynamy, float lift, float posX, float posY, float cRotation, int width, int height) {
        this.acceleration = acceleration;
        this.turnrate = turnrate;
        this.aerodynamy = aerodynamy;
        this.lift = lift;
        this.posX = posX;
        this.posY = posY;
        this.cRotation = cRotation;
        this.width = width;
        this.height = height;
    }

    /**
     *
     * @param acceleration
     * @param turnrate
     * @param aerodynamy
     * @param lift
     */
    public PhysicObject(float acceleration, float turnrate, float aerodynamy, float lift) {
        this.acceleration = acceleration;
        this.turnrate = turnrate;
        this.aerodynamy = aerodynamy;
        this.lift = lift;
        
        this.drag = 0.05f;
        this.gravity = 0.25f;
        this.cRotation = 0;
    }
    
    public void init() {
        try {
        this.image = new Image("ship.png");
        this.width = image.getWidth();
        this.height = image.getHeight();
        }catch (Exception e) {}
    }
    
    public void draw() {
        image.draw(this.posX, this.posY);
        image.rotate(dRotation);
    }
    
    public void update() {
        /* Experiemental lift, not working at all
        this.gravity *= (this.lift * this.forceX) / this.gravity; */
        
        // Gravity
        this.forceY += gravity;
        
        /* Experiemental drag, not working properly, might not need at all
        * 'cos simpler way works well enough
        this.forceX -= Math.pow(forceX, 3) * drag;
        this.forceY -= Math.pow(forceY, 3) * drag; */
        
        // Drag
        this.forceX -= drag * forceX;
        this.forceY -= drag * forceY;
        
        // Position
        this.posX += forceX;
        this.posY += forceY;
        this.dRotation *= 0.9f;
        this.cRotation += this.dRotation;
    }
    
    /**
     *
     * @param direction 1 or -1. Other values interfere with base acceleration.
     */
    public void accelerate(int direction) {
        this.forceX += (float) Math.cos(Math.toRadians(cRotation)) * acceleration * direction;
        this.forceY += (float) Math.sin(Math.toRadians(cRotation)) * acceleration * direction;
    }
    
    /**
     *
     * @param direction  1 or -1. Other values interfere with base turn rate.
     */
    public void rotate(int direction) {
        if (this.dRotation <= MAX_DROTATION && this.dRotation >= -MAX_DROTATION) {
            this.dRotation += this.turnrate * direction;
        } else {
            this.dRotation = MAX_DROTATION * direction;
        }
    }
    
    public float getX() { return this.posX; }
    public float getY() { return this.posY; }
    public float getFX() { return this.forceX; }
    public float getFY() { return this.forceY; }
    public float getDRotation() { return this.dRotation; }
    public float getCRotation() { return this.cRotation; }
}
