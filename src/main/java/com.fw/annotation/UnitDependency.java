
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Optional Annotation {@code Dependency} defines dependency on other test units within a scan scope.
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
@Target(ElementType.METHOD)
public @interface UnitDependency {

	/**
	 * Mandatory argument which declares dependency method names
	 * 
	 * @return array of String containing method names
	 */
	String[] dependency();

}
