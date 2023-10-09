package io.tripled.elevator;

import java.util.*;

public class CallParser {
    public List<Floor> collectecCalls = new ArrayList<>();
    public List<ElevatorCall> collectedElevatorCalls = new ArrayList<>();

    public void parse(List<Floor> callList) {
        List<Floor> duplicateCallList = new LinkedList<>(callList);
        duplicateCallList.remove(0);
        duplicateCallList.remove(0);
        Floor originalOrigin = callList.get(0);
        Floor originalDestination = callList.get(1);
        collectecCalls.add(originalOrigin);
        collectecCalls.add(originalDestination);

        if (goingDown(originalOrigin, originalDestination)) {
            for (int i = 2; i < callList.size(); i += 2 ) {
                Floor newOrigin = callList.get(i);
                Floor newDestination = callList.get(i+1);
                if (goingDown(newOrigin, newDestination) && newOrigin.floorNumber < originalOrigin.floorNumber) {
                    collectecCalls.add(newOrigin);
                    collectecCalls.add(newDestination);
                    duplicateCallList.remove(0);
                    duplicateCallList.remove(0);
                }
            }
            //remove duplicates from orderedcalls
            HashSet<Floor> uniqueSet = new HashSet<>(collectecCalls);
            List<Floor> uniqueList = new ArrayList<>(uniqueSet);

            Collections.sort(uniqueList, Collections.reverseOrder());

            for (int j = 0; j < uniqueList.size() -1; j++) {
                ElevatorCall newCall = new ElevatorCall(uniqueList.get(j), uniqueList.get(j+1));
                collectedElevatorCalls.add(newCall);
            }

        }
        if (goingUp(originalOrigin, originalDestination)) {

        }

    }

    private boolean goingDown(Floor callOrigin, Floor callDestination) { return callOrigin.floorNumber > callDestination.floorNumber; }
    private boolean goingUp(Floor callOrigin, Floor callDestination) { return callOrigin.floorNumber < callDestination.floorNumber; }
}
