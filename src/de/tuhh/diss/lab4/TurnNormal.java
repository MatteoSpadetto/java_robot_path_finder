package de.tuhh.diss.lab4;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.utility.Delay;

public class TurnNormal implements Turner {
	private static final double DIAM_W = 5.4;
    private static final double W_BIG = 15.1; // Outer distance between wheels
    private static final double W_SMALL = 9.5; // Inner distance between wheels
    private static final double RADIUS_CURV = (((W_BIG / 2) - (W_SMALL / 2)) / 2) + (W_SMALL / 2);
    private static final double CORR_FACTOR = 0.35;
    private int speed = 0;	
    @Override
    public void setSpeed(int degreesPerSecond) {
        speed = degreesPerSecond;	
    }
    @Override
    public void turn(int degrees) {
        //motor.moveTurn(speed, degrees);
        EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.B);
        EV3LargeRegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.C);
        double distance = Math.abs(degrees) * 2 * Math.PI * RADIUS_CURV / 360; // Distance the wheel has to run 
        double deg = (distance * 360) / ((DIAM_W / 2) * Math.PI); // Distance converted in degrees
        deg =  deg + deg * CORR_FACTOR; // Correcting degrees error to get the desired turn
        motorRight.setSpeed(speed);
        motorLeft.setSpeed(speed);
        if (degrees < 0) {
            motorRight.backward();
            motorLeft.forward();
        }
        else {
            motorRight.forward();
            motorLeft.backward();
        }
        double time = deg / speed;  // Get time to wait to run the correct distance
        int timeout = (int)(time * 1000);
        Delay.msDelay(timeout);
        motorRight.setSpeed(0); // Use setSpeed(0) instead of stop() to not have slip
        motorLeft.setSpeed(0);
        motorRight.close();
        motorLeft.close();
    }
}
