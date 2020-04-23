
package com.fw.interfaces;

import java.util.List;

/**
 * Used for creating filter for any class which implements {@code Connectable}
 * 
 * @see Connectable
 */
public interface ConnectableMessageParser {

	/**
	 * 
	 * @param data
	 *            received byte array
	 * @return list of msgs after parsing of the byte array
	 */
	public List<byte[]> parse(byte[] data);

	public byte[] getLeftOverBytes();
}
