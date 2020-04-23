
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * Annotation {@code Group} let user define the array of test groups. All the tests belong to the global group by default which is defined as
 * "<b>*</b>". In addition to the global group test is also part of any other groups defined by the user.
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
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface Group {

	/**
	 * Test group array
	 * 
	 * @return array of group name which test case belongs to
	 */
	String[] group() default { "*" };

}
