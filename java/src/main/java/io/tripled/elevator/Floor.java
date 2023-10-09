package io.tripled.elevator;

import java.util.stream.Stream;

public enum Floor {
    BASEMENT(-1),
    GROUND(0),
    FLOOR_1(1),
    FLOOR_2(2),
    FLOOR_3(3);

    public int floorNumber;

    Floor(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    private static Floor byFloorNumber(int number) {
        return Stream.of(values())
                .filter(floor -> floor.floorNumber == number)
                .findFirst()
                .orElse(null);
    }

    public Floor move(Floor destination) {
        if (goingUp(destination)) {
            return goUp();
        }
        else if (goingDown(destination)) {
            return goDown();
        }
        else return null;
    }

    public Floor goUp() {
        return Floor.byFloorNumber(this.floorNumber + 1);
    }
    public Floor goDown() {
        return Floor.byFloorNumber(this.floorNumber - 1);
    }
    public boolean notReached(Floor destination) { return destination != this; }
    private boolean goingUp(Floor destination) { return destination.floorNumber > this.floorNumber; }
    private boolean goingDown(Floor destination) { return destination.floorNumber < this.floorNumber; }
    public boolean hasReached(Floor destination) { return destination == this; }
}