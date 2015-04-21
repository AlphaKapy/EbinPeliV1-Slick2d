package fi.jamk;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;


/**
 *
 * @author Matias Kivikoura
 */
public class Application extends StateBasedGame
{
    public Stack<BasicGameState> gameStateStack = new Stack();
    
    MainMenu MAINMENU = new MainMenu();
    
    private static int WIDTH = 1920, HEIGHT = 1080;
    
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
            appgc.setShowFPS(false);
            appgc.setTargetFrameRate(60);
            appgc.setDisplayMode(WIDTH, HEIGHT, false);
            appgc.setFullscreen(true);
            appgc.start();
            
        }
        catch (SlickException e)
                {
                    Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, e);
                }
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

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        addState(new MainMenu());
        addState(new world1());
        enterState(0);
    }
}
