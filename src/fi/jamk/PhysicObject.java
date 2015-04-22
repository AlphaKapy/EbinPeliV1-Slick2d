package fi.jamk;


import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;


/**
 * Any object that needs to be affected by physics. Ships, projectiles, etc.
 * <p>
 * @author Matias Kivikoura
 */
public class PhysicObject
{

    // Constants which are configurable
    private float acceleration,
            turnrate,
            mass,
            aerodynamy,
            lift;

    // Map specific, configurable constants, here for just prototyping
    private float gravity,
            drag;

    // Physic variables
    private float forceX, forceY;

    // Object variables, here for just prototyping
    private float posX, posY, dRotation, cRotation;
    private Image image;
    private int width, height;
    private final float MAX_DROTATION = 5.0f;
    
    //collision
    private Rectangle colBox, colBox2;
    private boolean collision;

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
    public PhysicObject(float acceleration, float turnrate, float aerodynamy,
                        float lift, float posX, float posY, float cRotation,
                        float drag, float gravity, float forceX, float forceY)
    {
        this.acceleration = acceleration;
        this.turnrate = turnrate;
        this.aerodynamy = aerodynamy;
        this.lift = lift;
        this.posX = posX;
        this.posY = posY;
        this.cRotation = cRotation;
        this.forceX = forceX;
        this.forceY = forceY;
    }
    public PhysicObject(){
        
    }

    /**
     *
     * @param acceleration
     * @param turnrate
     * @param aerodynamy
     * @param lift
     */
    public PhysicObject(float acceleration, float turnrate, float aerodynamy, float lift)
    {
        this.acceleration = acceleration;
        this.turnrate = turnrate;
        this.aerodynamy = aerodynamy;
        this.lift = lift;

        this.drag = 0.01f;
        this.gravity = 0.05f;
        this.cRotation = 0;
        this.posX = 200;
        this.posY = 200;
    }

    public void init(String img)
    {
        try
        {
            this.image = new Image(img);
            this.width = image.getWidth();
            this.height = image.getHeight();
            this.image.rotate(cRotation);
        } catch (Exception e)
        {
        }
    }

    public void draw()
    {
        image.draw(this.posX, this.posY);
        image.rotate(dRotation);
    }

    public void update()
    {
        /* Experiemental lift, not working at all
         this.gravity *= (this.lift * this.forceX) / this.gravity; */

        // Gravity
        this.forceY += gravity;

        /* Experiemental drag, not working properly, might not need at all
         * because simpler way works well enough
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
        this.boundryCheck(this);
    }

    /**
     *
     * @param direction 1 or -1. Other values interfere with base acceleration.
     */
    public void accelerate(int direction)
    {
        this.forceX += (float) Math.cos(Math.toRadians(cRotation)) * acceleration * direction;
        this.forceY += (float) Math.sin(Math.toRadians(cRotation)) * acceleration * direction;
    }

    /**
     *
     * @param direction 1 or -1. Other values interfere with base turn rate.
     */
    public void rotate(int direction)
    {
        if (this.dRotation <= MAX_DROTATION && this.dRotation >= -MAX_DROTATION)
        {
            this.dRotation += this.turnrate * direction;
        } else
        {
            this.dRotation = MAX_DROTATION * direction;
        }
    }
    public void boundryCheck(PhysicObject obj){ //Still broken
        if(obj.getX() > Application.WIDTH+32 || obj.getX() < 0-32){
            obj.setForce(-1*obj.getFX(), obj.getFY());
            //obj.setForce(0,0);
            //System.out.println(obj+"Boundry collision X");
        }
        if(obj.getY() > Application.HEIGHT-32 || obj.getY() < 0-32){
           obj.setForce(obj.getFX(), -1*obj.getFY());
           //obj.setForce(0,0);
            //obj.setCRotation(90+ obj.getCRotation());
            //System.out.println(obj+"Boundry collision Y");
        }
        else
            obj.setForce(obj.getFX(), obj.getFY());
        //debug output
        
    }
    
    public boolean collisionCheck(PhysicObject obj, PhysicObject obj2){
        colBox = new Rectangle(obj.getX(), obj.getY(), 32, 32);
        colBox2 = new Rectangle(obj2.getX(),obj2.getY(),32,32);
        
        if(!colBox.intersects(colBox2)){
                    collision = false;
                    return collision;
                    
                }
        else{
                collision = true;
                System.out.println("Collision!");
                obj.setForce(0, 0);
                if(obj.getClass()==ShipV3.class){
                    System.out.println("Ship collision");
                }
                obj2.setForce(0, 0);
                return collision;
            }
        
    }

    public float getX()
    {
        return this.posX;
    }

    public float getY()
    {
        return this.posY;
    }

    public float getFX()
    {
        return this.forceX;
    }

    public float getFY()
    {
        return this.forceY;
    }

    public float getDRotation()
    {
        return this.dRotation;
    }

    public float getCRotation()
    {
        return this.cRotation;
    }

    public void setDRotation(float dRotation)
    {
        this.dRotation = dRotation;
    }

    public void setCRotation(float cRotation)
    {
        this.cRotation = cRotation;
    }

    public void setForce(float forceX, float forceY)
    {
        this.forceX = forceX;
        this.forceY = forceY;
    }

    public void setPosition(float posX, float posY)
    {
        this.posX = posX;
        this.posY = posY;
    }
}
