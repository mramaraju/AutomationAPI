
package com.fw.framework.infra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.scanners.TypeAnnotationsScanner;

import com.fw.annotation.ExpectedException;
import com.fw.annotation.Group;
import com.fw.annotation.KnownToFail;
import com.fw.annotation.StepDefinition;
import com.fw.annotation.TestImportance;
import com.fw.annotation.TestPlan;
import com.fw.annotation.Unit;

import javassist.Modifier;

/**
 * This class provides all utilities for reflection
 * 
 * 
 *
 */
public class BDDScanTestSuite {

	TestContext context;
	Reflections reflection;
	Map<String, TestUnitObjectWrapper> stepDefinitionsMap = new HashMap<>();

	/**
	 * Scans all packages within provided package
	 * 
	 * @param context Test context
	 * @param packageName Base package name
	 * 
	 */
	public BDDScanTestSuite(TestContext context, String packageName) {
		this.context = context;

		System.out.println("Scanning for test cases. Please wait...");
		scan(packageName);
	}

	/**
	 * Scans for Test methods within provided packageName
	 * 
	 * @param packageName Base package name
	 */
	private void scan(String packageName) {

		// Find all annotation
		reflection = new Reflections(packageName, new MethodAnnotationsScanner(), new TypeAnnotationsScanner(), new SubTypesScanner(false));

		// GetAllStepDefMethods => Filter Public methods => Get UpperCase StepDef => Store it
		reflection.getMethodsAnnotatedWith(StepDefinition.class).stream().filter(method -> Modifier.isPublic(method.getModifiers()))
				.forEach(method -> {

					Unit unit = method.getAnnotation(Unit.class);
					TestPlan testplan = method.getAnnotation(TestPlan.class);
					KnownToFail ktf = method.getAnnotation(KnownToFail.class);
					ExpectedException expectedException = method.getAnnotation(ExpectedException.class);
					Group group = method.getAnnotation(Group.class);
					TestImportance testImportance = method.getAnnotation(TestImportance.class);
					StepDefinition stepDef = method.getAnnotation(StepDefinition.class);

					// @Unit annotation is an optional for BDD
					TestUnitObjectWrapper testUnitObj;
					if (null == unit) {
						testUnitObj = new TestUnitObjectWrapper(method, false, 0, null, 0, "", false);
					} else {
						testUnitObj = new TestUnitObjectWrapper(method, unit.skip(), unit.sequence(), unit.dataprovider(), unit.testtimeout(),
								unit.bugref(), unit.dropRemainingUnitsUponFailure());
					}

					// Test Plan is an optional attribute so it can be null
					if (null != testplan) {
						testUnitObj.setTestPlanDescription(testplan.description());
						testUnitObj.setTestPlanPreparedBy(testplan.preparedBy());
						testUnitObj.setTestPlanPreparationDate(testplan.preparationDate());
						testUnitObj.setTestreviewedBy(testplan.reviewedBy());
						testUnitObj.setTestReviewDate(testplan.reviewDate());
						testUnitObj.setTestPlanBDD(testplan.bdd());
					}

					/*
						 * Store group list for each test cases.
						 * 
						 * @formatter:off
						 * Label must follow following rules
						 * <PRE>
						 * - Name is case in-sensitive (but it will stored in upper case)
						 * - Name can not have leading or trailing spaces, it will be removed
						 * - Name can not have \r or \n or \t char, it will be removed
						 * - Name can not have \ char, it will be removed
						 * </PRE>
						 * @formatter:on
						 */
					{
						if (null != group) {
							List<String> groupList = Arrays.asList(group.group());
							testUnitObj
									.setGroupList(
											groupList
													.stream().map(s -> s.toUpperCase().trim().replaceAll("\n", "").replaceAll("\r", "")
															.replaceAll("\t", "").replaceAll("\\\\", "").replaceAll("/", ""))
													.collect(Collectors.toList()));
						} else {
							// Create empty arrayList
							testUnitObj.setGroupList(new ArrayList<String>());
						}

						// each group must have * by default (which represents all
						if (!testUnitObj.getGroupList().contains("*")) {
							testUnitObj.getGroupList().add("*");
						}
					}

					// KTF is optional annotation so it can be null
					if (null != ktf) {
						testUnitObj.setKTF(ktf.ktf());
					}

					// expectedException is an optional annotation
					if (null != expectedException) {
						List<Class<? extends Throwable>> expectedExceptionsList = Arrays.asList(expectedException.expectedExceptions());
						testUnitObj.setExpectedExceptionList(expectedExceptionsList);
						testUnitObj.setExceptionContains(expectedException.contains());
						testUnitObj.setEnforce(expectedException.enforce());
					}

					// TestImportance is an optional annotation
					if (null != testImportance) {
						testUnitObj.setTestImportance(testImportance.value());
					}

					// Test Def is an optional attribute so it can be null
					if (null != stepDef) {

						String stepDefKey = stepDef.value().trim();

						// If cucumber was used to generate the argument then do the following
						if (stepDefKey.startsWith("^") && stepDefKey.endsWith("$")) {
							// Replace "([^"]*)"
							stepDefKey = stepDefKey.replaceAll("\\\"\\(\\[\\^\\\"\\]\\*\\)\\\"", "\"\"").trim();
							// Replace $
							stepDefKey = stepDefKey.replaceAll("\\$", "").trim();
							// Replace ^
							stepDefKey = stepDefKey.replaceAll("\\^", "").trim();

						} else { // otherwise do this
							// Replace anything between quotes to empty string "xyz" => ""
							stepDefKey = stepDefKey.replaceAll("\\\".*?\\\"", "\"\"").trim();
						}

						testUnitObj.setStepDefinition(stepDefKey);

						if (!stepDefinitionsMap.containsKey(testUnitObj.getStepDefinition())) {
							// Remove everything between "" and store it as a key
							stepDefinitionsMap.put(testUnitObj.getStepDefinition(), testUnitObj);
						} else {
							System.err.println("[Warning] Duplicate step : " + testUnitObj.getStepDefinition());
						}
					}

				});
	}

	public Map<String, TestUnitObjectWrapper> getStepDefinitionsMap() {
		return stepDefinitionsMap;
	}

	protected void setStepDefinitionsMap(Map<String, TestUnitObjectWrapper> stepDefinitionsMap) {
		this.stepDefinitionsMap = stepDefinitionsMap;
	}
}
