/**
 * General class to time tasks
 */
public class Timer {
	long start;

	public void start() {
		start = System.currentTimeMillis();
	}
	
	public long getTime() {
		return System.currentTimeMillis() - start;
	}
	
}
