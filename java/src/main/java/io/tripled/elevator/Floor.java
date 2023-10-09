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

    public Floor goUp() {
        return Floor.byFloorNumber(this.floorNumber + 1);
    }
}
