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
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

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
    public static final int WIDTH=1024,HEIGHT=768;
    
	public test(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
        ship = new ControllablePhysicObject(0.25f, 1.0f, 1.0f, 1.0f, XB360WIRED);
        ship.init("ship.png");
            //shipTrans = new Transform();
            //bg = new Image("bg.jpg");
        }

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
            //Game logic
            //input
            Input input = gc.getInput();

            ship.updateControls(gc);
            
            if(input.isButton1Pressed(ship.getControllerID())){
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
            }
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
                //g.drawImage(bg, 0, 0);
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
                //g.drawString(ship.getName(),ship.getX(),ship.getY()+40);
                //g.drawString("angleRad: "+ship.getAngleRad(), 420, 10);

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new test("Ship movement"));
                        appgc.setIcon("ship.png");
                        appgc.setShowFPS(true);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
                        appgc.setTargetFrameRate(60);
			appgc.start();
                        
		}
		catch (SlickException ex)
		{
			Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
}
