/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.jamk;

import static fi.jamk.ControllablePhysicObject.ControllerTypes.*;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author H8535
 */
public class test extends BasicGame
{
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
    
    boolean introPlayed;
    
    Music loop;
    Music intro;
    
    //collision
    private Rectangle colBox;
    private Rectangle groundColBox;
    private Rectangle base1ColBox;
    private Rectangle base2ColBox;
    boolean ship1Collides;
    private Rectangle missile1Colbox;
    boolean missile1Collides;
    
	public test(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
       
        bg = new Image("bg2.png");
        map = new TiledMap("/map1.tmx",true);
        blowers = new Faction(1,"blowers");
        crackers = new Faction(2,"crackers");
        tmb1 = new TeamBase(64, HEIGHT-120, 1, "Crackers", "base_red.png");
        blowers.addToFaction(1,100);
        tmb2 = new TeamBase(WIDTH-128, HEIGHT-120, 2, "Blowers", "base_blue.png");
        blowers.addToFaction(2, 200);
         ship = new ShipV3(0.25f, 1.0f, 1.0f, 1.0f, KB1);
        ship.init("ship_red.png");
        crackers.addToFaction(1, 1);
            //shipTrans = new Transform();
        
        
        loop = new Music("loop.ogg");
        intro = new Music("intro.ogg");
        
        intro.play();
        System.out.println(intro.getPosition());
        //collision
        groundColBox = new Rectangle(0, HEIGHT-64, WIDTH, 64);
        colBox = new Rectangle(ship.getX(), ship.getY(), 32, 32);
        base1ColBox = new Rectangle(0,HEIGHT-156,32*3,32*4);
        base2ColBox = new Rectangle(WIDTH-32*3,HEIGHT-156,32*3,32*4);
        missile1Colbox = new Rectangle(0, 0, 40, 20);
        
        }

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
            //Game logic
            //input
            Input input = gc.getInput();
            if(intro.getPosition() > 48.0 && !introPlayed){
                intro.stop();
                System.out.println("Looping loop");
                introPlayed = true;
                loop.loop();
            }
            /*else if (introPlayed && loop.getPosition() > 24.0) {
                loop.play();
            }*/
            else if(!introPlayed)
                System.out.println("Intro position: "+intro.getPosition());

            ship.updateControls(gc);
            
            if(input.isKeyDown(input.KEY_SPACE)){ //if(input.isButton1Pressed(ship.getControllerID())){
                missile = new PhysicObject(0.25f, 0, 0, 0, ship.getX(), ship.getY(), ship.getCRotation(), 0, 0, ship.getFX(), ship.getFY());
                missile.init("missile.png");
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
                //missile collision
                missile1Colbox.setLocation(missile.getX(), missile.getY());
            }
            //collision
            colBox.setLocation(ship.getX(), ship.getY());
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
            }
            /*if(ship1Collides){
                ship.setForce((1/2)*-1*(ship.getFX()), (1/2)*-1*(ship.getY()));
            }*/
            //missile collision
            if(!missile1Colbox.intersects(base1ColBox)){
                missile1Collides = false;
                
            }
            if(!missile1Colbox.intersects(base2ColBox)){
                missile1Collides = false;
            }
            else{
                missile1Collides = true;
                System.out.println("Missile collides with base");
                System.exit(0);
            }
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
                g.drawImage(bg, 0, 0);
                map.render(0, 0);
                tmb1.render(g);
                tmb2.render(g);
                
		ship.draw();
                if (missileFired) {
                    missile.draw();
                    missilePT.draw();
                }
                //g.drawRect(110, 5, 170, 75);
                g.drawString("Location X: "+ship.getX(), 120, 10);
                g.drawString("Y: "+ship.getY(), 340, 10);
                
                g.drawString("Angle Delta: "+df.format(ship.getDRotation()), 120, 25);
                g.drawString("Current: "+df.format(ship.getCRotation()), 340, 25);
                
                g.drawString("Velocity X: "+ship.getFX(), 120, 40);
                g.drawString("Y: "+ship.getFY(), 340, 40);
                
                //g.setColor(Color.green);
                //g.draw(base1ColBox);
                //g.draw(base2ColBox);
                //g.drawString(ship.getName(),ship.getX(),ship.getY()+40);
                //g.drawString("angleRad: "+ship.getAngleRad(), 420, 10);

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new test("collision"));
                        appgc.setIcon("ship_red.png");
                        appgc.setShowFPS(true);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
                        appgc.setFullscreen(true);
                        appgc.setTargetFrameRate(60);
			appgc.start();
                        
		}
		catch (SlickException ex)
		{
			Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
