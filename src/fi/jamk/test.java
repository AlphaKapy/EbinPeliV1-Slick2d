/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.jamk;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Transform;

/**
 *
 * @author H8535
 */
public class test extends BasicGame
{
    ShipV2 ship;
    Transform shipTrans;
    Image bg;
    public static final int WIDTH=800,HEIGHT=600;
	public test(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
            ship = new ShipV2();
            ship.init();
            //shipTrans = new Transform();
            bg = new Image("bg.jpg");
        }

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
            //Game logic
            //input
            Input input = gc.getInput();
            /*if(input.isKeyDown(input.KEY_UP)){
                ship.setSpeed(3*i);
            }*/
            if(input.isKeyDown(input.KEY_A)){
                ship.turn("CCW", i);

            }
            if(input.isKeyDown(input.KEY_D)){
                ship.turn("CW", i);

                
            }
            /*if(input.isKeyDown(input.KEY_RIGHT)){
                ship.setY((int) (0.2*i));
                ship.checkBounds();

            }*/
            /*if(input.isKeyDown(input.KEY_LEFT)){
                ship.setY((int) (-0.2*i));
                ship.checkBounds();

            }*/
            if(input.isKeyDown(input.KEY_UP)){
                ship.move("FW", i);
                //ship.checkBounds();
            }
            if(input.isKeyDown(input.KEY_DOWN)){
                ship.move("BW", i);
                //ship.checkBounds();
            }
            if(input.isKeyDown(input.KEY_ESCAPE)){
                System.exit(0);
            }
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
                g.drawImage(bg, 0, 0);
		ship.draw();
                g.drawRect(110, 5, 170, 75);
                g.drawString("Location: "+ship.getX()+","+ship.getY(), 120, 10);
                g.drawString("Angle: "+(int)ship.getAngle(), 120, 25);
                //g.drawString("Speed: "+ship.getSpeed(), 120, 40);
                //g.drawString(ship.getName(),ship.getX(),ship.getY()+40);
                g.drawString("angleRad: "+ship.getAngleRad(), 420, 10);

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new test("Ship movement"));
                        appgc.setIcon("ship.png");
                        appgc.setShowFPS(false);
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
