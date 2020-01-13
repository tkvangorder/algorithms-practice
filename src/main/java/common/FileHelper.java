package common;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;

public class FileHelper {

	private static final String rootPath = "/home/tyler/work/sandbox/algs4-data/";

	public static In alogDataFile(String name) {
		return new In(rootPath + name);
	}

	public static Out writeAlogDataFile(String name) {
		return new Out(rootPath + name);
	}

}
