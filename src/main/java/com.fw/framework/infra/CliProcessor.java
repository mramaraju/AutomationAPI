
package com.fw.framework.infra;

import java.io.File;
import java.io.PrintWriter;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import com.fw.framework.FWStaticStore;

/** Process Command Line arguments */
public class CliProcessor {

	private static File testScriptFile = null;
	private static String profile = null;

	/**
	 * Processes command line arguments
	 *
	 * @param args Command line arguments
	 */
	protected static void proessCommandLine(String[] args) {

		if (args.length <= 0) {
			return;
		}

		// create the command line parser
		CommandLineParser parser = new DefaultParser();

		// Create Option
		Option testScript = Option.builder("t").required(false).longOpt("testscript")
				.desc("use test script to drive test : --testscript=\"./scripts/sampletest.xml\"").hasArg().build();
		Option version = Option.builder("v").required(false).longOpt("version").desc("Version of FW").build();
		Option help = Option.builder("h").required(false).longOpt("help").desc("Command line help.")
				.build();
		Option contributors = Option.builder("c").required(false).longOpt("contributors").desc("Project Contributors name").build();
		Option testProfile = Option.builder("p").required(false).longOpt("profile").desc("Test Configuration Profile").hasArg().build();

		// Add Option
		Options options = new Options();
		options.addOption(testScript);
		options.addOption(version);
		options.addOption(help);
		options.addOption(contributors);
		options.addOption(testProfile);

		// Process Options
		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			// validate that block-size has been set

			if (line.hasOption("profile")) {
				profile = line.getOptionValue("profile");
			}
			// Store test script path if provided
			if (line.hasOption("testscript")) {
				PrintWriter pw = new PrintWriter(System.out);
				// TestScript path should be "./script/testscript.xml"
				String testScriptPath = "." + File.separator + "script" + File.separator + line.getOptionValue("testscript");
				File tscriptFile = new File(testScriptPath);
				if (tscriptFile.exists() && tscriptFile.isFile()) {
					testScriptFile = tscriptFile;
					System.err.println("TestScript found : " + tscriptFile.getAbsolutePath());
					pw.flush();
				} else {
					System.err.println("TestScript missing : " + tscriptFile.getAbsolutePath());
					pw.flush();
					System.exit(1);
				}

			}
		} catch (ParseException exp) {
			// Do not do anything, just continue
			System.out.println(exp.getMessage());
		}
	}

	public static File getTestScriptFile() {
		return testScriptFile;
	}

	protected static void setTestScriptFile(File testScriptFile) {
		CliProcessor.testScriptFile = testScriptFile;
	}

	public static String getProfile() {
		return profile;
	}

	protected static void setProfile(String profile) {
		CliProcessor.profile = profile;
	}

}
