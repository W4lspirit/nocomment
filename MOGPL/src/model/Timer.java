package model;

public class Timer {
	private static final int _TIME_LIMIT = 120000;
	private static long start;


	public static void start() {
		start = System.currentTimeMillis();
	}

	public static void check() throws TimeException {
		if (System.currentTimeMillis() > Timer.start + _TIME_LIMIT) {
			throw new TimeException();

		}

	}
}
