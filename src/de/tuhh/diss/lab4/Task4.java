package de.tuhh.diss.lab4;

public class Task4 {
    public static void main(String[] args) {
        Turner robotNormal = new TurnNormal();
        robotNormal.setSpeed(400);
        robotNormal.turn(180);
        Turner robotFeedback = new TurnFeedback();
        robotFeedback.setSpeed(400);
        robotFeedback.turn(-180);
    }
}