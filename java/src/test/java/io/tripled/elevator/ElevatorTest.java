package io.tripled.elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorTest {

    private Elevator elevator;
    private TestFeedback testFeedBack;

    @BeforeEach
    void setUp() {
        //create new feedback module
        testFeedBack = new TestFeedback();
        //always create a new elevator
        elevator = new Elevator(testFeedBack);
    }

    @Test
    void elevatorStartsAtGroundFloor() {
        //read the currentfloor of the elevator
        assertEquals(Floor.GROUND, elevator.getCurrentFloor());
    }

    @Test
    void elevatorCanGoToThirdFloor() {
        //call the elevator to floor 3
        elevator.call(Floor.FLOOR_3);
        //elevator needs to travel to third floor
        assertEquals(Floor.FLOOR_3, elevator.getCurrentFloor());
        //elevator needs to report that the doors opened at the third floor
        assertEquals(Floor.FLOOR_3, testFeedBack.doorsOpenedAtFloor());
    }

    @Test
    void elevatorReportsOnFloorsPassed() {
        elevator.call(Floor.FLOOR_3);
        //when traveling to a floor, elevator reports on all floors passed
        assertEquals(List.of(Floor.FLOOR_1, Floor.FLOOR_2, Floor.FLOOR_3), testFeedBack.allFloorsPassed());
    }
}
