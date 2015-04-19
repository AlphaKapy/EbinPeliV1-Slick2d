/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ghost
 */
public class TeamBase{
    private int posX, posY, teamID;
    private String teamName;
    private String imgPath;
    private Image img;
    private int health;
    private boolean destroyed =false;
    private boolean gameOver = false;

    public TeamBase(int posX, int posY, int teamID, String teamName, String imgPath){
        this.posX = posX;
        this.posY = posY;
        this.teamID = teamID;
        this.teamName = teamName;
        this.imgPath = imgPath;
        try {
            img = new Image(imgPath);
        } catch (SlickException ex) {
            System.out.println("Loading base image failed");
            Logger.getLogger(TeamBase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void render(Graphics g){
        g.drawImage(img, posX, posX);
    }
    
    public void update(){
        if(health == 0)
            destroyed = true;
    }
    public boolean gameOver(){
        if(destroyed)
            return true;
        else 
            return false;
    }
    
}
