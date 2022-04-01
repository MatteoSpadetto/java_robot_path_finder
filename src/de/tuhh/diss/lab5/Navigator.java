package de.tuhh.diss.lab5;

import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Navigator {
    private static final int TILE = 35;
    private static final int SPEED_FAST = 600; // Going straight
    private static final int SPEED_TURN = 300; // Turning
    private static final int SPEED_SLOW = 300; // Approaching walls
    private static final int ULTRA_DIST = 5;   // Space between distance sensor and center of robot
    private static final int COLOR_DIST = 3;   // Distance at which detect color
    private Motor motor = new Motor();
    private UltraSensor us= new UltraSensor(SensorPort.S4);
    private ColorSensor cs = new ColorSensor(SensorPort.S1);
    private void nextTile() {
        float start = us.getDist();
        while ((int)((start - us.getDist())) < TILE) {
            motor.moveStraight(1, SPEED_FAST, Motor.FORWARD);
        }
    }
    private void fixTile() {   // Used to center the robot in the tile accordingly to moving direction (to fix gyro feedback errors in turns)
        if (us.getDist() < TILE/2 - ULTRA_DIST) {    // When robot is too near the wall -> go back
            while (us.getDist() < TILE/2 - ULTRA_DIST) {
                motor.moveStraight(1, SPEED_FAST, Motor.BACKWARD);
            }
        }
        else if (us.getDist() <= TILE && us.getDist() > TILE/2 - ULTRA_DIST) {   // When robot is too far the wall -> go ahead
            while (us.getDist() > (TILE/2) - ULTRA_DIST) {
                motor.moveStraight(1, SPEED_FAST, Motor.FORWARD);
            }
        }
    }
    private boolean findColor(int color) {  // If robot is on end tile check the walls for color
        if (nearColor(color) == false) {
            motor.turnProp(-90, SPEED_TURN);
            fixTile();
            if (nearColor(color) == false) {
                motor.turnProp(-90, SPEED_TURN);
                fixTile();
                if (nearColor(color) == false) {
                    return false;
                }
                else {
                    return true;
                }
            }
            else {
                return true;
            }
        }
        else {
            return true;
        }
    }
    private boolean nearColor(int color) {   // Robot go near the wall to check the color
        float start = us.getDist();
        int colorDetected = -1;
        while (us.getDist() >= COLOR_DIST && colorDetected != color) {
            motor.moveStraight(1, SPEED_SLOW, Motor.FORWARD);
            colorDetected = cs.getColor();
        }
        motor.moveStraight(0, 0, Motor.FORWARD);
        if (colorDetected == color) {
            return true;
        }
        else {   // If color is not detected go back to center and try a new direction in next loop
            while (us.getDist() < start) {
                motor.moveStraight(1, SPEED_SLOW, Motor.BACKWARD);
            }
            motor.moveStraight(0, 0, Motor.BACKWARD);
            return false;
        }
    }
    public int move(int color) {  // Robot movements and rotations inside a tile
        fixTile();
        motor.turnProp(-90, SPEED_TURN);
        fixTile();
        int dist = (int)us.getDist();
        if (dist >= TILE) {
            nextTile();
        }
        else {
            motor.turnProp(90, SPEED_TURN);
            fixTile();
            dist = (int)us.getDist();
            if (dist >= TILE) {
                nextTile();
            }
            else {
                motor.turnProp(90, SPEED_TURN);
                fixTile();
                dist = (int)us.getDist();
                if (dist >= TILE) {
                    nextTile();
                }
                else {
                    if (findColor(color) == true) {  // If color is detected stop
                    	Sound.beep();
                        Delay.msDelay(1000);
                        return 1;
                    }
                    else {
                        motor.turnProp(-90, SPEED_TURN); // If color is not detected go to next tile and restart the loop
                        nextTile(); // The robot moves on previous tile but then it will turn in a different path
                    }
                }
            }
        }
        motor.moveStraight(0, 0, Motor.FORWARD);
        return 0;
    }
}