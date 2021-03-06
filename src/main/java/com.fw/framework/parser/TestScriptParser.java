
package com.fw.framework.parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.fw.exception.InvalidDataException;
import com.fw.framework.Enums.ScriptFileType;
import com.fw.framework.FWStaticStore;
import com.fw.framework.infra.BDDFeatureObjectWrapper;
import com.fw.framework.infra.TestObjectWrapper;

public class TestScriptParser {

	/**
	 * Reads test script and provides testSuite list back to user.
	 * 
	 * @param testScriptFile testScript formatted with XML
	 * @return list of test cases name
	 * @throws ParserConfigurationException if a DocumentBuildercannot be created which satisfies the configuration requested.
	 * @throws IOException If any IO errors occur.
	 * @throws SAXException If any parse errors occur.
	 * @throws InvalidDataException If user provides invalid data
	 */
	public List<TestSuite> readTestScript(File testScriptFile) throws ParserConfigurationException, SAXException, IOException, InvalidDataException {
		try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(testScriptFile);

			doc.getDocumentElement().normalize();

			return readTestScript(doc);
		} catch (FileNotFoundException fe) {
			System.out.println(fe.getMessage() + "\n" + "Fall back to Default Organisation values");
		}
		return null;
	}

	/**
	 * Reads test script
	 * 
	 * @param doc Document object of XML file
	 * @throws InvalidDataException thrown when invalid data is provided by user
	 */
	private List<TestSuite> readTestScript(Document doc) throws InvalidDataException {

		String version = "0";
		List<TestSuite> testSuiteList = new ArrayList<>();

		Element rootElement = doc.getDocumentElement();
		if (!"".equals(rootElement.getAttribute("version"))) {
			version = rootElement.getAttribute("version");
		}
		NodeList suiteNodeList = doc.getElementsByTagName("suite");

		for (int temp = 0; temp < suiteNodeList.getLength(); temp++) {
			TestSuite _suite = new TestSuite();
			// This is true if object is constructed using test script
			_suite.setTestScriptProvided(true);
			_suite.setVersion(version);

			Node suiteNode = suiteNodeList.item(temp);
			parseSuite(_suite, suiteNode);

			// add test suite in the list even no test list is provided
			if (null != _suite.getTestFQCNList() && _suite.isEnable()) {
				testSuiteList.add(_suite);
			}
		}

		return testSuiteList;
	}

	private void parseSuite(TestSuite _suite, Node suiteNode) throws InvalidDataException {
		if (suiteNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) suiteNode;
			if ("suite".equals(eElement.getNodeName())) {

				// if set correctly then process it or set to true by default
				String enable = eElement.getAttribute("enable").trim();
				if (enable.toLowerCase().equals("false")) {
					_suite.setEnable(false);
				} else {
					_suite.setEnable(true);
				}

				// Only allow A-Z or a-Z or 0-9 or - to be part of the name
				String suiteName = eElement.getAttribute("name").trim().replaceAll("[^A-Za-z0-9-_]", "");
				// Only allow maximum of 10 digit
				if (suiteName.length() > 10) {
					System.err.println("Warning: TestSuite name >10 char. It will be trimmed");
					suiteName = suiteName.substring(0, 10);
				}
				_suite.setSuiteName(suiteName);

				// If loop count attribute is not provided then assume 1, if
				// provided then check if it is valid
				String loopCount = eElement.getAttribute("loopcount").trim();

				if ("".equals(loopCount)) {
					_suite.setLoopCount(1);
				} else {
					try {
						int nLoopCount = Integer.parseInt(loopCount);
						if (nLoopCount <= 0) {
							// If invalid then set to 1
							nLoopCount = 1;
						}
						_suite.setLoopCount(nLoopCount);
					} catch (NumberFormatException e) {
						throw new InvalidDataException("Invalid Loop Count : " + loopCount);
					}
				}
			}

			NodeList testsNodeList = eElement.getElementsByTagName("com");
			if (testsNodeList.getLength() > 0) {
				for (int temp = 0; temp < testsNodeList.getLength(); temp++) {
					Node testsNode = testsNodeList.item(temp);
					parseTests(_suite, testsNode);
				}
			} else {
				// create empty list so null pointer exception can be avoided
				_suite.setTestFQCNList(new ArrayList<>());
			}

			NodeList featureFileNodeList = eElement.getElementsByTagName("featurefiles");
			if (featureFileNodeList.getLength() > 0) {
				for (int temp = 0; temp < featureFileNodeList.getLength(); temp++) {
					Node featureFileNode = featureFileNodeList.item(temp);
					parseFeatureFiles(_suite, featureFileNode);
				}
			} else {
				// create empty list so null pointer exception can be avoided
				_suite.setFeatureFiles(new ArrayList<>());
			}

			NodeList paramsNodeList = eElement.getElementsByTagName("parameters");
			if (paramsNodeList.getLength() > 0) {
				for (int temp = 0; temp < paramsNodeList.getLength(); temp++) {
					Node parameterNode = paramsNodeList.item(temp);
					parseParameters(_suite, parameterNode);
				}
			} else {
				// create empty map so null pointer exception can be avoided
				_suite.setTestSuiteParameters(new HashMap<>());
			}

			NodeList testGroupsNodeList = eElement.getElementsByTagName("testcasegroups");
			if (testGroupsNodeList.getLength() > 0) {
				for (int temp = 0; temp < testGroupsNodeList.getLength(); temp++) {
					Node groupsNode = testGroupsNodeList.item(temp);
					parseTestGroups(_suite, groupsNode);
				}
			} else {
				// create list with "*" so all test can be run
				_suite.setTestGroupList(new ArrayList<>());
				_suite.getTestGroupList().add("*");
			}

			NodeList unitGroupsNodeList = eElement.getElementsByTagName("testunitgroups");
			if (unitGroupsNodeList.getLength() > 0) {
				for (int temp = 0; temp < unitGroupsNodeList.getLength(); temp++) {
					Node groupsNode = unitGroupsNodeList.item(temp);
					parseTestUnitGroups(_suite, groupsNode);
				}
			} else {
				// create list with "*" so all test units can be run
				_suite.setTestUnitGroupList(new ArrayList<>());
				_suite.getTestUnitGroupList().add("*");
			}
		}
	}

	private void parseParameters(TestSuite _suite, Node parameterNode) {
		Map<String, String> parametersMap = new HashMap<String, String>();
		NodeList nChildList = parameterNode.getChildNodes();
		for (int i = 0; i < nChildList.getLength(); i++) {
			Node nChildNode = nChildList.item(i);
			if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nChildNode;
				if ("parameter".equals(eElement.getNodeName())) {
					parametersMap.put(eElement.getAttribute("name"), eElement.getTextContent());
				}
			}
		}
		_suite.setTestSuiteParameters(parametersMap);
	}

	private void parseTests(TestSuite _suite, Node testsNode) {
		List<String> testFQCNList = new ArrayList<>();
		NodeList nChildList = testsNode.getChildNodes();
		for (int i = 0; i < nChildList.getLength(); i++) {
			Node nChildNode = nChildList.item(i);
			if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nChildNode;
				if ("test".equals(eElement.getNodeName())) {
					testFQCNList.add(eElement.getAttribute("name").trim());
				}
			}
		}
		_suite.setTestFQCNList(testFQCNList);
	}

	private void parseFeatureFiles(TestSuite _suite, Node featureFileNode) {
		List<BDDFeatureObjectWrapper> featureFileList = new ArrayList<>();
		NodeList nChildList = featureFileNode.getChildNodes();
		for (int i = 0; i < nChildList.getLength(); i++) {
			Node nChildNode = nChildList.item(i);
			if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nChildNode;
				if ("file".equals(eElement.getNodeName())) {
					File f = new File("." + File.separator + "script" + File.separator + eElement.getAttribute("name").trim());
					if (f.exists() && f.isFile() && f.getName().toLowerCase().endsWith(".feature")) {
						featureFileList.add(new BDDFeatureObjectWrapper(f, ""));
					}
				}
			}
		}

		_suite.setFeatureFiles(featureFileList);
	}

	private void parseTestGroups(TestSuite _suite, Node groupsNode) {
		List<String> groupList = new ArrayList<>();
		NodeList nChildList = groupsNode.getChildNodes();
		for (int i = 0; i < nChildList.getLength(); i++) {
			Node nChildNode = nChildList.item(i);
			if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nChildNode;
				if ("group".equals(eElement.getNodeName())) {
					// Store only Upper case group names to avoid case sensitiveness
					groupList.add(eElement.getAttribute("name").trim().toUpperCase());
				}
			}
		}

		// If group list is empty then at least add * which will run all test
		// cases
		if (groupList.isEmpty()) {
			groupList.add("*");
		}

		_suite.setTestGroupList(groupList);
	}

	private void parseTestUnitGroups(TestSuite _suite, Node groupsNode) {
		List<String> groupList = new ArrayList<>();
		NodeList nChildList = groupsNode.getChildNodes();
		for (int i = 0; i < nChildList.getLength(); i++) {
			Node nChildNode = nChildList.item(i);
			if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) nChildNode;
				if ("group".equals(eElement.getNodeName())) {
					// Store only Upper case group names to avoid case sensitiveness
					groupList.add(eElement.getAttribute("name").trim().toUpperCase());
				}
			}
		}

		// If group list is empty then at least add * which will run all test
		// cases
		if (groupList.isEmpty()) {
			groupList.add("*");
		}

		_suite.setTestUnitGroupList(groupList);
	}

	public void createExecScriptFromObjWrapper(Class<?> mainClass, List<TestObjectWrapper> testList, ScriptFileType scriptType) throws Exception {
		if (null == testList) {
			return;
		}

		// Package name will be null if Runner is in root package
		String packageName = (null == mainClass.getPackage() ? "ProjectRoot" : mainClass.getPackage().getName());

		File scriptFile;
		if (scriptType == ScriptFileType.TEST_SCRIPT) {
			scriptFile = new File(FWStaticStore.TESTSCRIPT_BASE_DIR + packageName + ".xml");

			if (scriptFile.exists() && scriptFile.isFile()) {
				// If file is present then do not overwrite
				return;
			}
		} else {
			scriptFile = new File(FWStaticStore.TESTSCRIPT_BASE_DIR + "FAIL_" + packageName + ".xml");

			if (scriptFile.exists() && scriptFile.isFile()) {
				// Delete old file if present
				scriptFile.delete();
			}
		}

		// When test list is empty then do not create a file because empty file means use all test cases
		if (testList.isEmpty()) {
			return;
		}

		if (!scriptFile.getParentFile().exists()) {
			scriptFile.getParentFile().mkdirs();
		}

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		// root elements
		Document doc = docBuilder.newDocument();
		Element rootElement = doc.createElement("configuration");
		doc.appendChild(rootElement);

		Attr verAttr = doc.createAttribute("version");
		verAttr.setValue("1");
		rootElement.setAttributeNode(verAttr);

		// Organisation Info elements
		Element suite = doc.createElement("suite");
		rootElement.appendChild(suite);

		Attr attr1 = doc.createAttribute("enable");
		attr1.setValue("true");
		suite.setAttributeNode(attr1);

		Attr attr2 = doc.createAttribute("name");
		attr2.setValue("UniqueName");
		suite.setAttributeNode(attr2);

		Attr attr3 = doc.createAttribute("loopcount");
		attr3.setValue("1");
		suite.setAttributeNode(attr3);

		Comment comment = doc.createComment("java -cp \"test.jar\" unit_test.Main --testscript=\"script_name.xml\"");
		suite.getParentNode().insertBefore(comment, suite);

		createTestList(testList, doc, suite);
		createFeatureFiles(doc, suite);
		createSuiteParameters(doc, suite);
		createTestCaseGroups(doc, suite);
		createTestUnitGroups(doc, suite);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(scriptFile);

		transformer.transform(source, result);

	}

	private void createTestList(List<TestObjectWrapper> testList, Document doc, Element suite) {
		// Organisation Info elements
		Element tests = doc.createElement("com");
		suite.appendChild(tests);

		for (int i = 0; i < testList.size(); i++) {
			// add test cases
			Element property = doc.createElement("test");
			property.appendChild(doc.createTextNode(""));
			tests.appendChild(property);

			Attr attr1 = doc.createAttribute("name");
			attr1.setValue(testList.get(i).getTestClassObject().getCanonicalName());
			property.setAttributeNode(attr1);
		}
	}

	private void createFeatureFiles(Document doc, Element suite) {
		// Organisation Info elements
		Element files = doc.createElement("featurefiles");
		suite.appendChild(files);

		// add feature files
		Element property = doc.createElement("file");
		property.appendChild(doc.createTextNode(""));
		files.appendChild(property);

		Attr attr1 = doc.createAttribute("name");
		attr1.setValue("testscenarios.feature");
		property.setAttributeNode(attr1);

		Attr attr2 = doc.createAttribute("glue");
		attr2.setValue("");
		property.setAttributeNode(attr2);
	}

	private void createSuiteParameters(Document doc, Element suite) {
		// Organisation Info elements
		Element parameters = doc.createElement("parameters");
		suite.appendChild(parameters);

		for (int i = 0; i < 3; i++) {
			// add test cases
			Element property = doc.createElement("parameter");
			property.appendChild(doc.createTextNode("parameterValue_" + i));
			parameters.appendChild(property);

			Attr attr1 = doc.createAttribute("name");
			attr1.setValue("PARAMETER_" + i);
			property.setAttributeNode(attr1);
		}
	}

	private void createTestCaseGroups(Document doc, Element suite) {
		// Organisation Info elements
		Element groups = doc.createElement("testcasegroups");
		suite.appendChild(groups);

		// add test cases
		Element property = doc.createElement("group");
		property.appendChild(doc.createTextNode(""));
		groups.appendChild(property);

		Attr attr1 = doc.createAttribute("name");
		attr1.setValue("*");
		property.setAttributeNode(attr1);
	}

	private void createTestUnitGroups(Document doc, Element suite) {
		// Organisation Info elements
		Element groups = doc.createElement("testunitgroups");
		suite.appendChild(groups);

		// add test cases
		Element property = doc.createElement("group");
		property.appendChild(doc.createTextNode(""));
		groups.appendChild(property);

		Attr attr1 = doc.createAttribute("name");
		attr1.setValue("*");
		property.setAttributeNode(attr1);
	}

}
