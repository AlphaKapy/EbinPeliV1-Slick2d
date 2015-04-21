package fi.jamk;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
* Base class for all game objects
* <p>
* @author Matias Kivikoura
*/
public class GameObject
{
    private float posX, posY;
    private float dRot, cRot;
    private Image image;
    private Faction faction;
    private Rectangle collBox;
    private CollisionType collType;
    private Physic physic;
    
    public void render(Graphics g)
    {
        image.draw(posX, posY);
        image.rotate(dRot);
    }
    
    public void update(GameContainer gc)
    {
        
    }
    
    public void setPosition(float posX, float posY)
    {
        this.posX = posX;
        this.posY = posY;
    }
    
    // GameObject with physics
    public class Physic extends GameObject
    {
        private float acceleration, turnrate;
        private float forceX, forceY;
        
        private final float MAX_DROTATION = 5;
        
        @Override
        public void update(GameContainer gc)
        {
            // Gravity
            this.forceY += Game.GRAVITY;
            
            // Drag
            this.forceX -= Game.DRAG * this.forceX;
            this.forceY -= Game.DRAG * this.forceY;
            
            // Position
            super.posX += this.forceX;
            super.posY += this.forceY;
            super.dRot *= 0.9f;
            super.cRot += super.dRot;
        }
        
        public void accelerate(int dir)
        {
            this.forceX += (float) Math.cos(Math.toRadians(cRot)) * acceleration * dir;
            this.forceY += (float) Math.sin(Math.toRadians(cRot)) * acceleration * dir;
        }
        
        public void rotate(int dir)
        {
            if (super.dRot <= MAX_DROTATION && super.dRot >= -MAX_DROTATION)
            {
                super.dRot += this.turnrate * dir;
            }
            else
            {
                super.dRot = MAX_DROTATION * dir;
            }
        }
                
        
        private Controllable controllable;
        
        // GameObject.Physic with controls
        public class Controllable extends Physic
        {
            private ControllerType ctrlrType;
        }
    }
}
