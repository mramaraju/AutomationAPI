
package com.fw.framework.parser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fw.framework.infra.BDDFeatureObjectWrapper;

public class TestSuite {

	private String suiteName = "SuiteName";
	private int loopCount = 1; // default 1
	private List<String> testGroupList = new ArrayList<>();
	private List<String> testUnitGroupList = new ArrayList<>();
	private List<String> testFQCNList = new ArrayList<>();
	private Map<String, String> testSuiteParameters = new HashMap<>();
	private String threadName = "ThreadN";
	private String version = "1";
	private boolean enable = true;
	private boolean testScriptProvided = false;
	private List<BDDFeatureObjectWrapper> featureFiles = new ArrayList<>();

	// *****************************************************************
	// Getters and setters
	// *****************************************************************
	public List<String> getTestFQCNList() {
		return testFQCNList;
	}

	public void setTestFQCNList(List<String> testFQCNList) {
		this.testFQCNList = testFQCNList;
	}

	public Map<String, String> getTestSuiteParameters() {
		return testSuiteParameters;
	}

	public void setTestSuiteParameters(Map<String, String> testSuiteParameters) {
		this.testSuiteParameters = testSuiteParameters;
	}

	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public String getSuiteName() {
		return suiteName;
	}

	public void setSuiteName(String suiteName) {
		this.suiteName = suiteName;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	public List<String> getTestGroupList() {
		return testGroupList;
	}

	public void setTestGroupList(List<String> testGroupList) {
		this.testGroupList = testGroupList;
	}

	public List<String> getTestUnitGroupList() {
		return testUnitGroupList;
	}

	public void setTestUnitGroupList(List<String> testUnitGroupList) {
		this.testUnitGroupList = testUnitGroupList;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	public boolean isTestScriptProvided() {
		return testScriptProvided;
	}

	public void setTestScriptProvided(boolean testScriptProvided) {
		this.testScriptProvided = testScriptProvided;
	}

	public List<BDDFeatureObjectWrapper> getFeatureFiles() {
		return featureFiles;
	}

	public void setFeatureFiles(List<BDDFeatureObjectWrapper> featureFiles) {
		this.featureFiles = featureFiles;
	}

}
