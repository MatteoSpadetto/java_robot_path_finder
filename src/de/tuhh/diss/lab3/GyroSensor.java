package de.tuhh.diss.lab3;

import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;

public class GyroSensor {
    private EV3GyroSensor gyroSens;
    private SampleProvider gyroId;
    public GyroSensor(Port port) {
        gyroSens = new EV3GyroSensor(port);
        gyroId = gyroSens.getAngleMode();
    }
    public float getGyro() {
        float sample[] = new float[gyroId.sampleSize()];
        gyroId.fetchSample(sample, 0);
        return sample[0];
    }
    public void printAngle(float angle) {
        String angle_str = Float.toString(angle);
        LCD.clear();
        LCD.drawString("Ang: " + angle_str, 0, 2);
    }
}