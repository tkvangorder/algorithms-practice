package algorithms.concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Suppose you are given the following code:
 * <PRE>
 * class FooBar {
 *   public void foo() {
 *     for (int i = 0; i < n; i++) {
 *       print("foo");
 *     }
 *   }
 *
 *   public void bar() {
 *    for (int i = 0; i < n; i++) {
 *      print("bar");
 *    }
 *   }
 * }
 * </PRE>
 * The same instance of FooBar will be passed to two different threads. Thread A will call foo()
 * while thread B will call bar(). Modify the given program to output "foobar" n times.
 *
 * NOTE: The funky contract for the methods foo and bar were dictated by leetcode, we
 *       have to go through some coding gymnastics to setup the test harness.
 */
public class SimpleFooBarPrinter {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		SimpleFooBarPrinter solution = new SimpleFooBarPrinter(40);

		ExecutorService executor = Executors.newFixedThreadPool(2);

		Runnable printFoo = () -> System.out.print("foo");
		Runnable printBar = () -> System.out.print("bar");

		executor.execute(() -> {
			try {
				solution.foo(printFoo);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		executor.execute(() -> {
			try {
				solution.bar(printBar);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		executor.shutdown();
	}

	private final int printCount;

	//This uses a boolean toggle where:
	//    true == ok to print "foo"
	//   false == ok to print "bar"
	// Initial state is set to print "foo"
	private boolean fooOrBarToggle = true;


	public SimpleFooBarPrinter(int n) {
		this.printCount = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {

		for (int index = 0; index < printCount; index++) {

			printGate(true); //Wait until it is ok to print "foo".
			// printFoo.run() outputs "foo". Do not change or remove this line.
			printFoo.run();
			flipPrintToggle(); //Flip the print toggle and notify both threads to wake up.
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {

		for (int index = 0; index < printCount; index++) {
			printGate(false); //Wait until it is ok to print "bar".
			// printBar.run() outputs "bar". Do not change or remove this line.
			printBar.run();
			flipPrintToggle(); //Flip the print toggle and notify both threads to wake up.
		}
	}

	/**
	 * This method will pause the thread if the toggle is not set to the same value passed
	 * in. Once the flag has been flipped (and threads have been notified) this method will
	 * exit
	 *
	 * This strategy only works because one thread is responsible for "foo" and one for
	 * "bar". If there were more threads, we would not be able to use this approach.
	 *
	 * @param toggle Desired toggle state.
	 */
	private synchronized void printGate(boolean toggle) {
		while (fooOrBarToggle != toggle) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method flips the toggle and notifies all waiting threads.
	 */
	private synchronized void flipPrintToggle() {
		fooOrBarToggle = !fooOrBarToggle;
		notifyAll();
	}

}
