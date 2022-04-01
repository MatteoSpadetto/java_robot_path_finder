package de.tuhh.diss.lab4;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
    private EV3GyroSensor gyroSens;
    private SampleProvider gyroId;
    public GyroSensor(Port port) {
        gyroSens = new EV3GyroSensor(port);
        gyroSens.reset();   // Calibrate to zero
        gyroId = gyroSens.getAngleMode();
    }
    public float getGyro() {
    	float sample[] = new float[gyroId.sampleSize()];
        gyroId.fetchSample(sample, 0);
        return sample[0];
    }
}