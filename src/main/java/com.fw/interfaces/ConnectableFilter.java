
package com.fw.interfaces;

/**
 * Used for creating filter for any class which implements {@code Connectable}
 * 
 * @see Connectable
 */
public interface ConnectableFilter {

	/**
	 * 
	 * @param data
	 *            byte array under scanner
	 * @return true|false based on defined criteria
	 */
	public boolean meetCriteria(byte[] data);

}
