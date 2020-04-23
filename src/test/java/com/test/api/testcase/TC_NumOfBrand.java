package com.test.api.testcase;

import com.fw.annotation.TestCase;
import com.fw.annotation.TestPlan;
import com.fw.annotation.Unit;
import com.fw.framework.infra.TestContext;
import com.fw.helper.ReadPropertyFile;
import com.fw.interfaces.TestExecutable;
import com.test.api.helper.RestHelper;


@TestPlan(preparedBy = "maheshwaran", preparationDate = "22/4/20", bdd = "Verify how many named brands of used cars are available in the TradeMe UsedCars category.")
@TestCase(sequence = 4)


public class TC_NumOfBrand implements TestExecutable {

    @Unit(sequence = 1)
    public void NumOfBrand(TestContext context) throws Exception {
        RestHelper op = new RestHelper(context);
        ReadPropertyFile RF = new ReadPropertyFile();
        op.getNumOfBrand("Kia", RF.APIUsedCarEndPoint);

    }
}