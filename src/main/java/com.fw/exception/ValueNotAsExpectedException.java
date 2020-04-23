
package com.fw.exception;

import com.fw.utils.Guard;

/**
 * 
 * Checked Exception used for Guard class if compared values are not as expected
 * 
 * @see Guard
 *
 */
public class ValueNotAsExpectedException extends Exception {

	private static final long serialVersionUID = 3143300873685133606L;

	public ValueNotAsExpectedException() {
		super();
	}
	
	public ValueNotAsExpectedException(String message) {
		super(message);
	}

}
