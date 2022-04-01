package de.tuhh.diss.lab3;

/*Structure:
    - Why is the code for lab 3 task 3 in the file Task1.java in package de.tuhh.diss.lab2? 
    - Instead of trying to choose between all programs in your main with all the logic, make seperate files for every program (with their own main)

Variables: 
    - In main, magic numbers in switch case: You already defined e.g., TEST_RUN, then use it in the switch case statement as well ("case TEST_RUN:")
    - Magic Numbers all over the place: "speed = HIGH_SPEED - (20 * (20 - dist));"
    - Method names should be in camel case get_dist() -> getDist(), get_color() -> getColor()
    - 

Style:
    - Please drop the ASCII Art. It is distracting!

Comments:
    - Don't add comments, if they don't add value. E.g., 
            /// Init ultrasonic distance sensor ///
            UltraSensor us = new UltraSensor(SensorPort.S4);
      is obvious.

1 / 2 Points
*/

import lejos.hardware.Button;
import lejos.hardware.lcd.*;
import lejos.hardware.port.*;
import lejos.robotics.Color;

public class Task3 {
    public static final double MOTOR_STEP = 1;   // Motor are moving 1 cm at step
    public static final int HIGH_SPEED = 250;    // Initial speed
    public static final int SLOW_FACTOR = 20;    // Factor to slow down robot when approaching wall
    public static void main(String[] args) {
        ColorSensor cs = new ColorSensor(SensorPort.S1);
        UltraSensor us = new UltraSensor(SensorPort.S4);
        Motor motor = new Motor(MotorPort.B, MotorPort.C);
        float speed = HIGH_SPEED;  // Initial speed
        /// Cycling to move robot on straight trajectory ///
        while (!Button.LEFT.isDown()) {
        	motor.moveStraight(MOTOR_STEP, (int)speed);
            LCD.clear();
            float dist = us.getDist();   // Read distance [cm]
            us.printDist(dist);
            /// If robot is approaching target (less then 6 cm) slow down ///
            if (dist <= 6.0) {
            	speed = HIGH_SPEED - (SLOW_FACTOR * (SLOW_FACTOR - dist)); 
                float color = cs.getColor();
                if ((int)color != Color.NONE) {
                	cs.printColor(color);
                    motor.moveStraight(MOTOR_STEP, 0);
                    break;
                }
                /// If robot is very close to target and color is not detected yet then stop it ///
                if (dist <= 4.0) {
                LCD.clear();
                LCD.drawString("Too near so stop", 0, 0);
                motor.moveStraight(MOTOR_STEP, 0);
                break;
                }
            }
        }
    }
}