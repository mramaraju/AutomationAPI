
package com.fw.interfaces;

/**
 * Interface used to create real time log event listener, Listener which
 * implements {@code RealTimeLoggable} can be used to listen any
 * {@code Connectable} class log events.
 * 
 * @see Connectable
 */
public interface RealTimeLoggable {

	default public void connected() {
	}

	default public void disConnected() {
	}

	default public void send(byte[] data) {
	}

	default public void receive(byte[] data) {
	}

}
