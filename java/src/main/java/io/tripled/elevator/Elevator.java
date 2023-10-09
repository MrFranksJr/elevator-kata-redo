package io.tripled.elevator;

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
        do {
            currentFloor = currentFloor.goUp();
            feedback.floorPassed(currentFloor);
        } while (destination.floorNumber != currentFloor.floorNumber);

        feedback.doorsOpened(this.currentFloor);
    }
}
