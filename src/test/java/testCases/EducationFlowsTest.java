package testCases;

import org.testng.annotations.Test;
import pageObjects.DashboardPage;
import pageObjects.EducationFlowsPage;
import testBase.BaseClass;

public class EducationFlowsTest extends BaseClass {

    @Test
    public void verifyEducationFlows() {

        login(p.getProperty("adminEmail"), p.getProperty("adminPassword"), true);

        DashboardPage dp = new DashboardPage(driver);
        dp.clickOnTogglerIcon();
        dp.clickOnHamBurgerMenuItem("Education Flows");

        EducationFlowsPage ef = new EducationFlowsPage(driver);
        ef.clickOnAdd();
        ef.enterEducationFlowName(randomString());
        ef.selectPurposeOfFlowValue(p.getProperty("purposeOfFlow"));
        ef.verifyActiveCheckbox();
        ef.verifyEducationFlowDialogueText(p.getProperty("educationFlowDialogueText"));
        ef.clickOnEducationFlowDialogueOk();
    }
}
