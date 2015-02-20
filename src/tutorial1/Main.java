package tutorial1;

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
 * @author Ghost
 */
public class Main extends BasicGame{

    private TiledMap map;
    
    Image bg;
    Image ship;
    static int gameWidth=800, gameHeight = 600;
    //ship's stuff
    float x =200;
    float y = 200;
    float speed = 0.2f;
    
    public Main(String title) {
        super(title);
    }

    public static void main(String args[]) throws SlickException{
        
        AppGameContainer app = new AppGameContainer(new Main("First Slick2D"));
        app.setDisplayMode(gameWidth, gameHeight, false);
        app.start();
    }
    
    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        //draws all the graphics
        map.render(0,0);
        //g.drawImage(bg, 0, 0);
        g.drawImage(ship,x,y);
    }
    
    @Override
    public void init(GameContainer gc) throws SlickException {
        //load all the assets
        map = new TiledMap("map.tmx"); //this needs to be created, continue tutorial: https://www.youtube.com/watch?v=0BivL6Carh4
        bg = new Image("bg.jpg");
        ship = new Image("ship1.png");
    }

    @Override
    public void update(GameContainer gc, int delta) throws SlickException {
        //Game logic
        Input input = gc.getInput(); //asks slick2d keys pressed
        
        if(input.isKeyDown(input.KEY_RIGHT)){
            x +=speed*delta;
        }
        if(input.isKeyDown(input.KEY_LEFT)){
            x -=speed * delta;
        }
        if(input.isKeyDown(input.KEY_UP)){
            y -=speed * delta;
        }
        if(input.isKeyDown(input.KEY_DOWN)){
            y +=speed * delta;
        }
    }
}
