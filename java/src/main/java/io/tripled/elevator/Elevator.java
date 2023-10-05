package io.tripled.elevator;

import static io.tripled.elevator.Floor.*;

public class Elevator {
    private final Feedback feedback;
    private Floor currentFloor = Floor.GROUND;

    public Elevator(Feedback feedback) {
        this.feedback = feedback;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void call(Floor destination) {
        feedback.floorPassed(FLOOR_1);
        feedback.floorPassed(FLOOR_2);
        feedback.floorPassed(FLOOR_3);
        currentFloor = destination;
        feedback.doorsOpened(this.currentFloor);
    }
}
