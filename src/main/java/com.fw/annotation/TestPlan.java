
package com.fw.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 *
 */
// Make the annotation available at runtime:
@Retention(RetentionPolicy.RUNTIME)
// Allow to use only on types:
@Target({ ElementType.TYPE, ElementType.METHOD })
public @interface TestPlan {

	/**
	 * Test Description
	 * 
	 * @return test short description
	 */
	String description() default "";

	/**
	 * Name of the person who prepared the test
	 * 
	 * @return test engineer name
	 */
	String preparedBy() default "";

	/**
	 * Date of the test preparation
	 * 
	 * @return test case preparation date
	 */
	String preparationDate() default "";

	/**
	 * Test reviewer name
	 * 
	 * @return test case reviewer name
	 */
	String reviewedBy() default "";

	/**
	 * Test review date
	 * 
	 * @return test case review date
	 */
	String reviewDate() default "";

	/**
	 * Behaviour driven test plan, prefer to write it in Gherkin language
	 * 
	 * @return test plan in Gherkin format
	 */
	String bdd() default "";
}
