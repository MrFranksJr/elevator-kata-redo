package io.tripled.elevator;

import java.util.ArrayList;
import java.util.List;

public class Elevator {
    private final Feedback feedback;
    private final CallParser callParser;
    private Floor currentFloor = Floor.GROUND;

    public Elevator(Feedback feedback, CallParser callParser) {
        this.feedback = feedback;
        this.callParser = callParser;
    }

    public Floor getCurrentFloor() {
        return currentFloor;
    }

    public void call(List<Floor> callList) {
        feedback.clearLogs();
        if (callList.size() == 1) {
            moveTo(callList.get(0));
        }
        if (callList.size() == 2) {
            moveTo(callList.get(0));
            moveTo(callList.get(1));
        }
        if (callList.size() > 2) {
            //move to call origin
            moveTo(callList.get(0));
            //parse the rest of the calls
            callParser.parse(callList);
            for (ElevatorCall elevatorCall : callParser.collectedElevatorCalls) {
                moveTo(elevatorCall.callDestination());
            }
        }
    }

    public void moveTo(Floor destination) {
        while (currentFloor.notReached(destination)) {
            currentFloor = currentFloor.move(destination);
            feedback.floorPassed(currentFloor);

            if (currentFloor.hasReached(destination)) {
                feedback.doorsOpened(this.currentFloor);
            }
        }
    }
}
