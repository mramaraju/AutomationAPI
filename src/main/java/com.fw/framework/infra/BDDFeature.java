
package com.fw.framework.infra;

import java.util.ArrayList;
import java.util.List;

public class BDDFeature {

	List<BDDScenario> Scenarios = new ArrayList<>();
	BDDScenario background = null;

	public List<BDDScenario> getScenarios() {
		return Scenarios;
	}

	public void setScenarios(List<BDDScenario> scenarios) {
		Scenarios = scenarios;
	}

	public BDDScenario getBackground() {
		return background;
	}

	public void setBackground(BDDScenario background) {
		this.background = background;
	}

}
