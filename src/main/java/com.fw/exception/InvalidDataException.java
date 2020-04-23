
package com.fw.exception;

/**
 * 
 * Checked Exception used for InvalidData
 *
 */
public class InvalidDataException extends Exception {

	private static final long serialVersionUID = 4966307464223695923L;

	public InvalidDataException() {
		super();
	}
	
	public InvalidDataException(String message) {
		super(message);
	}

}
