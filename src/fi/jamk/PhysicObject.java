package fi.jamk;

import org.newdawn.slick.GameContainer;

/**
* @author Matias Kivikoura
*/
public class PhysicObject extends GameObject
{
    private float acceleration, turnrate;
    private float forceX, forceY;
    private GameObject gameObject;
    
    public PhysicObject()
    {
        
    }

    public PhysicObject(float acceleration, float turnrate, float forceX, float forceY, GameObject gameObject)
    {
        this.acceleration = acceleration;
        this.turnrate = turnrate;
        this.gameObject = gameObject;
    }
    
    @Override
    public void update(GameContainer gc)
    {
        // Gravity
        this.forceY += Game.GRAVITY;

        // Drag
        this.forceX -= Game.DRAG * this.forceX;
        this.forceY -= Game.DRAG * this.forceY;

        // Position
        gameObject.move(forceX, forceY);
        
        //Rotation
        gameObject.setDRotation(gameObject.getDRotation() * 0.9f);
        gameObject.setCRotation(gameObject.getDRotation());
    }

    public float getAcceleration()
    {
        return acceleration;
    }

    public float getTurnrate()
    {
        return turnrate;
    }

    public void setAcceleration(float acceleration)
    {
        this.acceleration = acceleration;
    }

    public void setTurnrate(float turnrate)
    {
        this.turnrate = turnrate;
    }

    public void setForceX(float forceX)
    {
        this.forceX = forceX;
    }

    public void setForceY(float forceY)
    {
        this.forceY = forceY;
    }
    
    
}