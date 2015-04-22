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
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Ghost
 */
public class world1 extends BasicGameState {
    private ControllablePhysicObject ship, ship2;
    private PhysicObject missile, missile2;
    private Image bg;
    private boolean missileFired = false;
    private boolean missile2Fired = false;
    private DecimalFormat df = new DecimalFormat("#.##");
    private ParticleTrail missilePT;
    public static final int WIDTH=1920,HEIGHT=1080;
    private TiledMap map;
    private TeamBase tmb1,tmb2;
    private Faction blowers, crackers;
    
    private MusicMg musMg;
    //collision
    private Rectangle colBox;
    private Rectangle groundColBox;
    private Rectangle base1ColBox;
    private Rectangle base2ColBox;
    boolean ship1Collides;
    private Rectangle missile1Colbox;
    boolean missile1Collides;
    
    
    
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
        
        tmb1 = new TeamBase(64, HEIGHT-120, 1, "Crackers", "res/base_red.png",100);
        blowers.addToFaction(1,100);
        tmb2 = new TeamBase(WIDTH-128, HEIGHT-120, 2, "Blowers", "res/base_blue.png",100);
        blowers.addToFaction(2, 200);
        
         ship = new ShipV3(0.25f, 1.0f, 1.0f, 1.0f,ControllablePhysicObject.ControllerTypes.KB1);
         ship2 = new ShipV3(0.25f, 1.0f, 1.0f, 1.0f,ControllablePhysicObject.ControllerTypes.XB360WIRED);
        ship.init("res/ship_red.png");
        ship.setPosition(64, 32);
        ship2.init("res/ship_blue.png");
        ship2.setPosition(1920-64, 32);
        crackers.addToFaction(1, 1);
            //shipTrans = new Transform();
        
        //collision
        groundColBox = new Rectangle(0, HEIGHT-64, WIDTH, 64);
        colBox = new Rectangle(ship.getX(), ship.getY(), 32, 32);
        base1ColBox = new Rectangle(0,HEIGHT-156,32*3,32*4);
        base2ColBox = new Rectangle(WIDTH-32*3,HEIGHT-156,32*3,32*4);
        missile1Colbox = new Rectangle(0, 0, 40, 20);
        
        //music
        musMg = new MusicMg();
        musMg.MusicMg();
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
         grphcs.drawImage(bg, 0, 0);
                map.render(0, 0);
                tmb1.render(grphcs);
                tmb2.render(grphcs);
                
		ship.draw();
                ship2.draw();
                if (missileFired) {
                    missile.draw();
                    //missilePT.draw();
                }
                if(missile2Fired){
                    missile2.draw();
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
            ship2.updateControls(gc);
            
            if(input.isButton1Pressed(ship.getControllerID())){
                missile = new PhysicObject(0.25f, 0, 0, 0, ship.getX(), ship.getY(), ship.getCRotation(), 0, 0, ship.getFX(), ship.getFY());
                missile.init("res/missile.png");
                //missilePT = new ParticleTrail("smoke-particle.png");
                //missilePT.init();
                missileFired = true;
            }
            if(input.isButton1Pressed(ship2.getControllerID())){
                missile2 = new PhysicObject(0.25f, 0, 0, 0, ship2.getX(), ship2.getY(), ship2.getCRotation(), 0, 0, ship2.getFX(), ship2.getFY());
                missile2.init("res/missile.png");
                missile2Fired = true;
            }
            
            if (input.isKeyDown(input.KEY_R)) {
                ship.setForce(0, 0);
                ship.setPosition(64, 64);
                
                ship2.setForce(0, 0);
                ship2.setPosition(96, 64);
            }
            
            if(input.isKeyDown(input.KEY_ESCAPE)){
                System.exit(0);
            }
            
            //ship.boundryCheck(ship); //these are relocated to physic object
            //ship2.boundryCheck(ship2);
            ship.update();
            ship2.update();
            ship.collisionCheck(ship, ship2);
            //tmb1.missileCollision(missile2);
            //tmb2.missileCollision(missile);
            
            if (missileFired)
            {
                missile.accelerate(1);
                missile.update();
                missilePT.update(missile.getX(), missile.getY());
            }
            if(missile2Fired){
                missile2.accelerate(1);
                missile2.update();
                //particle trail
            }
            //collision
            /*colBox.setLocation(ship.getX(), ship.getY());
            //check for collisions
            if(!colBox.intersects(groundColBox)){
                    ship1Collides = false;
                    
                }
            if(!colBox.intersects(base1ColBox)){
                ship1Collides = false;
            }
            if(!colBox.intersects(base2ColBox)){
                ship1Collides = false;
            }
            else{
                ship1Collides = true;
                System.out.println("Collision!");
                ship.setForce((1/2)*-1*(ship.getFX()), (1/2)*-1*(ship.getY()));
            }*/
            /*if(ship1Collides){
                ship.setForce((1/2)*-1*(ship.getFX()), (1/2)*-1*(ship.getY()));
            }*/
            //missile collision
           /*if(!missile1Colbox.intersects(base1ColBox)){
                missile1Collides = false;
                
            }
            if(!missile1Colbox.intersects(base2ColBox)){
                missile1Collides = false;
            }
            else{
                missile1Collides = true;
                System.out.println("Missile collides with base");
                System.exit(0);
            }*/
            musMg.introLoop();

        }

    
    
}
