
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Mandatory Annotation {@code TestCase} defines class to be a test case. User can provide optional parameters test sequence number
 * {@code sequence()}, skip attribute and label. All of the optional parameters will be ignored when user runs test case via test script or via
 * provided test list.
 * 
 * <p>
 * Annotation {@code RetentionPolicy.RUNTIME} is recorded in the class file by the compiler and retained by the VM at run time, so it may be read
 * reflectively.
 * </p>
 *
 */
// Make the annotation available at runtime:
@Retention(RetentionPolicy.RUNTIME)
// Allow to use only on types:
@Target(ElementType.TYPE)
public @interface TestCase {

	/**
	 * Responsible for marking test to be skipped if set true. Ignored if test list is provided via test script or using main() class.
	 * 
	 * @return true = skip test|false = execute test
	 */
	boolean skip() default false;

	/**
	 * Defines sequence in which test case should execute. Ignored if test list is provided via test script or using main() class.
	 * 
	 * @return test sequence number
	 */
	int sequence() default 1;

	/**
	 * Labels for each test cases
	 * 
	 * @return test case label
	 */
	// String[] label() default { "all" };

	/**
	 * Data provider name set insider {@code DataProvider} annotation
	 * 
	 * @return data provider method name
	 */
	// String dataprovider() default "";

	/**
	 * Test Timeout, If test execution did not finish within this time then test will be marked as fail
	 * 
	 * @return test timeout in milliseconds
	 */
	// long testtimeout() default 0;

	/**
	 * bug reference number
	 * 
	 * @return bugRefNumber
	 */
	String bugref() default "";

	/**
	 * Responsible for dropping execution of remaining test cases within test suite execution if particular test case outcome is a fail. Generally
	 * used for test cases which are critical or pre-requisite for other test cases
	 * 
	 * @return true = stop test suite execution upon failure | false = continue execution rest of the test case/units
	 */
	boolean dropRemainingTestsUponFailure() default false;
}
