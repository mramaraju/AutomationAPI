
package com.fw.framework;

/**
 * This class contains all generic enum which can be used by test framework
 * 
 * 
 *
 */
public class Enums {

	/**
	 * Enum for test status
	 * 
	 * 
	 *
	 */
	public enum TestStatus {

		// @formatter:off
		
		PASS(0),
		SKIP(1),
		KTF(2), 
		FAIL(3);
		
		// @formatter:on

		private final int status;

		TestStatus(int status) {
			this.status = status;
		}

		public int getValue() {
			return status;
		}

		public static String getEnumName(int status) {
			for (TestStatus e : TestStatus.values()) {
				if (status == e.getValue()) {
					return e.name();
				}
			}
			return null;
		}
	}

	/**
	 * Enum for exception value which can be used anywhere in test framework
	 * 
	 * 
	 *
	 */
	public enum ExceptionValue {

		// @formatter:off
		
		INVALID_LENGTH("Invalid Length"),
		INVALID_INPUT("Invalid Input"),
		OVERSIZE_OBJECT("Object is oversize"), 
		INVALID_FILEPATH("Invalid File Path"),
		INVALID_LOCATION("Invalid Location"),
		OBJECTS_ARE_NOT_EQUAL("Objects are not equal");
		
		// @formatter:on

		private final String status;

		ExceptionValue(String status) {
			this.status = status;
		}

		public String getValue() {
			return status;
		}

		public String getEnumName(String status) {
			for (ExceptionValue e : ExceptionValue.values()) {
				if (status.equals(e.getValue())) {
					return e.name();
				}
			}
			return null;
		}
	}

	public enum Importance {

		// @formatter:off
		
		FATAL(0),
		CRITICAL(1),
		HIGH(2), 
		MEDIUM(3),
		LOW(4),
		UNDEFINED(5);
		
		// @formatter:on

		private final int status;

		Importance(int status) {
			this.status = status;
		}

		public int getValue() {
			return status;
		}

		public static String getEnumName(int status) {
			for (Importance e : Importance.values()) {
				if (status == e.getValue()) {
					return e.name();
				}
			}
			return null;
		}

	}

	public enum ScriptFileType {

		// @formatter:off
		
		TEST_SCRIPT(0),
		ERROR_SCRIPT(1);
		
		// @formatter:on

		private final int status;

		ScriptFileType(int status) {
			this.status = status;
		}

		public int getValue() {
			return status;
		}

		public String getEnumName(int status) {
			for (ScriptFileType e : ScriptFileType.values()) {
				if (status == e.getValue()) {
					return e.name();
				}
			}
			return null;
		}

	}

	public enum Gherkin {

		// @formatter:off
		
		GIVEN("GIVEN"),
		AND("AND"),
		BUT("BUT"), 
		WHEN("WHEN"),
		THEN("THEN");
		
		// @formatter:on

		private final String step;

		Gherkin(String status) {
			this.step = status;
		}

		public String getValue() {
			return step;
		}

		public String getEnumName(String step) {
			for (Gherkin e : Gherkin.values()) {
				if (step.equals(e.getValue())) {
					return e.name();
				}
			}
			return null;
		}

	}

}
