/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.text.DecimalFormat;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Ghost
 */
public class world1 extends BasicGameState {
    ControllablePhysicObject ship;
    PhysicObject missile;
    Image bg;
    boolean missileFired = false;
    DecimalFormat df = new DecimalFormat("#.##");
    ParticleTrail missilePT;
    public static final int WIDTH=1920,HEIGHT=1080;
    TiledMap map;
    TeamBase tmb1,tmb2;
    Faction blowers, crackers;

    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        System.out.println("Initiliazing world1");
        bg = new Image("res/bg2.png");
        map = new TiledMap("/res/map1.tmx",true);
        blowers = new Faction(1,"blowers");
        crackers = new Faction(2,"crackers");
        tmb1 = new TeamBase(64, HEIGHT-120, 1, "Crackers", "base_red.png");
        blowers.addToFaction(1,100);
        tmb2 = new TeamBase(WIDTH-128, HEIGHT-120, 2, "Blowers", "base_blue.png");
        blowers.addToFaction(2, 200);
         ship = new ShipV3(0.25f, 1.0f, 1.0f, 1.0f,ControllablePhysicObject.ControllerTypes.KB1);
        ship.init("res/ship_red.png");
        crackers.addToFaction(1, 1);
            //shipTrans = new Transform();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
         grphcs.drawImage(bg, 0, 0);
                map.render(0, 0);
                tmb1.render(grphcs);
                tmb2.render(grphcs);
                
		ship.draw();
                if (missileFired) {
                    missile.draw();
                    missilePT.draw();
                }
                //g.drawRect(110, 5, 170, 75);
                grphcs.drawString("Location X: "+ship.getX(), 120, 10);
                grphcs.drawString("Y: "+ship.getY(), 340, 10);
                
                grphcs.drawString("Angle Delta: "+df.format(ship.getDRotation()), 120, 25);
                grphcs.drawString("Current: "+df.format(ship.getCRotation()), 340, 25);
                
                grphcs.drawString("Velocity X: "+ship.getFX(), 120, 40);
                grphcs.drawString("Y: "+ship.getFY(), 340, 40);
                //g.drawString(ship.getName(),ship.getX(),ship.getY()+40);
                //g.drawString("angleRad: "+ship.getAngleRad(), 420, 10);

    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
         Input input = gc.getInput();

            ship.updateControls(gc);
            
            if(input.isButton1Pressed(ship.getControllerID())){
                missile = new PhysicObject(0.25f, 0, 0, 0, ship.getX(), ship.getY(), ship.getCRotation(), 0, 0, ship.getFX(), ship.getFY());
                missile.init("res/missile.png");
                missilePT = new ParticleTrail("smoke-particle.png");
                missilePT.init();
                missileFired = true;
            }
            
            if (input.isKeyDown(input.KEY_R)) {
                ship.setForce(0, 0);
                ship.setPosition(0, 0);
            }
            
            if(input.isKeyDown(input.KEY_ESCAPE)){
                System.exit(0);
            }
            
            ship.update();
            if (missileFired)
            {
                missile.accelerate(1);
                missile.update();
                missilePT.update(missile.getX(), missile.getY());
            }
        }

    
    
}
