@echo off
mvnw -q install exec:java -Dexec.mainClass="io.tripled.elevator.ElevatorApplication"
