
package com.fw.framework;

import java.io.File;

import com.fw.framework.parser.FrameworkConfigParser;

/**
 * This class provides container which holds all static element of test framework
 */
public class FWStaticStore {

	public static final String TOOL_NAME = "FW";

	// default decoration
	// used for encapsulating headers or separate sections
	public static final String FW_LINE_BREAK_1 = "*************************************************************************";
	// used to lightly separate sections within same test cases (Example: test units)
	public static final String FW_LINE_BREAK_2 = ".........................................................................";
	// Warning/Highlights
	public static final String FW_LINE_BREAK_3 = "=========================================================================";
	// Fail Stamp
	public static final String FW_TEST_FAIL_STAMP = FW_LINE_BREAK_3
			+ "\n=============================== FAIL HERE ===============================\n" + FW_LINE_BREAK_3;
	// KTF TEST PASSED Stamp
	public static final String FW_KTF_TEST_PASSED_STAMP = FW_LINE_BREAK_3
			+ "\n============================ KTF TEST PASSED ============================\n" + FW_LINE_BREAK_3;
	// KTF TEST UNIT PASSED Stamp
	public static final String FW_KTF_TESTUNIT_PASSED_STAMP = FW_LINE_BREAK_3
			+ "\n========================== KTF TEST UNIT PASSED =========================\n" + FW_LINE_BREAK_3;
	// DataProvider Fail Stamp
	public static final String FW_DATAPROVIDER_FAIL_STAMP = FW_LINE_BREAK_3 + "\n=== DataProvider Method failed to return data ===\n"
			+ FW_LINE_BREAK_3;
	// Stop on Fail Stamp
	public static final String FW_STOP_ON_FAIL_STAMP = FW_LINE_BREAK_3
			+ "\n====================== STOP ON FAIL IS ACTIVE ==========================="
			+ "\n================== REMAINING TESTS WILL BE SKIPPED ======================\n" + FW_LINE_BREAK_3;
	// Drop units on Fail Stamp
	public static final String FW_DROP_EXECUTION_UPON_UNIT_FAIL_STAMP = FW_LINE_BREAK_3
			+ "\n========== DROP REMAINING UNITS UPON FAILURE IS TRIGGERED ==============="
			+ "\n================== REMAINING UNITS WILL BE DROPPED ======================\n" + FW_LINE_BREAK_3;
	// Drop tests on Fail Stamp
	public static final String FW_DROP_EXECUTION_UPON_TEST_FAIL_STAMP = FW_LINE_BREAK_3
			+ "\n========== DROP REMAINING TESTS UPON FAILURE IS TRIGGERED ==============="
			+ "\n================== REMAINING TESTS WILL BE DROPPED ======================\n" + FW_LINE_BREAK_3;
	// Dependency requirements are not met
	public static final String FW_TEST_DEPENDENCY_REQ_NOT_MET = FW_LINE_BREAK_3
			+ "\n================ DEPENDENCY REQUIREMENTS ARE NOT MET ===================="
			+ "\n===================== TEST CASE WILL BE SKIPPED =========================\n" + FW_LINE_BREAK_3;
	// Dependency requirements are not met
	public static final String FW_UNIT_DEPENDENCY_REQ_NOT_MET = FW_LINE_BREAK_3
			+ "\n================ DEPENDENCY REQUIREMENTS ARE NOT MET ===================="
			+ "\n===================== TEST UNIT WILL BE SKIPPED =========================\n" + FW_LINE_BREAK_3;

	// default paths
	public static final String TESTSCRIPT_BASE_DIR = "." + File.separator + "script" + File.separator;
	public static final String CONFIG_BASE_DIR = "." + File.separator + "conf" + File.separator;
	public static final String TEMPLATE_BASE_DIR = "." + File.separator + "template" + File.separator;
	public static final String LOG_BASE_DIR = "." + File.separator + "reporting" + File.separator;
	public static final String JUNIT_REPORT_BASE_DIR = "." + File.separator + "reporting" + File.separator + "JUnitReport" + File.separator;

	// Must be kept after default paths initialised
	public static FrameworkConfigParser frameworkConfig = null;
	public static SystemProperties systemProperties = new SystemProperties();
	//public static final String FW_BUILD_VERSION = new Version().getBuildVersion();
	//public static final String FW_BUILD_DATE = new Version().getBuildDate();

	// Global Hashmap key name
	public static final String GLOBAL_ANNOTATED_TEST_MAP = "ANNOTATED_TEST_MAP";

	public static void logDebug(String log) {
		if (frameworkConfig.isenableDebug()) {
			System.err.println("[DEBUG] " + log);
		}
	}

}
