package de.tuhh.diss.lab3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

public class ColorSensor {
    private EV3ColorSensor colSens;
    private SensorMode colorId;
    public ColorSensor(Port port) {
        colSens = new EV3ColorSensor(port);
        colorId = colSens.getColorIDMode();
    }
    public float getColor() {
        float sample[] = new float[colorId.sampleSize()]; 
        colorId.fetchSample(sample, 0);
        return sample[0];
    }
    /// Translate color numbers to color names by switch-case ///
    public String parseColor(int id) {
        String output = "NULL";
        switch (id) {
            case Color.NONE:
                output = "NONE";
                break;
            case Color.GREEN:
                output = "GREEN";
                break;
            case Color.BLUE:
                output = "BLUE";
                break;
            case Color.YELLOW:
                output = "YELLOW";
                break;
            case Color.MAGENTA:
                output = "MAGENTA";
                break;
            case Color.RED:
                output = "RED";
                break;
            case Color.ORANGE:
                output = "ORANGE";
                break;
            case Color.WHITE:
                output = "WHITE";
                break;
            case Color.BLACK:
                output = "BLACK";
                break;
            case Color.PINK:
                output = "PINK";
                break;
            case Color.GRAY:
                output = "GRAY";
                break;
            case Color.LIGHT_GRAY:
                output = "LIGTH_GRAY";
                break;
            case Color.DARK_GRAY:
                output = "DARK_GRAY";
                break;
            case Color.CYAN:
                output = "CYAN";
                break;
            case Color.BROWN:
                output = "BROWN";
                break;
            default:
                output = "NULL";
                break;
        }
        return output;
    }
    public void printColor(float color) {
        String color_str = parseColor((int)color);
        LCD.clear();
        LCD.drawString("Color: " + color_str, 0, 0);
    }
}
