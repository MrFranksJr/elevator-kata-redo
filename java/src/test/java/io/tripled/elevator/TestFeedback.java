package io.tripled.elevator;

public class TestFeedback implements Feedback {

    private Floor floor;

    public String doorsOpenedAtFloor() {
        return "<DING> - door open at floor " + floor.floorNumber;
    }

    @Override
    public void doorsOpened(Floor floor) {
        this.floor = floor;
    }
}
