package de.tuhh.diss.lab5;

import lejos.hardware.Sound;
import lejos.utility.Delay;

public class Task5 {
    public static void main(String[] args) {
        Menu menu = new Menu();
        Navigator nav = new Navigator();
        int color = menu.selection();     // Select target color from menu
        Sound.beep();                     // Start beep before first movement
        Delay.msDelay(1000);
        while (true) {                    // Cycling TILEs
            if (nav.move(color) == 1) {   // Stop if target color is found
                break;
            }
        }
    }
}