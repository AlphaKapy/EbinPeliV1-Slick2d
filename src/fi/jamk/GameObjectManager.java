package fi.jamk;

import java.util.ArrayList;
import org.newdawn.slick.*;

/**
* @author Matias Kivikoura
*/
public class GameObjectManager
{
    ArrayList<GameObject> objectList;
    
    public void add(GameObject gameObject)
    {
        objectList.add(gameObject);
    }
    
    public void remove(GameObject gameObject)
    {
        objectList.remove(gameObject);
    }
    
    public void update(GameContainer gc)
    {
        for (GameObject gb : objectList)
        {
            gb.update(gc);
        }
    }
    
    public void render(Graphics g)
    {
        for(GameObject gb : objectList)
        {
            gb.render(g);
        }
    }
}
