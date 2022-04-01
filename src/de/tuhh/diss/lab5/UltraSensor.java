package de.tuhh.diss.lab5;

import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class UltraSensor {
    private EV3UltrasonicSensor distSens;
    private SampleProvider distId;
    public UltraSensor(Port port) {
        distSens = new EV3UltrasonicSensor(port);
        distId = distSens.getDistanceMode();
    }
    public float getDist() {
        float sample[] = new float[distId.sampleSize()];
        distId.fetchSample(sample, 0);
        float output= sample[0] * 100;  // Change scale [m] to [cm]
        return output;
    }
}
