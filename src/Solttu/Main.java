package Solttu;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class Main extends BasicGame{
    public static final int WIDTH=1024,HEIGHT=768;;//WIDTH=800,HEIGHT=600;
    public Sotilas Sotilas;
    public Sotilas[] Sotilaat;
    public int suunta_w=10;
    public int suunta_h=10;
    public Main(String name){
        
    super(name);
    }

public void update(GameContainer gc, int i) throws SlickException{   
    //Sotilas.update();
    Input input = gc.getInput();
    if(input.isKeyDown(input.KEY_ESCAPE)){
                System.exit(0);
    }
    
    for(int j=0;j<Sotilaat.length;j++)Sotilaat[j].update(i);
}
public void render(GameContainer gc, Graphics g) throws SlickException
	{
            for(int j=0;j<Sotilaat.length;j++)Sotilaat[j].draw();
            g.drawString("Ebin", 120, 10);
        }
public void init(GameContainer gc){
Sotilas.setScreenSize(WIDTH, HEIGHT);

Random rnd= new Random();
Sotilaat= new Sotilas[4];
for(int i=0;i<Sotilaat.length;i++)
{
    Sotilaat[i]=new Sotilas("Untitled.anim");
    Sotilaat[i].x = rnd.nextInt(WIDTH-Sotilaat[i].w);
    Sotilaat[i].y = rnd.nextInt(HEIGHT-Sotilaat[i].h);
    //Sotilaat[i].speed_h=rnd.nextInt(51); //arpajaiset
    //Sotilaat[i].speed_w=rnd.nextInt(51);
    //Sotilaat[i].AnimFPS=rnd.nextInt(41)+20;
    Sotilaat[i].y=HEIGHT-Sotilaat[i].h;
    //Sotilaat[i].x=i*(Sotilaat[i].w+40);
    Sotilaat[i].x=0;
    Sotilaat[i].speed_w=0; 
}

new Thread(){
    public void run(){
    for(int i=0;i<Sotilaat.length;i++){
       Sotilaat[i].speed_w=1;
       try{
        Thread.sleep(500);
       }catch(Exception e){}
    }
    }
}.start();

//Sotilas = new Nahkasotilas(40,40,1000,50,"Untitled.png");
}

    public static void main(String[] args) {
       try
		{
			AppGameContainer appgc;
			appgc = new AppGameContainer(new Main("Ship movement"));
                        appgc.setIcon("Untitled.png");
                        appgc.setShowFPS(true);
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
                        appgc.setFullscreen(true);
                        appgc.setTargetFrameRate(60);
			appgc.start();
                        
		}
		catch (SlickException ex)
		{
			Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
		}
    }
    
}
