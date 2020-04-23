
package com.fw.interfaces;

import java.util.List;

import com.fw.framework.infra.TestContext;
import com.fw.framework.infra.TestObjectWrapper;

/**
 * Used for GUI Test Selector
 */
public interface TestRunnable {

	/**
	 * Execute tests from testList sequentially
	 * 
	 * @param context
	 *            Test Context
	 * @param testList
	 *            List of TestExecutors to run
	 * @throws Exception
	 *             exceptions that happened within the tests
	 */
	public void executeTest(TestContext context, List<TestObjectWrapper> testList) throws Exception;
}
