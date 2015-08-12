package baseClasses;

import java.sql.Timestamp;

/**
 * Allows to manipulate the system time without actually changing it. Convenient for testing.
 * @author domme
 *
 */
public class Clock {

	static Timestamp time = new Timestamp(System.currentTimeMillis());
	
	/**
	 * Returns current time. May be manipulated. 
	 * @return current time
	 */
	public static Timestamp getCurrentTime() {
		return new Timestamp(time.getTime());
	}
	
	/**
	 * Sets a +- difference in milliseconds to the actual time
	 * @param offset
	 */
	public static void setOffset(long offset) {
		time.setTime(time.getTime()+offset);
	}
	
	/**
	 * Resets time to system time
	 */
	public static void reset() {
		time = new Timestamp(System.currentTimeMillis());
	}
}
