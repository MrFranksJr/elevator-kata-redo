package io.tripled.elevator;

public interface Feedback {
    void doorsOpened(Floor floor);
    void floorPassed(Floor floor);
    void clearLogs();
}
