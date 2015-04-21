/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Ghost
 */
public class MusicMg {
    
    private Music loop;
    private Music intro;
    private Music track1;
    
    private boolean track1Playing;
    
    private boolean introPlayed=false;
    private boolean introPlayback = false;

    public void MusicMg() {
        try {
            this.intro = new Music("Intro.ogg");
            this.loop = new Music("Loop.ogg");
            this.track1 = new Music("music.ogg");
        } catch (SlickException ex) {
            System.out.println("Loading music failed");
            Logger.getLogger(MusicMg.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void introLoop(){
        if(introPlayback =false){
            intro.play();
            introPlayback = true;
        }
        if(intro.getPosition() > 48.0 && !introPlayed){
                intro.stop();
                System.out.println("Looping loop");
                introPlayed = true;
                loop.loop();
            }
            /*else if (introPlayed && loop.getPosition() > 24.0) {
                loop.play();
            }*/
            else if(!introPlayed)
                System.out.println("Intro position: "+intro.getPosition());
    }
    public void menuMusic(){
        System.out.println("Starting menu music playback");
        track1.loop();
        track1Playing = true;
    }
    public void stopMenuMusic(){
        if(track1Playing)
        track1.stop();
        else
            System.out.println("Track isn't being played");
    }
}
