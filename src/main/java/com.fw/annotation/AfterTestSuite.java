
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation {@code AfterTestSuite} can be used to perform actions that are
 * required after completion of a test suite. E.g. User may want to do a clean
 * up. The method annotated by this annotation gets executed only once post test
 * suite execution.
 * 
 * <p>
 * Annotation {@code RetentionPolicy.RUNTIME} is recorded in the class file by
 * the compiler and retained by the VM at run time, so it may be read
 * reflectively.
 * </p>
 */
// Make the annotation available at runtime:
@Retention(RetentionPolicy.RUNTIME)
// Allow to use only on types:
@Target(ElementType.METHOD)
public @interface AfterTestSuite {

}
