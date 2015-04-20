/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Ghost
 */
public class Faction {
    private int ID;
    private final String name;
    private LinkedList inFaction; 
    private int objectID;
    //GameObject obj;
    

    public Faction(int ID, String name) {
        inFaction = new LinkedList(); //no use yet
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
    
    public void addToFaction(int factionID, int objectID){
        this.objectID = objectID;
        this.ID = factionID;
        inFaction.add(objectID);
    }
    
    
    
}
