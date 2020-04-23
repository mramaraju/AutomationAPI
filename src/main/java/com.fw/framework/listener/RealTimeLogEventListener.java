
package com.fw.framework.listener;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Logger;

import com.fw.framework.infra.LogWrapper;
import com.fw.framework.infra.TestContext;
import com.fw.interfaces.RealTimeLoggable;
import com.fw.utils.Transform;

/**
 * Responsible for logging byte array provided by registering classes. Generally
 * used in cases where real time logging is required for all send/receive type
 * of communication. Example: {@code TCPClient}. Logs will be stored in the log
 * file with name *-realtime.*
 *
 */
public class RealTimeLogEventListener implements RealTimeLoggable {

	static final String FQCN = LogWrapper.class.getName();
	Transform _tfm = new Transform();
	TestContext context;
	Logger logger;

	/**
	 * Constructor of type 'Real Time Log Listener' storing the context and the
	 * log4j logger object
	 * 
	 * @param context
	 *            Test context
	 */
	public RealTimeLogEventListener(TestContext context) {
		this.context = context;
		this.logger = context.getLogger().getRealTimeLogger();
	}

	/**
	 * Logs the byte array
	 */
	@Override
	public void send(byte[] data) {
		logger.logIfEnabled(FQCN, Level.TRACE, null, "Req: {}", _tfm.bytesToHexString(data));
	}

	/**
	 * Logs the byte array
	 */
	@Override
	public void receive(byte[] data) {
		logger.logIfEnabled(FQCN, Level.TRACE, null, "Res: {}", _tfm.bytesToHexString(data));
	}

	/**
	 * Logs if connect event is triggered
	 */
	@Override
	public void connected() {
		// Do not do anything
	}

	/**
	 * Logs if disconnect event is triggered
	 */
	@Override
	public void disConnected() {
		// Do not do anything
	}
}
