package io.tripled.elevator;

import java.util.*;

public class CallParser {
    public List<Floor> collectedCalls = new ArrayList<>();
    public List<ElevatorCall> collectedElevatorCalls = new ArrayList<>();

    public void parse(List<Floor> callList) {
        List<Floor> duplicateCallList = new LinkedList<>(callList);
        duplicateCallList.remove(0);
        duplicateCallList.remove(0);
        Floor originalOrigin = callList.get(0);
        Floor originalDestination = callList.get(1);
        collectedCalls.add(originalOrigin);
        collectedCalls.add(originalDestination);

        if (goingDown(originalOrigin, originalDestination)) {
            for (int i = 2; i < callList.size(); i += 2 ) {
                Floor newOrigin = callList.get(i);
                Floor newDestination = callList.get(i+1);
                if (goingDown(newOrigin, newDestination) && newOrigin.floorNumber <= originalOrigin.floorNumber) {
                    collectedCalls.add(newOrigin);
                    collectedCalls.add(newDestination);
                    duplicateCallList.remove(0);
                    duplicateCallList.remove(0);
                }
            }
            //remove duplicates from orderedCalls
            HashSet<Floor> uniqueSet = new HashSet<>(collectedCalls);
            List<Floor> uniqueList = new ArrayList<>(uniqueSet);

            Collections.sort(uniqueList, Collections.reverseOrder());

            for (int j = 0; j < uniqueList.size() -1; j++) {
                ElevatorCall newCall = new ElevatorCall(uniqueList.get(j), uniqueList.get(j+1));
                collectedElevatorCalls.add(newCall);
            }

            if (!duplicateCallList.isEmpty()) {
                ElevatorCall intermediateCall = new ElevatorCall(uniqueList.get(uniqueList.size() - 1), duplicateCallList.get(0));
                collectedElevatorCalls.add(intermediateCall);


                for (int k = 0; k < duplicateCallList.size(); k += 2) {
                    Floor newOrigin = duplicateCallList.get(k);
                    Floor newDestination = duplicateCallList.get(k + 1);
                    if (goingUp(newOrigin, newDestination)) {
                        ElevatorCall newCall = new ElevatorCall(duplicateCallList.get(k), duplicateCallList.get(k + 1));
                        collectedElevatorCalls.add(newCall);
                    }
                }
            }

        }

        if (goingUp(originalOrigin, originalDestination)) {
            for (int i = 2; i < callList.size(); i += 2 ) {
                Floor newOrigin = callList.get(i);
                Floor newDestination = callList.get(i+1);
                if (goingUp(newOrigin, newDestination) && newOrigin.floorNumber >= originalOrigin.floorNumber) {
                    collectedCalls.add(newOrigin);
                    collectedCalls.add(newDestination);
                    duplicateCallList.remove(0);
                    duplicateCallList.remove(0);
                }
            }
            //remove duplicates from orderedCalls
            HashSet<Floor> uniqueSet = new HashSet<>(collectedCalls);
            List<Floor> uniqueList = new ArrayList<>(uniqueSet);

            Collections.sort(uniqueList, Collections.reverseOrder());

            for (int j = 0; j < uniqueList.size() -1; j++) {
                ElevatorCall newCall = new ElevatorCall(uniqueList.get(j), uniqueList.get(j+1));
                collectedElevatorCalls.add(newCall);
            }

            if (!duplicateCallList.isEmpty()) {
                ElevatorCall intermediateCall = new ElevatorCall(uniqueList.get(uniqueList.size() - 1), duplicateCallList.get(0));
                collectedElevatorCalls.add(intermediateCall);


                for (int k = 0; k < duplicateCallList.size(); k += 2) {
                    Floor newOrigin = duplicateCallList.get(k);
                    Floor newDestination = duplicateCallList.get(k + 1);
                    if (goingUp(newOrigin, newDestination)) {
                        ElevatorCall newCall = new ElevatorCall(duplicateCallList.get(k), duplicateCallList.get(k + 1));
                        collectedElevatorCalls.add(newCall);
                    }
                }
            }
        }

    }

    private boolean goingDown(Floor callOrigin, Floor callDestination) { return callOrigin.floorNumber > callDestination.floorNumber; }
    private boolean goingUp(Floor callOrigin, Floor callDestination) { return callOrigin.floorNumber < callDestination.floorNumber; }

    //private void parseGoingDownCalls
}
