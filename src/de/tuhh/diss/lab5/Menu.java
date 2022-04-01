package de.tuhh.diss.lab5;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.Color;
import lejos.utility.Delay;

public class Menu {
    public int selection() {
        String strVal[] = new String[3];
        int colors[] = new int[3];
        colors[0] = Color.RED;
        colors[1] = Color.GREEN;
        colors[2] = Color.BLUE;
        int index = 0;
        LCD.clear();
        LCD.drawString("selection", 0, 0);
        int flagDown = 0;
        int flagUp = 0;
        while (!Button.ENTER.isDown()) {
            if (Button.DOWN.isDown() && flagDown == 0 && flagUp == 0) {
                index++;
                flagDown = 1;
            }
            if (Button.DOWN.isUp()) {
                flagDown = 0;
            }
            if (Button.UP.isDown() && flagDown == 0 && flagUp == 0) {
                index--;
                flagUp = 1;
            }
            if (Button.UP.isUp()) {
                flagUp = 0;
            }
            if (index == -1) {
                index = 2;
            }
            if (index == 3) {
                index = 0;
            }
            strVal[0] = "Red";
            strVal[1] = "Green";
            strVal[2] = "Blue";
            strVal[index] = "> " + strVal[index];
            LCD.clear();
            LCD.drawString("Select color:", 0, 0);
            LCD.drawString(strVal[0], 0, 1);
            LCD.drawString(strVal[1], 0, 2);
            LCD.drawString(strVal[2], 0, 3);
            Delay.msDelay(100);
        }
        LCD.clear();
        LCD.drawString("Target " + strVal[index], 0, 0);
        return colors[index];
    }
}
