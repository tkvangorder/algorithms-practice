package alogirthms.arrays;

public class ArrayUtils {

	public static String arrayToString(int [] array) {
		StringBuilder builder = new StringBuilder("[");
		for (int index = 0; index < array.length; index++) {
			if (index > 0) {
				builder.append(",");
			}
			builder.append(array[index]);
		}
		builder.append("]");

		return builder.toString();
	}
}
