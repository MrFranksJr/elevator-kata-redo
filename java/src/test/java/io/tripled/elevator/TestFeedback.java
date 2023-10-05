package io.tripled.elevator;

import java.util.ArrayList;
import java.util.List;

public class TestFeedback implements Feedback {

    private Floor floor;
    public List<Floor> floorsPassed = new ArrayList<>();

    public Floor doorsOpenedAtFloor() { return floor; }

    @Override
    public void doorsOpened(Floor floor) {
        this.floor = floor;
    }

    @Override
    public void floorPassed(Floor floor) {
        floorsPassed.add(floor);
    }

    public List<Floor> allFloorsPassed() {
        return floorsPassed;
    }
}
