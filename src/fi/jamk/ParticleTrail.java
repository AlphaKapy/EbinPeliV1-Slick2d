package fi.jamk;

import java.util.Stack;
import org.newdawn.slick.Image;

/**
 *
 * @author Matias Kivikoura
 */
public class ParticleTrail
{
    private Image image;
    private String imgPath;
    Stack<Particle> particleTrail;
    private int width, height;
    
    public ParticleTrail(String imgPath)
    {
        this.imgPath = imgPath;
        particleTrail = new Stack<>();
    }
    
    public void init()
    {
        try {
            this.image = new Image(imgPath);
        } catch (Exception e) {}
    }
    
    public void draw()
    {
        for (Particle pt : particleTrail)
        {
            pt.draw();
        }
    }
    
    public void update(float x, float y)
    {
        particleTrail.add(new Particle(x, y));
        
        if (particleTrail.size() > 10)
        {
            // remove last
        }
    }

    class Particle
    {
        private float posX, posY;

        public Particle(float posX, float posY)
        {
            this.posX = posX;
            this.posY = posY;
        }
        
        public void draw()
        {
            image.draw(posX, posY);
        }
    }
}
