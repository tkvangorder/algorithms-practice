package algorithms.arrays;

import java.util.Arrays;

public class RetainPositiveNumbers {
	public static void main(String[] args) {
		int[] check = retainPositiveNumbers(new int[] {9, -1 , 6, 4, 1});
		System.out.println( Arrays.toString(check));
		check = retainPositiveNumbers(new int[] {-99, -1 , -7, 0, -7});
		System.out.println( Arrays.toString(check));
		check = retainPositiveNumbers(new int[] {7});
		System.out.println( Arrays.toString(check));
	}
	

    public static int[] retainPositiveNumbers(int[] a) {
    	if (a == null || a.length == 0) {
    		return a;
    	}
    	Arrays.sort(a);
    	for (int index=0;index < a.length; index++) {
    		if (a[index] < 1) continue;
    		return Arrays.copyOfRange(a, index, a.length);
    	}
    	return new int[0];
    }
}
