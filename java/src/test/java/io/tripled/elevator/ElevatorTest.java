package io.tripled.elevator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ElevatorTest {

    private Elevator elevator;
    private TestFeedback testFeedBack;
    private CallParser callParser;

    @BeforeEach
    void setUp() {
        //create new feedback module
        testFeedBack = new TestFeedback();
        //always create a call parser
        callParser = new CallParser();
        //always create a new elevator
        elevator = new Elevator(testFeedBack, callParser);
    }

    @Test
    void elevatorStartsAtGroundFloor() {
        //read the currentFloor of the elevator
        assertEquals(Floor.GROUND, elevator.getCurrentFloor());
    }

    @Test
    void elevatorCanGoToThirdFloor() {
        //call the elevator to floor 3
        elevator.call(List.of(Floor.FLOOR_3));
        //elevator needs to travel to third floor
        assertEquals(Floor.FLOOR_3, elevator.getCurrentFloor());
        //elevator needs to report that the doors opened at the third floor
        assertEquals(List.of(Floor.FLOOR_3), testFeedBack.allDoorsOpened());
    }

    @Test
    void elevatorReportsOnFloorsPassed() {
        elevator.call(List.of(Floor.FLOOR_3));
        //when traveling to a floor, elevator reports on all floors passed
        assertEquals(List.of(Floor.FLOOR_1, Floor.FLOOR_2, Floor.FLOOR_3), testFeedBack.allFloorsPassed());
    }

    @Test
    void elevatorReportsOnDoorsOpenedPerCallMade() {
        elevator.call(List.of(Floor.FLOOR_3));
        assertEquals(List.of(Floor.FLOOR_3), testFeedBack.allDoorsOpened());
        elevator.call(List.of(Floor.FLOOR_1));
        assertEquals(List.of(Floor.FLOOR_1), testFeedBack.allDoorsOpened());
    }

    @Test
    void elevatorShouldBeAbleToGoDown() {
        elevator.call(List.of(Floor.BASEMENT));
        assertEquals(List.of(Floor.BASEMENT), testFeedBack.allFloorsPassed());
        assertEquals(List.of(Floor.BASEMENT), testFeedBack.allDoorsOpened());
    }

    @Test
    void elevatorShouldBeAbleToParseFullCall() {
        elevator.call(List.of(Floor.FLOOR_3, Floor.BASEMENT));

        assertEquals(List.of(Floor.FLOOR_1, Floor.FLOOR_2, Floor.FLOOR_3, Floor.FLOOR_2, Floor.FLOOR_1, Floor.GROUND, Floor.BASEMENT), testFeedBack.allFloorsPassed());
        assertEquals(List.of(Floor.FLOOR_3, Floor.BASEMENT), testFeedBack.allDoorsOpened());
    }

    @Test
    void elevatorShouldBeAbleToParseMultipleCallsInTheSameDirection() {
        elevator.call(List.of(Floor.FLOOR_3, Floor.BASEMENT, Floor.GROUND, Floor.BASEMENT, Floor.FLOOR_2, Floor.BASEMENT));

        assertEquals(List.of(Floor.FLOOR_1, Floor.FLOOR_2, Floor.FLOOR_3, Floor.FLOOR_2, Floor.FLOOR_1, Floor.GROUND, Floor.BASEMENT), testFeedBack.allFloorsPassed());
        assertEquals(List.of(Floor.FLOOR_3, Floor.FLOOR_2, Floor.GROUND, Floor.BASEMENT), testFeedBack.allDoorsOpened());
    }
}
