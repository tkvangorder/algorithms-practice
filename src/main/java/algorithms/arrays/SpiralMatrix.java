package algorithms.arrays;

import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import common.FileHelper;

/**
 *
 * <PRE>
 * </PRE>
 *
 * @author tyler
 */
public class SpiralMatrix {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");
	private static final Scanner scanner = new Scanner(FileHelper.getInputStreamForClassResource(SpiralMatrix.class, "soultion-input.txt"));

	public static void main(String[] args) {
		SpiralMatrix solution = new SpiralMatrix();
		solution.method();
	}

	public void method() {

	}
}
