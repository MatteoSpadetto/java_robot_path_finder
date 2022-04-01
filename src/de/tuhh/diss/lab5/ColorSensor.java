package de.tuhh.diss.lab5;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;

public class ColorSensor {
    private EV3ColorSensor colSens;
    private SensorMode colorId;
    public ColorSensor(Port port) {
        colSens = new EV3ColorSensor(port);
        colorId = colSens.getColorIDMode();
    }
    public int getColor() {
        float sample[] = new float[colorId.sampleSize()]; 
        colorId.fetchSample(sample, 0);
        return (int)sample[0];
    }
}
