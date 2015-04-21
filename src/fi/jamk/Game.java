package fi.jamk;

import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;

/**
* @author Matias Kivikoura
*/
public class Game extends BasicGame
{
    
    public final static float GRAVITY = 0.05f, DRAG = 0.01f;
    public GameObjectManager objectManager;
    private ControllerType ctrlrType;
    
    private Player RedPlayer, BluPlayer;
    private Soldier RedSoldier, BluSoldier;
    private Base RedBase, BluBase;
    
    private boolean inIntro = true;
    private Music intro, loop;
    
    // Background properties
    private GameObject bg;
    private GameObject ground;

    public Game(String title)
    {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        
        // Create new gameobjects
        BluPlayer = new Player(ctrlrType.KB1);
        RedPlayer = new Player(ctrlrType.XB360WIRED);
        BluBase = new Base();
        RedBase = new Base();
        bg = new GameObject();
        ground = new GameObject();
        
        // Add gameobjects to manager
        objectManager.add(bg);
        objectManager.add(ground);
        objectManager.add(BluPlayer);
        objectManager.add(RedPlayer);
        objectManager.add(BluBase);
        objectManager.add(RedBase);
        
        intro = new Music("intro.ogg");
        intro.play();
        loop = new Music("loop.ogg");
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException
    {
        Input input = gc.getInput();
        
        // Exit
        if (input.isKeyDown(input.KEY_ESCAPE)) System.exit(0);
        
        // Music
        if (inIntro && intro.getPosition() > 48)
        {
            intro.stop();
            loop.play();
            inIntro = false;
        }
        else if (!inIntro && loop.getPosition() > 24)
        {
            loop.stop();
            loop.play();
        }
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        // Background
        g.drawImage(bg, 0, 0);
    }
    
    public static void main(String[] args)
    {
        try
        {
            AppGameContainer appgc;
            appgc = new AppGameContainer(new Game("Ebin Wings MOBA"));
            appgc.setShowFPS(true);
            appgc.setDisplayMode(1920, 1080, true);
            appgc.setTargetFrameRate(60);
            appgc.start();
        }
        catch (SlickException ex)
        {
            System.out.println(ex);
        }
    }
}
