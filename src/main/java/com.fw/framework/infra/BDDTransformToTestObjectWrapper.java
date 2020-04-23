
package com.fw.framework.infra;

import java.util.HashMap;
import java.util.Map;

public class BDDTransformToTestObjectWrapper {

	private Map<String, TestUnitObjectWrapper> stepDefinitionMap;

	/**
	 * Transforms given test list of{@code TestEecutable} type into {@code TestObjectWrapper} type list. This method will only consider test This
	 * method can not transform test cases outside current package, so those test cases will be omitted from the list
	 * 
	 * @param context {@code TestContext}
	 * @throws Exception in case any requirement of test cases are not met
	 */
	protected BDDTransformToTestObjectWrapper(TestContext context) throws Exception {
		stepDefinitionMap = new HashMap<>();

		if (null == context.getTestSuite().getTestGroupList() || context.getTestSuite().getTestGroupList().isEmpty()) {
			new Exception("Group must be specified");
		}

		// If main() method executes from root then package name will be none
		String packageName = "";
		if (null != context.getPrePostRunnableObj().getPackage()) {
			packageName = context.getPrePostRunnableObj().getPackage().getName();
		}
		BDDScanTestSuite reflection = new BDDScanTestSuite(context, packageName);
		stepDefinitionMap = reflection.getStepDefinitionsMap();
	}

	public Map<String, TestUnitObjectWrapper> getStepDefinitionMap() {
		return stepDefinitionMap;
	}

	protected void setStepDefinitionMap(Map<String, TestUnitObjectWrapper> stepDefinitionMap) {
		this.stepDefinitionMap = stepDefinitionMap;
	}
}
