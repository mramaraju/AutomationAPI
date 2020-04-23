
package com.fw.annotation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fw.framework.Enums.Importance;

/**
 * 
 * Optional Annotation {@code TestImportance} defines test importance. This will be highlighted during failure so user can quickly be warned if
 * failing test cases are critical to system.
 * 
 * <p>
 * Annotation {@code RetentionPolicy.RUNTIME} is recorded in the class file by the compiler and retained by the VM at run time, so it may be read
 * reflectively.
 * </p>
 *
 */
@Retention(RUNTIME)
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface TestImportance {

	/**
	 * Accepts Enum which defines test importance, incase of failure this will become a deciding factor on how urgent test needs to be investigated
	 * 
	 * @return true = skip test|false = execute test
	 */
	public Importance value() default Importance.UNDEFINED;

}
