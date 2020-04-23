
package com.fw.interfaces;

import java.util.concurrent.TimeUnit;

import com.fw.utils.Heartbeat;

/**
 * Interface recommended for classes which are responsible for making connections (socket,
 * serial etc..) class which implements {@code Connectable} can be used along
 * with {@code Heartbeat} class to provide hearbeat/keepalive.
 * 
 * @see Heartbeat
 */
public interface Connectable {

	public void connect() throws Exception;

	public void disconnect() throws Exception;

	public boolean isConnected();

	public void sendMsg(byte[] data) throws Exception;

	public byte[] getNextMsg() throws Exception;

	public byte[] getNextMsg(long timeout, TimeUnit timeunit) throws Exception;

	public boolean hasNextMsg();

}
