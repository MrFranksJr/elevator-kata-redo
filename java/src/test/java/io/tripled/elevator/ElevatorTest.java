package io.tripled.elevator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorTest {

    @Test
    void elevatorStartsAtGroundFloor() {
        var elevator = new Elevator();

        assertEquals(Floor.GROUND, elevator.getCurrentFloor());
    }
}
