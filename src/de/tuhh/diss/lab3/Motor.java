package de.tuhh.diss.lab3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.Port;
import lejos.utility.Delay;

public class Motor {
	private static final double DIAM_W = 5.4;
    private EV3LargeRegulatedMotor motorLeft;
    private EV3LargeRegulatedMotor motorRight;
    public Motor(Port portLeft, Port portRight) {
        motorLeft = new EV3LargeRegulatedMotor(portLeft); 
        motorRight = new EV3LargeRegulatedMotor(portRight);
    }
    public void moveStraight(double distance, int speed) {
        double deg = (distance * 360) / ((DIAM_W / 2) * Math.PI);  // Calculate degrees per distance
        /// Speed over 900 is out of range motor ///
        if (speed <= 900 & speed >= 0) {
            motorRight.setSpeed(speed);
            motorLeft.setSpeed(speed);
            motorRight.backward();              // Start motor right with defined speed
            motorLeft.backward();               // Start motor left with defined speed
            double time = deg / speed;          // Get time to wait to run the correct distance
            int timeout = (int)(time * 1000);
            Delay.msDelay(timeout);
        }
        /// If speed is out of range print it ///
        else {
            LCD.clear();
            LCD.drawString("Wrong set speed", 0, 0);
        }
    }
}