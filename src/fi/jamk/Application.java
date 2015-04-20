package fi.jamk;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;


/**
 *
 * @author Matias Kivikoura
 */
public class Application extends BasicGame
{
    public Stack<BasicGameState> gameStateStack = new Stack();
    
    MainMenu MAINMENU = new MainMenu();
    
    private static int WIDTH = 1600, HEIGHT = 800;
    
    public Application(String appName)
    {
        super(appName);
        
    }
    
    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Application("Ebin Game"));
            appgc.setShowFPS(true);
            appgc.setTargetFrameRate(60);
            appgc.setDisplayMode(WIDTH, HEIGHT, false);
            appgc.start();
        }
        catch (SlickException e)
                {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, e);
                }
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        gameStateStack.push(MAINMENU);
        MAINMENU.init(gc, null);
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException
    {
        gameStateStack.peek().update(gc, null, i);
    }

    @Override
    public void render(GameContainer gc, Graphics grphcs) throws SlickException
    {
        gameStateStack.peek().render(gc, null, grphcs);
    }
    
    /**
    * Pushes new game state on top of stack
    */
    public void push(BasicGameState bsg)
    {
        gameStateStack.push(bsg);
    }
    
    /**
     * Removes top most game state from stack
     */
    public void pop()
    {
        gameStateStack.pop();
    }
}
