package de.tuhh.diss.lab5;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.utility.Delay;

public class Motor {
    private static final double DIAM_W = 5.4;
    private static final double W_BIG = 15.1; // Outer distance between wheels
    private static final double W_SMALL = 9.5; // Inner distance between wheels
    private static final int PROP_COEFF = 10; // Coefficient for proportional turning controller
    public static final boolean FORWARD = true;
    public static final boolean BACKWARD = false;
    private EV3LargeRegulatedMotor motorLeft;
    private EV3LargeRegulatedMotor motorRight;
    private GyroSensor gs;
    public Motor() {
        motorLeft = new EV3LargeRegulatedMotor(MotorPort.B);
        motorRight = new EV3LargeRegulatedMotor(MotorPort.C);
        gs =  new GyroSensor(SensorPort.S3);
    }
    public void moveStraight(double distance, int speed, boolean direction) {
        double deg = (distance * 360) / ((DIAM_W / 2) * Math.PI);  // Calculate degrees per distance
        motorRight.setSpeed(speed);
        motorLeft.setSpeed(speed);
        if (direction == FORWARD) {
        	motorRight.backward();
            motorLeft.backward();
        }
        else {
        	motorRight.forward();
            motorLeft.forward();
        }
        double time = deg / speed;    // Get time to wait to run the correct distance
        int timeout = (int)(time * 1000);
        Delay.msDelay(timeout);
    }
    public void turnProp(int degrees, int speed) {
        gs.resetGyro();
        float gyro;
        int degSgn;
        if (degrees > 0) { 
            gyro = -gs.getGyro();
            degSgn = degrees;
        }
        else {
            gyro = gs.getGyro();
            degSgn = -degrees;
        }
        while (gyro < degSgn) {
        	double radius_curv = (((W_BIG / 2) - (W_SMALL / 2)) / 2) + (W_SMALL / 2);
            double distance = 2 * Math.PI * radius_curv / 360; // Distance the wheel has to run (the robot motor step is 1cm long on wheel)
            double deg = (distance * 360) / ((DIAM_W / 2) * Math.PI); // Distance converted in degrees
            motorRight.setSpeed((int)((gyro - degSgn) * PROP_COEFF)); // Using proportional controller
            motorLeft.setSpeed((int)((gyro - degSgn) * PROP_COEFF));
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
            if (degrees > 0) {
                gyro = -gs.getGyro();
            }
            else {
                gyro = gs.getGyro();
            }
        }
        motorRight.setSpeed(0); // Use setSpeed(0) instead of stop() to not have slip
        motorLeft.setSpeed(0);
    }
}