package de.tuhh.diss.lab5;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
    private EV3GyroSensor gyroSens;
    private SampleProvider gyroId;
    float offset = 0;
    public GyroSensor(Port port) {
        gyroSens = new EV3GyroSensor(port);
        gyroSens.reset();   // Calibrate to zero
        gyroId = gyroSens.getAngleMode();
    }
    public float getGyro() {
    	float sample[] = new float[gyroId.sampleSize()];
        gyroId.fetchSample(sample, 0);
        return sample[0] - offset; // Use offset to get relative angles
    }
    public void resetGyro() {
        float sample[] = new float[gyroId.sampleSize()];
        gyroId.fetchSample(sample, 0);
        offset = sample[0];
    }
}