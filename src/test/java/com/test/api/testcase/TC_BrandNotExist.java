package com.test.api.testcase;
import com.fw.annotation.TestCase;
import com.fw.annotation.TestPlan;
import com.fw.annotation.Unit;
import com.fw.framework.infra.TestContext;
import com.fw.helper.ReadPropertyFile;
import com.fw.interfaces.TestExecutable;
import com.test.api.helper.RestHelper;


@TestPlan(preparedBy = "maheshwaran", preparationDate = "22/4/20", bdd ="Verify if Hispano Suiza brand does not exist in the used car category")
@TestCase(sequence = 3)

public class TC_BrandNotExist implements TestExecutable {

    @Unit(sequence = 1)
    public void BrandNotExist(TestContext context) throws Exception {
        RestHelper op = new RestHelper(context);
        ReadPropertyFile RF = new ReadPropertyFile();
        op.isBrandNotExist("Hispano Suiza",RF.APIUsedCarEndPoint);

    }
}