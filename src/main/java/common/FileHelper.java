package common;

import java.io.InputStream;

import edu.princeton.cs.algs4.Out;

public class FileHelper {

	private static final String rootPath = "/Users/tyler.vangorder/work/algorithms/data/";
	
	public static In alogDataFile(String name) {
		return new In(rootPath + name);
	}

	public static Out writeAlogDataFile(String name) {
		return new Out(rootPath + name);
	}

	public static InputStream getInputStreamForClassResource(Class<?> clazz, String resourceName) {
		if (clazz == null) {
			throw new IllegalArgumentException("The class cannot be null");
		}
		if (resourceName == null) {
			throw new IllegalArgumentException("The resource name cannot be null");
		}
		InputStream in = clazz.getResourceAsStream(resourceName);
		if (in == null) {
			throw new IllegalArgumentException("The resource [" + resourceName + "] could not be loaded from the class path");
		}
		return in;
	}
}