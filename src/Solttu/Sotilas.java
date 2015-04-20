
package Solttu;
import org.newdawn.slick.Image;
import java.util.List;
import org.newdawn.slick.SlickException;

public class Sotilas {
    public int x,y,w,h;
    public Image kuva=null;
    public Image[] kuvat;
    public int kuvaIndex = 0;
    public int speed_w = 0;
    public int speed_h = 0;
    private static int WIDTH=800, HEIGHT=600;
    
    public int AnimFPS = 20;
    public int UpdateTime = 0;
    
    public Sotilas(int w, int h,int x, int y, String s){
        this.w=w;
        this.h=h;
        this.x=x;
        this.y=y;
        try{
            if(s==null || !(new java.io.File(s).exists()))
         {
             kuva=new Image(w,h);
             kuva.setImageColor(255, 0, 0);
         }
         else kuva = new Image(s);
        }catch(Exception e){}
    }
    public Sotilas(int w, int h){
        this(w,h,0,0,null);
    }
    public Sotilas(int w, int h, int x, int y){
        this(w,h,x,y,null);
    }
    
    public Sotilas(String anim){
        List<String> r= Lukija.Lue(anim);
        String[] rivit = r.get(0).split("\\|");
        kuvat = new Image[rivit.length];
        for(int i=0;i<rivit.length;i++){
            try{
                System.out.println(rivit[i]);
            kuvat[i]=new Image(rivit[i]);
            }catch(Exception e){}
        }
        if(rivit.length>0){
           kuva=kuvat[0];
           w=kuva.getWidth();
         h=kuva.getHeight();
        }else w=h=40;
        x=50;
        y=50;
    }
    
    public void update(){update(0);}
    
    public void update(int i){
        if(kuvat!=null){
         if(1000/AnimFPS<UpdateTime){
         UpdateTime=UpdateTime-1000/AnimFPS;
         if(kuvaIndex>kuvat.length-1)kuvaIndex=0;
         kuva=kuvat[kuvaIndex];
         w=kuva.getWidth();
         h=kuva.getHeight();
         
         kuvaIndex++;//kuvaIndex=kuvaIndex+1;
         }
         else UpdateTime+=i;
        }
        if(x+w>WIDTH || x<0)speed_w*=-1;
        x+=speed_w;
        if(y+h>HEIGHT || y<0)speed_h*=-1;
        y+=speed_h;
    }
    
     public void draw(){
         //Tsup
         if(kuva!=null)kuva.draw(x,y);
     }
     
     public void destroy()throws SlickException{
         kuva.destroy();
         kuva=null;
         if(kuvat!=null){
             for(int i=0;i<kuvat.length;i++)kuvat[i].destroy();
         }
         kuvat=null;
     }
     
     public static void setScreenSize(int ww,int hh){
         WIDTH = ww;
         HEIGHT = hh;
     }
     
}
