package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

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
public class SemaphoreFooBarPrinter {
	private static final Logger logger = LoggerFactory.getLogger("algorithms");

	public static void main(String[] args) {
		SemaphoreFooBarPrinter solution = new SemaphoreFooBarPrinter(40);

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
	}

	private final int printCount;
	private final Semaphore fooSemaphore = new Semaphore(0);
	private final Semaphore barSemaphore = new Semaphore(1);

	public SemaphoreFooBarPrinter(int n) {
		this.printCount = n;
	}

	public void foo(Runnable printFoo) throws InterruptedException {

		for (int index = 0; index < printCount; index++) {
			// printFoo.run() outputs "foo". Do not change or remove this line.
			barSemaphore.acquire();
			printFoo.run();
			fooSemaphore.release();
		}
	}

	public void bar(Runnable printBar) throws InterruptedException {

		for (int index = 0; index < printCount; index++) {

			// printBar.run() outputs "bar". Do not change or remove this line.
			fooSemaphore.acquire();
			printBar.run();
			barSemaphore.release();
		}
	}
}
