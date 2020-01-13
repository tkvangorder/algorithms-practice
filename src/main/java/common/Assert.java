package common;

public final class Assert {

	private Assert() {
	}

	public static final void isTrue(boolean condition, String message) {
		if (!condition) {
			throw new IllegalArgumentException(message);
		}
	}

	public static final void notNull(Object value, String message) {
		if (value == null) {
			throw new IllegalArgumentException(message);
		}
	}

	public static final void hasText(String value, String message) {
		if (value == null || value.trim().length() == 0) {
			throw new IllegalArgumentException(message);
		}
	}
}
