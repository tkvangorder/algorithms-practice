package chapter1.section3;

import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.In;

/**
 * This class is used to simulate an elevator. It uses a background thread to operate the elevator on a configurable time slice (in seconds)
 */
public class ElevatorManager {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	private static final int numberOfFloors = 20;
	private static final int timeSliceSeconds = 3;
	private static final int timeForPassengerLoadingSeconds = 5;

	private int currentFloor = 1;
	private ElevatorAction currentDirection = ElevatorAction.UP;

	private TreeSet<Integer> downQueue = new TreeSet<>();
	private TreeSet<Integer> upQueue = new TreeSet<>();

	public static void main(String[] args) {

		logger.info("Welcome to the building! This elevator has access to floors 1 through " + numberOfFloors + ".");
		logger.info("You may exit this simulator by typing 'exit'");
		logger.info("=============================================================================================");

		In input = new In();

		final ElevatorManager manager = new ElevatorManager();

		//Start the "elevator" calling the "move" every time slice. We also use this primitive form of scheduling becase
		//we can add additional pauses in the moveElevator method. (like wait longer when we visit a floor.)
		Thread elevatorController = new Thread(
				() -> {
					while (true) {
						try {
							Thread.sleep(timeSliceSeconds * 1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						manager.moveElevator();
					}
				}
				);
		//we set the thread to daemon so that it will terminate when the main thread exits.
		elevatorController.setDaemon(true);
		elevatorController.start();

		while (input.exists()) {
			String command = input.readLine();
			if (command.equals("exit")) {
				break;
			}
			String[] parameters = command.split(" ");
			if (parameters.length != 2) {
				logger.error("  - Syntax error. The command must be of the form <floor> <action>");
				continue;
			}
			int floor = 1;
			try {
				floor = Integer.parseInt(parameters[0]);
				if (floor < 1 || floor > numberOfFloors) {
					logger.error("  - Invalid floor. You only have access to floors 1 through " + numberOfFloors + ".");
				}
			} catch (NumberFormatException exception) {
				logger.error("  - Syntax error. The first parameter must be an integer.");
			}

			if (parameters[1].equalsIgnoreCase("D")) {
				if (floor == numberOfFloors) {
					//ignore a request to go "Down to the top floor"
					continue;
				}
				manager.scheduleElevatorCommand(floor, ElevatorAction.DOWN);
			} else if (parameters[1].equalsIgnoreCase("U")) {
				if (floor == 1) {
					//ignore a request to go "Up to the first floor."
					continue;
				}
				manager.scheduleElevatorCommand(floor, ElevatorAction.UP);
			} else {
				logger.error("  - Syntax error. The second parameter can only be 'D' (for Down) or 'U' (for Up.).");
			}
		}
	}

	/**
	 * This method schedules a new elevator command. This method manipulates the internal state of the manager and must be
	 * synchronized to ensure the manager does not get into an invalid state.
	 * @param floor
	 * @param up
	 */
	private synchronized void scheduleElevatorCommand(int floor, ElevatorAction action) {
		if (action == ElevatorAction.DOWN) {
			downQueue.add(floor);
		} else {
			upQueue.add(floor);
		}
	}

	/**
	 * This method manipulates the internal state of the manager and must be synchronized to ensure the manager
	 * does not get into an invalid state.
	 */
	private synchronized void moveElevator() {

		if (currentDirection == ElevatorAction.UP && upQueue.tailSet(currentFloor).size() > 0) {
			//There are still more floors we need to visit.
			currentFloor++;
			if (upQueue.remove(currentFloor)) {
				logger.info("Stopping on floor [" + currentFloor + "] going UP");
				logger.info("Giving you a little extra time to tell me which floor you are heading.");
				try {
					Thread.sleep(timeForPassengerLoadingSeconds * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				logger.info("Going UP passing floor [" + currentFloor + "].");
			}
		} else if (currentDirection == ElevatorAction.DOWN && downQueue.headSet(currentFloor).size() > 0) {
			currentFloor--;
			if(downQueue.remove(currentFloor)) {
				logger.info("Stopping on floor [" + currentFloor + "] going DOWN.");
				logger.info("Giving you a little extra time to tell me which floor you are heading.");
				try {
					Thread.sleep(timeForPassengerLoadingSeconds * 1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				logger.info("Going DOWN passing floor [" + currentFloor + "].");
			}
		} else {
			//Ran out of floors in the same direction, now what?
			int nextFloor = 1;

			if (currentDirection == ElevatorAction.UP && !downQueue.isEmpty()) {
				//Prioritize down, if we have run out of "up" floors.
				nextFloor = downQueue.last();
			} else if (!upQueue.isEmpty()) {
				//Prioritize up, if we have run out of "down" floors.
				nextFloor = upQueue.first();
			} else if (!downQueue.isEmpty()){
				nextFloor = downQueue.last();
			}

			if (currentFloor == 1 && nextFloor == 1) {
				currentDirection = ElevatorAction.UP;
				logger.info("Waiting on the bottom floor.");
			} else if (currentFloor < nextFloor) {
				scheduleElevatorCommand(nextFloor, ElevatorAction.UP);
				currentDirection = ElevatorAction.UP;
			} else {
				scheduleElevatorCommand(nextFloor, ElevatorAction.DOWN);
				currentDirection = ElevatorAction.DOWN;
			}
		}
	}

	private enum ElevatorAction {
		UP,
		DOWN;
	}
}
