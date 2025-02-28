package pageObjects.AdminAccount.AccountDetailsModules;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pageObjects.BasePage;
import utilities.CommonUtils;
import java.util.ArrayList;
import java.util.List;

public class OnlineEducationPage extends BasePage {

    CommonUtils commonUtils;

    public OnlineEducationPage(WebDriver driver) {
        super(driver);
        this.commonUtils = new CommonUtils(driver);
    }

    public String experiencesForProvisionedOrdersRadioButton = "//input[@id='provisioned_experience']";
    public String manageButton = "//a[@id='accountExperienceLink']";
    public String addButton = "(//a[normalize-space()='Add'])[1]";
    public String experienceNameField = "//input[@id='exp_name']";
    public String attachBundleDropDown = "//select[@id='bundle_id']";
    public String saveButton = "//button[@id='addFlowSubmit']";
    public String electronicTableRows = "//div[@id='show_electronics_adoc_experience']//table//tbody/tr";
    public String poTableRows = "//div[@id='show_provisioned_order_experience']//table//tbody//tr";
    public String experiencesForElectronicText = "//label[normalize-space()='Experiences for Electronic and Ad-hoc Bundles']";
    public String experiencesForProvisionedOrdersText = "//label[normalize-space()='Experiences for Provisioned Orders']";
    public String purchaserExpProvisionedOrderRadioButton = "provisioned_experience"; //id
    public String electronicAndAdhocRadioButton = "adoc_experience"; //id


    public List<String[]> getTableRowData(String tableRows) {
        List<String[]> rowDataList = new ArrayList<>();
        List<WebElement> rows = commonUtils.findElementsByXpath(tableRows);

        // Check if the table contains any actual data rows
        if (rows.size() == 1) { // Only header row is present, hence no data
            WebElement noDataMessage = rows.get(0).findElement(By.xpath("td"));
            if (noDataMessage.getText().contains("No Education Sessions Purchased")) {
                System.out.println("No data available.");
                return rowDataList; // Return an empty list if no data is present
            }
        }

        // Loop through all rows except the header and check if data exists
        for (WebElement row : rows) {
            // Ensure that the row is not the "No Education Sessions Purchased" message
            String cellText = row.getText();

            if (!cellText.contains("No Education Sessions Purchased")) {
                String friendlyName = row.findElement(By.xpath("td[1]")).getText();
                String bundleName = row.findElement(By.xpath("td[2]")).getText();
                String educationFlowName = row.findElement(By.xpath("td[3]")).getText();

                System.out.println(friendlyName);
                System.out.println(bundleName);
                System.out.println(educationFlowName);

                rowDataList.add(new String[]{friendlyName, bundleName, educationFlowName});
            }
        }

        return rowDataList;
    }


    public boolean validateDataInPurchaserAccount(String rows, List<String[]> rowDataList) {
        // Check if there's any data to validate
        if (rowDataList.isEmpty()) {
            System.out.println("No data to validate.");
            return true; // Nothing to validate, consider this as valid
        }

        // Assuming we're on the purchaser account page now
        List<WebElement> purchaserRows = commonUtils.findElementsByXpath(rows);

        // Loop through the rows to validate each one
        for (int i = 1; i < rowDataList.size(); i++) {
            WebElement purchaserRow = purchaserRows.get(i);
            String purchaserFriendlyName = purchaserRow.findElement(By.xpath("td[1]")).getText();
            String purchaserBundleName = purchaserRow.findElement(By.xpath("td[2]")).getText();
            String purchaserEducationFlowName = purchaserRow.findElement(By.xpath("td[3]")).getText();

            String[] originalRowData = rowDataList.get(i);
            String storedFriendlyName = originalRowData[0];
            String storedBundleName = originalRowData[1];
            String storedEducationFlowName = originalRowData[2];

            System.out.println("Purchaser Row " + (i + 1) + ":");
            System.out.println(storedFriendlyName + " == " + purchaserFriendlyName);
            System.out.println(storedBundleName + " == " + purchaserBundleName);
            System.out.println(storedEducationFlowName + " == " + purchaserEducationFlowName);

            // Compare the data
            if (!purchaserFriendlyName.equals(storedFriendlyName) ||
                    !purchaserBundleName.equals(storedBundleName) ||
                    !purchaserEducationFlowName.equals(storedEducationFlowName)) {
                System.out.println("Row " + i + " data mismatch detected on purchaser account page.");
                return false;
            }
        }
        System.out.println("All data is consistent on the purchaser account page.");
        return true;
    }
}