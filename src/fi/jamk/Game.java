package fi.jamk;

import org.newdawn.slick.*;

/**
* @author Matias Kivikoura
*/
public class Game extends BasicGame
{
    
    public final static float GRAVITY = 0.05f, DRAG = 0.01f;
    public GameObjectManager objectManager;
    private ControllerType ctrlrType;
    
    private boolean inIntro = true;
    private Music intro, loop;
    
    // Game objects
    private Image bgImage;
    private GameObject shipGO;
    private PhysicObject ship;
    
    private ControllableObject bluPlayer;
    private Image bluPlayerImage;
    
    private ControllableObject redPlayer;
    private Image redPlayerImage;
    
    private GameObject bluBase;
    private Image bluBaseImage;
    
    private GameObject redBase;
    private Image redBaseImage;

    public Game(String title)
    {
        super(title);
    }

    @Override
    public void init(GameContainer gc) throws SlickException
    {
        objectManager = new GameObjectManager();
        
        // Initialize images
        bgImage = new Image("bg.png");
        bluBaseImage = new Image("base_blue.png");
        redBaseImage = new Image("base_red.png");
        bluPlayerImage = new Image("ship_blue.png");
        redPlayerImage = new Image("ship_red.png");
        
        // Create new gameobjects
        bluPlayer = new ControllableObject(ControllerType.KB1, ship);
        bluBase = new GameObject(0,930-bluBaseImage.getHeight() , bluBaseImage, Faction.BLUFOR, CollisionType.DESTROY);
        redBase = new GameObject(1920-redBaseImage.getWidth(), 930-redBaseImage.getHeight(), redBaseImage, Faction.REDFOR, CollisionType.DESTROY);
        
        // Add gameobjects to manager
//        objectManager.add(BluPlayer);
//        objectManager.add(RedPlayer);
        objectManager.add(bluBase);
        objectManager.add(redBase);
        
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
        
        // Collision
        if 
        
        objectManager.update(gc);
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException
    {
        bgImage.draw(0,0);
        objectManager.render(gc, g);
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
