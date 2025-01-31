package org.OrangeHrm.Employee;

import org.OrangeHrm.DashboardPage;
import org.openqa.selenium.*;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

import static org.Utility.DropDownUtility.selectValueFromDropDown;
import static org.Utility.JavaScriptUtility.clickJS;
import static org.Utility.JavaScriptUtility.scrollToElementJs;
import static org.Utility.WaitUtility.fluentWait;
import static org.Utility.WaitUtility.fluentWaitUntilPresent;

public class EmployeePage extends DashboardPage {



    private final By EmployeeAddButtonElement=By.xpath("//button[@type='button' and text()=' Add ']");

    private final By searchInputFieldByEmployeeName=By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/child::div/child::div/input");
    private final By searchInputFieldByEmployeeID=By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private final By searchButton=By.xpath("//button[@type='submit']");
    private final By noRecordFoundText=By.xpath("//span[text()='No Records Found']");
    private final By employeeTableBody=By.className("oxd-table-body");
    private final By clickUpdateEmployeeIcon=By.xpath("//div[contains(@class, 'oxd-table-cell-actions')]/button[1]");
    private final By clickDeleteEmployeeIcon=By.xpath("//div[contains(@class, 'oxd-table-cell-actions')]/button[2]");
    private final By driverLicenseField = By.xpath("//label[contains(text(), \"License Number\")]/parent::div/following-sibling::div//input");
    private final By dateOfBirthField=By.xpath("//label[text()='Date of Birth']/parent::div/following-sibling::div//input");
    private final By personalDetailSaveButton = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-form-hint']/following-sibling::button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
    private final By attachmentAddButton=By.xpath("//button[contains(@class, 'oxd-button') and contains(@class, 'oxd-button--text')]");
    private final By attachmentBrowseButton=By.xpath("//input[@type='file']");
    private final By commentTextArea=By.xpath("//textArea[@placeholder='Type comment here']");
    private final By attachmentSubmitButton=By.xpath("//button[@type='button' and text()=' Cancel ']/following-sibling::button");
    private final By tableCommentCell=By.xpath("//div[contains(@class, 'oxd-table-row--with-border')]/child::div[@role='cell'][3]");


    private final By deleteModal=By.xpath("//div[contains(@class, 'oxd-sheet') and contains(@class, 'oxd-dialog-sheet--shadow')]");
    private final By deleteButton=By.xpath("//button[@type='button' and contains(@class,'oxd-button--label-danger')]");


//    get the current URL
    public String getCurrentUrl(){
      return   driver.getCurrentUrl();
    }

//click add employee and send to add employee page
    public AddEmployeePage addNewEmployee(){
        fluentWait(EmployeeAddButtonElement,5);
        click(EmployeeAddButtonElement);
        return new AddEmployeePage();

    }
    //    check the search function with wrong employee name
    public boolean checkWithWrongEmployeeName(String firstName) throws InterruptedException {
        fluentWait(searchInputFieldByEmployeeName,10);
        set(searchInputFieldByEmployeeName, firstName);
        click(searchButton);
        Thread.sleep(2000);
        scrollToElementJs(noRecordFoundText);
        fluentWait(noRecordFoundText,10);
        return find(noRecordFoundText).isDisplayed();

    }

//update employee by passing details
    public String updateEmployee(String firstName,String driverLicenseNumber,String countryValue,String dateOfBirth,String attachmentFilePath ,String comment)throws InterruptedException {
        fluentWait(searchInputFieldByEmployeeName,10);
        set(searchInputFieldByEmployeeName, firstName);
        click(searchButton);
        Thread.sleep(2000);
        scrollToElementJs(employeeTableBody);
//        fluentWait(noRecordFoundText,10);
        click(clickUpdateEmployeeIcon);
        Thread.sleep(5000);
        fluentWait(driverLicenseField,20);
        set(driverLicenseField,driverLicenseNumber);
        Thread.sleep(1000);


        selectValueFromDropDown("Nationality",countryValue);
        selectValueFromDropDown("Marital Status","Single");

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

        set(commentTextArea,comment);

        Thread.sleep(2000);
        click(attachmentSubmitButton);

        fluentWait(tableCommentCell,20);



        return find(tableCommentCell).getText();



    }

//delete employee using employee id and delete then check for table heading (span) tag with "No Records Found" text
    public  boolean deleteEmployee(String employeeID) throws InterruptedException {
        fluentWait(searchInputFieldByEmployeeID,10);
        set(searchInputFieldByEmployeeID, employeeID);
        click(searchButton);
        Thread.sleep(2000);
        scrollToElementJs(employeeTableBody);
        click(clickDeleteEmployeeIcon);
        fluentWait(deleteModal,10);
        click(deleteButton);
//        Thread.sleep(2000);
//        scrollToElementJs(noRecordFoundText);
        fluentWait(noRecordFoundText,10);
        return find(noRecordFoundText).isDisplayed();
    }



//click the dropdown and open it and then wait for some time to load the dropdown options and then match the option and click it
    public void selectEmploymentStatus(String status) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'oxd-select-text')]")));
        dropdownTrigger.click();

        try { Thread.sleep(500); } catch (InterruptedException e) { e.printStackTrace(); }

//        WebElement dropdownContainer = wait.until(ExpectedConditions.presenceOfElementLocated(
//                By.xpath("//div[contains(@class, 'oxd-select-dropdown')]")));


        WebElement optionToSelect = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'oxd-select-dropdown')]//span[contains(text(), '" + status.trim() + "')]")));

        optionToSelect.click();

        boolean isTextUpdated = wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.xpath("//div[contains(@class, 'oxd-select-text-input')]"), status));

        if (isTextUpdated) {
            System.out.println("Successfully selected employment status: " + status);
        } else {
            System.out.println("Failed to select employment status.");
        }
    }
}
