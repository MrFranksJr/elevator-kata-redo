package io.tripled.elevator;

public enum Floor {
    BASEMENT(-1),
    GROUND(0),
    FLOOR_1(1),
    FLOOR_2(2),
    FLOOR_3(3);

    public int floorNumber;

    Floor(int i) {
        this.floorNumber = i;
    }
}
