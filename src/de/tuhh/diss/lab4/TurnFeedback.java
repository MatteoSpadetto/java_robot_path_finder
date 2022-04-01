package de.tuhh.diss.lab4;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class TurnFeedback implements Turner{
    private static final double DIAM_W = 5.4;
    private static final double W_BIG = 15.1; // Outer distance between wheels
    private static final double W_SMALL = 9.5; // Inner distance between wheels
    private static final double RADIUS_CURV = (((W_BIG / 2) - (W_SMALL / 2)) / 2) + (W_SMALL / 2);
    private static final int STEP = 1;
    private static final int EPSILON = 1;
    private int speed = 0;
    EV3LargeRegulatedMotor motorLeft; 
    EV3LargeRegulatedMotor motorRight;
    @Override
    public void setSpeed(int degreesPerSecond) {
       speed = degreesPerSecond;	
    }
    @Override
    public void turn(int degrees) {
        //motor.moveTurnFeedback(speed, degrees);		
        EV3LargeRegulatedMotor motorLeft = new EV3LargeRegulatedMotor(MotorPort.B);
        EV3LargeRegulatedMotor motorRight = new EV3LargeRegulatedMotor(MotorPort.C);
        GyroSensor gs =  new GyroSensor(SensorPort.S3);
        float error = Math.abs(degrees - gs.getGyro());
        while (error > EPSILON) {
            double distance = Math.abs(STEP) * 2 * Math.PI * RADIUS_CURV / 360; // Distance the wheel has to run 
            double deg = (distance * 360) / ((DIAM_W / 2) * Math.PI); // Distance converted in degrees
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
            error = Math.abs(degrees - gs.getGyro());
        }
        motorRight.setSpeed(0); // Use setSpeed(0) instead of stop() to not have slip
        motorLeft.setSpeed(0);
        motorRight.close();
        motorLeft.close();
    }
}
