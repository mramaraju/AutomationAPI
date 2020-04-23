
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation {@code ExpectedException} can be used to define expected exception during test case execution. Specified exception will remain in scope
 * of test unit where annotation {@code ExpectedException} is defined. User can optionally provide regular expression which can be used to match
 * exception message.
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
@Target({ ElementType.METHOD })
public @interface ExpectedException {

	/**
	 * Mandatory argument which defines expected {@code Throwable}(s) or {@code Exception}(s)
	 * 
	 * @return array of {@code Throwable} or {@code Exception} class
	 */
	Class<? extends Throwable>[] expectedExceptions();

	/**
	 * Optional Exception Description, Accepts Regular expression
	 * 
	 * @return regular expression designed by user to match exception message
	 */
	String contains() default "";

	/**
	 * if enforce = true exception did not occur then test case will be marked failed.
	 * 
	 * @return true if enforced, false if not enforced
	 */
	boolean enforce() default true;
}
