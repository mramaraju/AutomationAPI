package com.test.api;
import java.util.ArrayList;
import com.fw.framework.infra.Runner;
import com.fw.interfaces.TestExecutable;



public class FeatureRunner {


    public static ArrayList<TestExecutable> getTestList() throws Exception {
        ArrayList<TestExecutable> tests = new ArrayList<TestExecutable>();

        // --------------------------------------------------------------------------------------------
        // TODO User May Add Test Case Manually as show in sample below
        // tests.add(new Test_123());
        // tests.add(new Test_abc());
        // --------------------------------------------------------------------------------------------

        //tests.add(new LoginVerification());


        return tests;
    }


    public static void main(String[] args) throws Exception {
        Runner runner = new Runner(FeatureRunner.class);
        //initialize test
        runner.setTestList(getTestList());
        runner.run(args);

    }


}