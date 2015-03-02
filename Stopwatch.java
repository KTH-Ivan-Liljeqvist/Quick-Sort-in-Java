/**
 * A simple Stopwatch utility for measuring time in milliseconds.
 *
 * @author  Stefan Nilsson
 * @version 2011-02-07
 */
public class Stopwatch {
    /**
     * Time when start() was called. Contains a valid time
     * only if the clock is running.
     */
    private long startTime;

    /**
     * Holds the total accumulated time since last reset.
     * Does not include time since start() if clock is running.
     */
    private long totalTime = 0;

    private boolean isRunning = false;

    /**
     * Constructs a new Stopwatch. The new clock is not
     * running and the total time is set to 0.
     */
    public Stopwatch() {}

    /**
     * Turns this clock on.
     * Has no effect if the clock is already running.
     *
     * @return a reference to this Stopwatch.
     */
    public Stopwatch start() {
        if (!isRunning) {
            isRunning = true;
            startTime = System.nanoTime();
        }
        return this;
    }

    /**
     * Turns this clock off.
     * Has no effect if the clock is not running.
     *
     * @return a reference to this Stopwatch.
     */
    public Stopwatch stop() {
        if (isRunning) {
            totalTime += System.nanoTime() - startTime;
            isRunning = false;
        }
        return this;
    }

    /**
     * Resets this clock.
     * The clock is stopped and the total time is set to 0.
     *
     * @return a reference to this Stopwatch.
     */
    public Stopwatch reset() {
        isRunning = false;
        totalTime = 0;
        return this;
    }

    /**
     * Returns the total time that this clock has been running since
     * last reset.
     * Does not affect the running status of the clock; if the clock
     * is running when this method is called, it continues to run.
     *
     * @return the time in milliseconds.
     */
    public long milliseconds() {
        return nanoseconds() / 1000000;
    }

    /**
     * Returns the total time that this clock has been running since
     * last reset.
     * Does not affect the running status of the clock; if the clock
     * is running when this method is called, it continues to run.
     *
     * @return the time in nanoseconds.
     */
    public long nanoseconds() {
        return totalTime +
            (isRunning ? System.nanoTime() - startTime : 0);
    }

    /**
     * Tests if this clock is running.
     *
     * @return <code>true</code> if this clock is running;
     *         <code>false</code> otherwise.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Returns a string description of this clock. The exact details
     * of the representation are unspecified and subject to change,
     * but this is typical: "25 ms (running)".
     */
    @Override
    public String toString() {
        return milliseconds() + " ms" +
            (isRunning() ? " (running)" : " (not running)");
    }
    
    /**
     * Unit test. Run with <code>java -ea Stopwatch</code>.
     */
    public static void main(String[] args) throws InterruptedException {
        Stopwatch c = new Stopwatch();
        assert !c.isRunning();
        assert c.milliseconds() == 0;
        assert c.toString().equals("0 ms (not running)");
        
        c.stop();
        assert !c.isRunning();
        assert c.milliseconds() == 0;
        
        c.reset();
        assert !c.isRunning();
        assert c.milliseconds() == 0;
        
        c.start();
        String s = c.toString();
        assert s.equals("0 ms (running)") || s.equals("1 ms (running)");
        assert c.isRunning();
        c.stop();
        assert !c.isRunning();
                
        c.start();
        assert c.isRunning();
        c.reset();
        assert !c.isRunning();
        assert c.milliseconds() == 0;

        c.start();
        assert c.milliseconds() < 2;
        assert c.isRunning();
        Thread.sleep(2);
        assert c.isRunning();
        assert c.milliseconds() > 0;
        assert c.milliseconds() < 4;
        assert !c.toString().equals("0 ms (running)");
        Thread.sleep(10);
        assert c.isRunning();
        assert c.milliseconds() > 10;
        assert c.milliseconds() < 14;
        
        c.stop();
        assert !c.isRunning();
        assert c.milliseconds() > 10;
        assert c.milliseconds() < 14;
        
        c.stop();
        assert !c.isRunning();
        assert c.milliseconds() > 10;
        assert c.milliseconds() < 14;
        
        c.start();
        assert c.isRunning();
        assert c.milliseconds() > 10;
        assert c.milliseconds() < 14;
        Thread.sleep(10);
        assert c.isRunning();
        assert c.milliseconds() > 20;
        assert c.milliseconds() < 24;
        
        c.reset();
        assert !c.isRunning();
        assert c.milliseconds() == 0;
        assert c.toString().equals("0 ms (not running)");
    }
}
