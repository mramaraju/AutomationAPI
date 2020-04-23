
package com.fw.interfaces;

import java.util.List;

import com.fw.framework.infra.BDDScenario;
import com.fw.framework.infra.TestContext;

/**
 * Used for BDD GUI Test Selector
 */
public interface TestScenarioRunnable {

	/**
	 * Execute tests from testScenarioList sequentially
	 * 
	 * @param context Test Context
	 * @param scenarioList List of TestScenarios to run
	 * @throws Exception exceptions that happened within the tests
	 */
	public void executeTest(TestContext context, List<BDDScenario> scenarioList) throws Exception;
}