
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.fw.interfaces.TestExecutable;

/**
 * 
 * Optional Annotation {@code Dependency} defines dependency on other test cases within a scan scope.
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
public @interface TestDependency {

	/**
	 * Mandatory argument which defines expected {@code Throwable}(s) or {@code Exception}(s)
	 * 
	 * @return array of {@code Throwable} or {@code Exception} class
	 */
	Class<? extends TestExecutable>[] dependency();

}
