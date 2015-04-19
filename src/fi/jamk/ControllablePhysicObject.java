package fi.jamk;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.lwjgl.input.Controllers;

/**
* @author Matias Kivikoura
*/
public class ControllablePhysicObject extends PhysicObject
{
    public static enum ControllerTypes {KB1, KB2, XB360WIRED};
    private ControllerTypes ctrlType;
    private int ctrlrID = Controllers.getControllerCount();
    

    public ControllablePhysicObject(float acceleration, float turnrate, float aerodynamy, float lift, ControllerTypes ctrlType)
    {
        super(acceleration, turnrate, aerodynamy, lift);
        this.ctrlType = ctrlType;
        this.ctrlrID = Input.ANY_CONTROLLER;
        
    }

    public ControllablePhysicObject(float acceleration, float turnrate, float aerodynamy, float lift, float posX, float posY, float cRotation, float drag, float gravity, float forceX, float forceY, ControllerTypes ctrlType)
    {
        super(acceleration, turnrate, aerodynamy, lift, posX, posY, cRotation, drag, gravity, forceX, forceY);
        this.ctrlType = ctrlType;
        this.ctrlrID = Input.ANY_CONTROLLER;
    }
    
    public void updateControls(GameContainer gc)
    {
        Input input = gc.getInput();
        
        switch (ctrlType)
        {
            case KB1:
                if (input.isKeyDown(input.KEY_W)) super.accelerate(1);
                if (input.isKeyDown(input.KEY_D)) super.rotate(1);
                if (input.isKeyDown(input.KEY_A)) super.rotate(-1);
                break;
            case KB2:
                if (input.isKeyDown(input.KEY_UP)) super.accelerate(1);
                if (input.isKeyDown(input.KEY_RIGHT)) super.rotate(1);
                if (input.isKeyDown(input.KEY_LEFT)) super.rotate(-1);
                break;
            case XB360WIRED:
                if (input.isButtonPressed(4, 4)) super.accelerate(1);
                if (input.isControllerRight(4)) super.rotate(1);
                if (input.isControllerLeft(4)) super.rotate(-1);
                break;
        }
    }

}
