package org.OrangeHrm.Employee;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.Utility.DropDownUtility.selectValueFromDropDown;
import static org.Utility.JavaScriptUtility.clickJS;
import static org.Utility.JavaScriptUtility.scrollToElementJs;
import static org.Utility.WaitUtility.fluentWait;
import static org.Utility.WaitUtility.fluentWaitUntilPresent;

public class UpdateEmployeePage extends EmployeePage {


    private final By driverLicenseField = By.xpath("//label[contains(text(), \"License Number\")]/parent::div/following-sibling::div//input");
    private final By dateOfBirthField = By.xpath("//label[text()='Date of Birth']/parent::div/following-sibling::div//input");
    private final By personalDetailSaveButton = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-form-hint']/following-sibling::button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
    private final By attachmentAddButton = By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--text')]");
    private final By attachmentBrowseButton = By.xpath("//input[@type='file']");
    private final By commentTextArea = By.xpath("//textArea[@placeholder='Type comment here']");
    private final By attachmentSubmitButton = By.xpath("//button[@type='button' and text()=' Cancel ']/following-sibling::button");
    private final By tableCommentCell = By.xpath("//div[contains(@class, 'oxd-table-row--with-border')]/child::div[@role='cell'][3]");


    //update employee by passing details
    public String updateEmployee(String driverLicenseNumber, String countryValue, String dateOfBirth, String attachmentFilePath, String comment) throws InterruptedException {

        Thread.sleep(5000);
        fluentWait(driverLicenseField, 20);
        set(driverLicenseField, driverLicenseNumber);
        Thread.sleep(1000);


        selectValueFromDropDown("Nationality", countryValue);
        selectValueFromDropDown("Marital Status", "Single");

        Thread.sleep(1000);


        scrollToElementJs(dateOfBirthField);


        //        add expiry date for license
        clickJS(dateOfBirthField);
        Thread.sleep(1000);
        find(dateOfBirthField).clear();
        set(dateOfBirthField, dateOfBirth);

//        trigger event using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement dateInput = find(dateOfBirthField);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateInput);

        find(dateOfBirthField).sendKeys(Keys.TAB);

        Thread.sleep(1000);
        scrollToElementJs(personalDetailSaveButton);
        clickJS(personalDetailSaveButton);

        scrollToElementJs(attachmentAddButton);
        clickJS(attachmentAddButton);
        scrollToElementJs(attachmentBrowseButton);
//        clickJS(attachmentBrowseButton);

        fluentWaitUntilPresent(attachmentBrowseButton, 10);
        WebElement fileInput = find(attachmentBrowseButton);
        js.executeScript("arguments[0].style.display='block';", fileInput);
        fileInput.sendKeys(attachmentFilePath);

        Thread.sleep(3000);

        set(commentTextArea, comment);

        Thread.sleep(2000);
        click(attachmentSubmitButton);

        fluentWait(tableCommentCell, 20);


        return find(tableCommentCell).getText();


    }

}
