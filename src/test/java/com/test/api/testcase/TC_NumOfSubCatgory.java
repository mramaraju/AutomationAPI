package com.test.api.testcase;

import com.fw.annotation.TestCase;
import com.fw.annotation.TestPlan;
import com.fw.annotation.Unit;
import com.fw.framework.infra.TestContext;
import com.fw.helper.ReadPropertyFile;
import com.fw.interfaces.TestExecutable;
import com.test.api.helper.RestHelper;


@TestPlan(preparedBy = "maheshwaran", preparationDate = "22/4/20", bdd = "Verify the current number of car listed in the kia category")
@TestCase(sequence = 5)

public class TC_NumOfSubCatgory implements TestExecutable {

    @Unit(sequence = 1)
    public void NumOfSubCategory(TestContext context) throws Exception {
        RestHelper op = new RestHelper(context);
        ReadPropertyFile RF = new ReadPropertyFile();
        op.getNumOfSubCats(76, RF.APIUsedCarEndPoint);

    }
}