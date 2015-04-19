/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fi.jamk;

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
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author H8535
 */
public class test extends BasicGame
{
    PhysicObject ship, missile;
    Image bg;
    boolean missileFired = false;
    DecimalFormat df = new DecimalFormat("#.##");
    ParticleTrail missilePT;
    public static final int WIDTH=1024,HEIGHT=768;
    Camera camera;
    TiledMap map;
    private int mapHeight;// = 1000, 
    private int mapWidth;//= 3000;
    private int tileHeight;// = 32, 
    private int tileWidth;// = 32;
    private int numbOfTilesRow;
    private int numbOfTilesColumn;
    private boolean[][] blocked;
    private static final int NUMBEROFLAYERS = 2;
    
	public test(String gamename)
	{
		super(gamename);
	}

	@Override
	public void init(GameContainer gc) throws SlickException {
            
            gc.setAlwaysRender(true);
            gc.setMaximumLogicUpdateInterval(60);
            gc.setTargetFrameRate(60);
            gc.setVSync(true);
            map = new TiledMap("/map1.tmx",true);
            
            //map objects
            int objectGroupCount = map.getObjectGroupCount();
            for(int gi=0; gi < objectGroupCount; gi++){ //gi = object group index
                int objectCount = map.getObjectCount(gi);
                for(int oi=0; oi < objectCount; oi++){ //oi = object index
                    System.out.println(map.getObjectName(gi, oi));
                    System.out.println(map.getObjectProperty(gi, oi, "name", ""));
                }
            }
            
            mapWidth = map.getWidth() * map.getTileWidth();
            mapHeight = map.getHeight() * map.getTileHeight();
            tileHeight = map.getTileHeight();
            tileWidth = map.getTileWidth();
            
            numbOfTilesRow = 60;
            numbOfTilesColumn = 37;
            blocked = new boolean[numbOfTilesRow][numbOfTilesColumn];
            //initializeBlocked();
            
            camera = new Camera(map, mapWidth, mapHeight);
            
            ship = new PhysicObject(0.25f, 1.0f, 1.0f, 1.0f);
            ship.init("ship.png");
            //shipTrans = new Transform();
            bg = new Image("bg2.png");
        }

	@Override
	public void update(GameContainer gc, int i) throws SlickException {
            //Game logic
            //input
            Input input = gc.getInput();

            if(input.isKeyDown(input.KEY_LEFT)){
                ship.rotate(-1);

            }
            if(input.isKeyDown(input.KEY_RIGHT)){
                ship.rotate(1);
            }

            if(input.isKeyDown(input.KEY_UP)){
                ship.accelerate(1);
                float SPEED = 0.25f;
                float x = ship.getX();
                float y = ship.getY();
               /* if (!isBlocked(ship.getX() + tileWidth -1, ship.getY() - i * SPEED) && !isBlocked(ship.getX() + 1, ship.getY() - i * SPEED)) {
                y -= i * SPEED;
                
            }*/
                ship.setPosition(x, y);
                //ship.checkBounds(ship.getX(),ship.getY());
            }
            
            if(input.isKeyPressed(input.KEY_SPACE)){
                missile = new PhysicObject(0.25f, 0, 0, 0, ship.getX(), ship.getY(), ship.getCRotation(), 0, 0, ship.getFX(), ship.getFY());
                missile.init("missile.png");
               // missilePT = new ParticleTrail("smoke-particle.png");
                //missilePT.init();
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
                //missilePT.update(missile.getX(), missile.getY());
            }
        }

	@Override
	public void render(GameContainer gc, Graphics g) throws SlickException
	{
                g.drawImage(bg, 0 + camera.getX(), 0 + camera.getY());
            try{
                map.render(0 + camera.getX(), 0 + camera.getY());
            }catch(Exception e){
                System.out.println("Failed to render map");
                e.printStackTrace();
            }
                
		ship.draw();
                if (missileFired) {
                    missile.draw();
                    //missilePT.draw();
                }
                camera.translate(g, ship);
                g.drawString("Location X: "+ship.getX(), 120, 10);
                g.drawString("Y: "+ship.getY(), 340, 10);
                
                g.drawString("Angle Delta: "+df.format(ship.getDRotation()), 120, 25);
                g.drawString("Current: "+df.format(ship.getCRotation()), 340, 25);
                
                g.drawString("Velocity X: "+ship.getFX(), 120, 40);
                g.drawString("Y: "+ship.getFY(), 340, 40);
                //debugging camera
                System.out.println("Camera X: "+camera.getX());
                System.out.println("Camera Y: "+camera.getY());
                //end of camera debug
                //g.drawString(ship.getName(),ship.getX(),ship.getY()+40);
                //g.drawString("angleRad: "+ship.getAngleRad(), 420, 10);

	}

	public static void main(String[] args)
	{
		try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new test("Sidescrolling"));
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
         private boolean isBlocked(float x, float y) {
        int xBlock = (int) x / tileWidth;
        int yBlock = (int) y / tileHeight;
        return blocked[xBlock][yBlock];
    }

    /*private void initializeBlocked() {
        try {
        for (int l = 0; l < NUMBEROFLAYERS; l++) {
            String layerValue = map.getLayerProperty(l, "blocked", "false");
            if (layerValue.equals("true")) {
                for (int c = 0; c < numbOfTilesColumn; c++) {
                    for (int r = 0; r < numbOfTilesRow; r++) {
                        if (map.getTileId(c, r, l) != 0) {
                            blocked[c][r] = true;
                        }
                    }
                }
            }
        }
    }catch(IndexOutOfBoundsException e){ e.printStackTrace();} 
    }*/
}
