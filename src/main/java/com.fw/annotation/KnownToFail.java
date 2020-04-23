
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation {@code KnownToFail} can be used to enable KTF check, if enabled then test case must fail with {@code TestStatus} = KTF status, otherwise
 * test case will be marked as failed. User may provide optional parameter using bugRef, which allows user to specify any bugRef/Ticket which can be
 * printed in the report.
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
public @interface KnownToFail {

	/**
	 * Check which can be enabled to ensure that test case annotated with {@code KnownToFail} fails with {@code TestStatus} = KTF status.
	 * 
	 * @return true = test case outcome must be {@code TestStatus} = KTF otherwise test case will be marked as fail|false = test case outcome can be
	 *         anything.
	 */
	boolean ktf() default true;

}
