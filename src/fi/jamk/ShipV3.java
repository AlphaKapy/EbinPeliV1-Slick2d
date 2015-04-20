/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.jamk;

/**
 *
 * @author Ghost
 */
public class ShipV3 extends ControllablePhysicObject {

    public ShipV3(float acceleration, float turnrate, float aerodynamy, float lift, ControllerTypes ctrlType) {
        super(acceleration, turnrate, aerodynamy, lift, ctrlType);
    }
    
}
