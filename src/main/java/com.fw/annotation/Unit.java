
package com.fw.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation {@code Unit} can be used to annotate method which can be unit test within a test case.
 * 
 * <p>
 * Annotation {@code RetentionPolicy.RUNTIME} is recorded in the class file by the compiler and retained by the VM at run time, so it may be read
 * reflectively.
 * </p>
 *
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Unit {

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
	 * Test Timeout, If test execution did not finish within this time then test will be marked as fail
	 * 
	 * @return test timeout in milliseconds
	 */
	long testtimeout() default 0;

	/**
	 * Data provider name set insider {@code DataProvider} annotation
	 * 
	 * @return data provider method name
	 */
	String dataprovider() default "";

	/**
	 * bug reference number
	 * 
	 * @return bugRefNumber
	 */
	String bugref() default "";

	/**
	 * Responsible for dropping execution of remaining test units within test case execution if particular test unit outcome is a fail. Generally
	 * used for test units which are critical or pre-requisite for other test units
	 * 
	 * @return true = stop test suite execution upon failure | false = continue execution rest of the test case/units
	 */
	boolean dropRemainingUnitsUponFailure() default false;
}
