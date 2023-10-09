package io.tripled.elevator;

import java.util.ArrayList;
import java.util.List;

public class TestFeedback implements Feedback {

    public List<Floor> floorsPassed = new ArrayList<>();
    public List<Floor> openedDoors = new ArrayList<>();

    @Override
    public void doorsOpened(Floor floor) {
        openedDoors.add(floor);
    }

    @Override
    public void floorPassed(Floor floor) {
        floorsPassed.add(floor);
    }

    public List<Floor> allFloorsPassed() {
        return floorsPassed;
    }
    public List<Floor> allDoorsOpened() { return openedDoors; }
}
