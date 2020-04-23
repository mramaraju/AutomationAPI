
package com.fw.framework.infra;

import java.io.File;

public class BDDFeatureObjectWrapper {

	File featureFile;
	String glue;

	public BDDFeatureObjectWrapper(File featureFile, String glue) {
		this.featureFile = featureFile;
		this.glue = glue;
	}

	public File getFeatureFile() {
		return featureFile;
	}

	public void setFeatureFile(File featureFile) {
		this.featureFile = featureFile;
	}

	public String getGlue() {
		return glue;
	}

	public void setGlue(String glue) {
		this.glue = glue;
	}

}
