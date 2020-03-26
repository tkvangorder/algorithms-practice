package algorithms;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.In;

/**
 * This is a problem where you are trying to find the minimum number of meeting rooms to accommodate the schedule
 * (essentially, finding the maximimum number of overlapping meetings.)
 *
 * The inputs are expected to be a single int to indicate the total number of meetings and then a set of integer pairs
 * that are used to represent the start and end time of each meeting.
 *
 * The time complexity is O (N log N) based on the time complexity of the sort of the start/end times and then requiring
 * us to iterate once through the set.
 */
public class OverlappingMeetings {

	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {

		//Read input
		In input = new In(OverlappingMeetings.class, "meetings2input.txt");
		int numberOfMeetings = input.readInt();

		int meetingStarts[] = new int[numberOfMeetings];
		int meetingEnds[] = new int[numberOfMeetings];

		for (int index = 0; index < numberOfMeetings; index++) {
			meetingStarts[index] = input.readInt();
			meetingEnds[index] = input.readInt();
		}

		//Sort the meetings in ascending order by start.
		Arrays.sort(meetingStarts);
		Arrays.sort(meetingEnds);

		int meetingRoomMax = 1;
		int startIndex = 1;
		int endIndex = 0;
		int currentMax = 1;
		while (startIndex < numberOfMeetings && endIndex < numberOfMeetings) {
			if (meetingStarts[startIndex] <= meetingEnds[endIndex]) {
				currentMax++;
				if (currentMax > meetingRoomMax) {
					meetingRoomMax = currentMax;
				}
				startIndex++;
			} else {
				currentMax--;
				endIndex++;
			}
		}
		logger.info("The maximum number of rooms is : " + meetingRoomMax);
	}

}
