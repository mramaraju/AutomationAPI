
package com.fw.exception;

/**
 *
 * Checked Exception used to highlight code travelling through wrong/unexpected path
 *
 */
public class WrongFlowException extends Exception {

	private static final long serialVersionUID = -7654109285223161620L;

	public WrongFlowException() {
		super();
	}

	public WrongFlowException(String message) {
		super(message);
	}
}
