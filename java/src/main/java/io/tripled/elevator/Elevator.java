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

    public void call(Floor callOrigin) {
        call(callOrigin, callOrigin);
    }

    public void call(Floor callOrigin, Floor callDestination) {
        moveTo(callOrigin);
        moveTo(callDestination);
    }

    private void moveTo(Floor destination) {
        while (currentFloor.notReached(destination)) {
            currentFloor = currentFloor.move(destination);
            feedback.floorPassed(currentFloor);

            if (currentFloor == destination) {
                feedback.doorsOpened(this.currentFloor);
            }
        }
    }
}
