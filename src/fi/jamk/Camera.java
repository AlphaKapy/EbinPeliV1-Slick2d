/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.jamk;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author H8535
 */
public class Camera {
    private int x,y;
    private int mapWidth, mapHeight;
    private Rectangle viewPort;
    
    public Camera(TiledMap map, int mapWidth, int mapHeight){
        x=0;
        y=0;
        viewPort = new Rectangle(0,0, test.WIDTH,test.HEIGHT);
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }
    
    public void translate(Graphics g, PhysicObject ship) {

        if (ship.getX() - test.WIDTH / 30 < 0) { // 2 + 16 < 0
            x = 0;
        } else if (ship.getX() + test.WIDTH * 30 > mapWidth) { // 2 + 16 > 0
            x = -mapWidth + test.WIDTH;
        } else {
            x = (int) -ship.getX() + test.WIDTH / 30; // 2-16
        }

        if (ship.getY() - test.HEIGHT / 200 < 0) { // 2 + 16 < 0
            y = 0;
        } else if (ship.getY() + test.HEIGHT * 30 > mapHeight) { // 2 + 16 > 0
            y = -mapHeight + test.HEIGHT;
        } else {
            y = (int) -ship.getY() + test.HEIGHT / 200; // 2 - 16
        }
        g.translate(x, y);
        viewPort.setX(-x);
        viewPort.setY(-y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
}
