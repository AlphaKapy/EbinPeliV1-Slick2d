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
    private CollisionType collType;
    private Physic physic;
    
    public GameObject()
    {
        
    }
    
    /**
     * Static GameObject
     * <p>
     * @param posX
     * @param posY
     * @param image
     * @param faction
     * @param collType 
     */
    public GameObject(float posX, float posY, Image image, Faction faction, CollisionType collType)
    {
        this.posX = posX;
        this.posY = posY;
        this.image = image;
        this.faction = faction;
        this.collType = collType;
    }

    /**
     * GameObject with Physics
     * <p>
     * @param posX
     * @param posY
     * @param dRot
     * @param cRot
     * @param image
     * @param faction
     * @param collType
     * @param physic 
     */
    public GameObject(float posX, float posY, float dRot, float cRot, Image image, Faction faction, CollisionType collType, Physic physic)
    {
        this.posX = posX;
        this.posY = posY;
        this.dRot = dRot;
        this.cRot = cRot;
        this.image = image;
        this.faction = faction;
        this.collType = collType;
        this.physic = physic;
    }
    
    
    
    public void render(GameContainer gc, Graphics g)
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
    
    public void move(float forceX, float forceY)
    {
        this.posX += forceX;
        this.posY += forceY;
    }
    
    public void setDRotation(float dRot) { this.dRot = dRot; }
    public void setCRotation(float cRot) { this.cRot = cRot; }
    
    public float getDRotation() { return dRot; }
    public float getCRotation() { return cRot; }
}
