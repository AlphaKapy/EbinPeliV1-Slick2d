/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

/**
 *
 * @author Ghost
 */
public class GameObject extends PhysicObject {
    private int objectID;
    private String objectType;
    private int factionID;
    private int health;
    private boolean alive;

    /*public GameObject(int objectID, String objectType, int factionID, int health, boolean alive) {
        this.objectID = objectID;
        this.objectType = objectType;
        this.factionID = factionID;
        this.health = health;
        this.alive = alive;
    }*/

    public int getObjectID() {
        return objectID;
    }

    public void setObjectID(int objectID) {
        this.objectID = objectID;
    }

    public String getObjectType() {
        return objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public int getFactionID() {
        return factionID;
    }

    public void setFactionID(int factionID) {
        this.factionID = factionID;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean isAlive() {
        if(health <= 0){
            return false;
        }
        else 
            return true;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
    
    
    
}
