package fi.jamk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Matias Kivikoura
 */
public class MainMenu extends BasicGameState
{
    private int cursorX = 85,cursorY = 250;
    private final int PLAY1V1 = 250, SETTINGS = 275, EXIT = 300;
    private final int CURSOR_MAX = 300, CURSOR_MIN = 250, CURSOR_STEP = 25, CURSOR_SELECT = 85, CURSOR_EDIT = 300;
    
    private enum MENU_STATES {MAIN_MENU, PLAY1V1, SETTINGS};
    private MENU_STATES menuState = MENU_STATES.MAIN_MENU;
    
    @Override
    public int getID()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.drawString(" _______ __     __           ________ __                       _______ _______ ______ _______ \n"
                +    "|    ___|  |--.|__|.-----.  |  |  |  |__|.-----.-----.-----.  |   |   |       |   __ \\   _   |\n"
                +    "|    ___|  _  ||  ||     |  |  |  |  |  ||     |  _  |__ --|  |       |   -   |   __ <       |\n"
                +    "|_______|_____||__||__|__|  |________|__||__|__|___  |_____|  |__|_|__|_______|______/___|___|\n"
                +    "                                              |_____|      ", 100, 100);
        g.drawString("Pelaa 1vs1", 100, PLAY1V1);
        g.drawString("Asetukset", 100, SETTINGS);
        g.drawString("Poistu", 100, EXIT);
        g.drawString(">", cursorX, cursorY);
        
        switch (menuState)
        {
            case PLAY1V1:
                g.drawString("Pelaaja 1\nvalitse aluksesi\n(WASD-näppäimet)", 500, 250);
                g.drawString("Pelaaja 2\nvalitse aluksesi\n(nuolinäppäimet)", 1000, 250);
                g.drawString("Paina ENTER jatkaaksesi...", 1300, 775);
                break;
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException
    {
        Input input = gc.getInput();
        
        switch(menuState)
        {
            case MAIN_MENU:
                // Choosing menu
                if (input.isKeyPressed(input.KEY_W) || input.isKeyPressed(input.KEY_UP)) cursorUp();
                // Choosing menu
                if (input.isKeyPressed(input.KEY_S) || input.isKeyPressed(input.KEY_DOWN)) cursorDown();
                // Activate chosen menu
                if (input.isKeyPressed(input.KEY_ENTER) || input.isKeyPressed(input.KEY_D) || input.isKeyPressed(input.KEY_RIGHT))
                {
                    if (cursorX == CURSOR_SELECT) {
                        cursorX = CURSOR_EDIT;
                        if (cursorY == PLAY1V1)
                        {
                            menuState = MENU_STATES.PLAY1V1;
                            // Siirry valitsemaan alukset
                        }
                        else if (cursorY == SETTINGS)
                        {
                            menuState = MENU_STATES.SETTINGS;
                            // Siirry käsittelemään asetuksia
                        }
                        else if (cursorY == EXIT)
                        {
                            System.exit(0);
                        }
                    }
                } break;
                
            case PLAY1V1:
                // Deactivate chosen menu and return to main
                if (input.isKeyPressed(input.KEY_BACK))
                {
                    menuState = MENU_STATES.MAIN_MENU;
                    cursorX = CURSOR_SELECT;
                } break;
            case SETTINGS:
                // Deactive chosen menu and return to main
                if (input.isKeyPressed(input.KEY_BACK))
                {
                    menuState = MENU_STATES.MAIN_MENU;
                    cursorX = CURSOR_SELECT;
                }
        }
    }
    
    public void cursorUp()
    {
        if (cursorY == CURSOR_MIN) cursorY = CURSOR_MAX;
        else cursorY -= CURSOR_STEP;
    }
    
    public void cursorDown()
    {
        if (cursorY == CURSOR_MAX) cursorY = CURSOR_MIN;
        else cursorY += CURSOR_STEP;
    }
}
