
package com.fw.framework.parser;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AuthSettingsParser {

	File fXmlFile;
	Map<String, UserAuthParametersParser> authMap;

	public AuthSettingsParser(File emailAuthSettingFilePath) {
		this.fXmlFile = emailAuthSettingFilePath;
		this.authMap = new HashMap<>();

		readXMLConfig();
	}

	private void readXMLConfig() {
		try {
			if (!fXmlFile.exists() || !fXmlFile.isFile()) {
				throw new Exception("File does not exist : " + fXmlFile.getAbsolutePath());
			}

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			readUsersAuth(doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void readUsersAuth(Document doc) {
		// System.out.println("Root element :" +
		// doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("user");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			UserAuthParametersParser userAuth = new UserAuthParametersParser();
			String id = null;
			Node nNode = nList.item(temp);

			NodeList nChildList = nNode.getChildNodes();
			for (int i = 0; i < nChildList.getLength(); i++) {
				Node nChildNode = nChildList.item(i);

				if (nChildNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nChildNode;
					if ("id".equals(eElement.getTagName())) {
						id = eElement.getTextContent();
					}
					if ("username".equals(eElement.getTagName())) {
						userAuth.setUserName(eElement.getTextContent());
					}
					if ("password".equals(eElement.getTagName())) {
						userAuth.setPassword(eElement.getTextContent());
					}
				}
			}
			authMap.put(id, userAuth);
		}
	}
	
	public UserAuthParametersParser getAuthParametersByID(String id){
		return getAuthMap().get(id);
	}

	public Map<String, UserAuthParametersParser> getAuthMap() {
		return authMap;
	}

	public void setAuthMap(Map<String, UserAuthParametersParser> authMap) {
		this.authMap = authMap;
	}
}
