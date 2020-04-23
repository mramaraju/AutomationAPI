
package com.fw.interfaces;

import com.fw.framework.FWStaticStore;
import com.fw.framework.infra.TestContext;

/**
 * Interface implemented by each test cases.
 */
public interface TestExecutable {

	default public void execute(TestContext context) throws Exception {
		FWStaticStore.logDebug("execute() default method executed");
	}

}
