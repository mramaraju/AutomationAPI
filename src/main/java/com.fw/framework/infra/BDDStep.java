
package com.fw.framework.infra;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class BDDStep {

	private String stepAction;
	private String stepDescription;
	private LinkedHashMap<String, List<String>> localDataTable = new LinkedHashMap<>();
	private TestUnitObjectWrapper unit;
	private List<String> inlineParameterList = new ArrayList<>();
	private boolean hasGlobalReference = false;

	/**
	 * 
	 * @param stepAction Gherikin action keyword (GIVEN, AND, THEN, WHEN, BUT)
	 * @param stepDescription stepDescription description of a test step
	 * @param localDataTable local datatable object
	 */
	public BDDStep(String stepAction, String stepDescription, LinkedHashMap<String, List<String>> localDataTable) {
		super();
		this.stepAction = stepAction;
		this.stepDescription = stepDescription;
		this.localDataTable = localDataTable;
	}

	public String getStepDescription() {
		return stepDescription;
	}

	public void setStepDescription(String stepDescription) {
		this.stepDescription = stepDescription;
	}

	public LinkedHashMap<String, List<String>> getLocalDataTable() {
		return localDataTable;
	}

	public void setLocalDataTable(LinkedHashMap<String, List<String>> localDataTable) {
		this.localDataTable = localDataTable;
	}

	public TestUnitObjectWrapper getUnit() {
		return unit;
	}

	public void setUnit(TestUnitObjectWrapper unit) {
		this.unit = unit;
	}

	public List<String> getInlineParameterList() {
		return inlineParameterList;
	}

	public void setInlineParameterList(List<String> inlineParameterList) {
		this.inlineParameterList = inlineParameterList;
	}

	public boolean hasGlobalReference() {
		return hasGlobalReference;
	}

	public void setHasGlobalReference(boolean hasGlobalReference) {
		this.hasGlobalReference = hasGlobalReference;
	}

	public String getStepAction() {
		return stepAction;
	}

	public void setStepAction(String stepAction) {
		this.stepAction = stepAction;
	}

}
