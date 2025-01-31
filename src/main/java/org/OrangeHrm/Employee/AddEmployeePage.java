package org.OrangeHrm.Employee;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.awt.*;
import java.awt.event.KeyEvent;

import static org.Utility.JavaScriptUtility.clickJS;
import static org.Utility.JavaScriptUtility.scrollToElementJs;
import static org.Utility.WaitUtility.fluentWait;
import static org.Utility.WaitUtility.fluentWaitUntilPresent;

public class AddEmployeePage extends EmployeePage {

    private final By employeePageVerifyTag = By.xpath("//h6[contains(@class,'orangehrm-main-title')]");
    private final By employeeAddForm = By.xpath("//form[contains(@class, 'oxd-form')]");


    private final By employeeImageUploadInput = By.xpath("//input[@type='file']");
    private final By employeeFirstNameInput = By.name("firstName");
    private final By employeeMiddleNameInput = By.name("middleName");
    private final By employeeLastNameInput = By.name("lastName");


    private final By addNewEmployeeButton = By.xpath("//button[@type='submit' or contains(text(),'Save')]");
    private final By licenseExpiryDateInput = By.xpath("//label[text()='License Expiry Date']/parent::div/following-sibling::div//input");
    private final By maleRadioIcon = By.xpath("//label[contains(., 'Male')]");

    private final By personalDetailSaveButton = By.xpath("//p[@class='oxd-text oxd-text--p orangehrm-form-hint']/following-sibling::button[@class='oxd-button oxd-button--medium oxd-button--secondary orangehrm-left-space']");
    private final By employeeAddSuccessVerification = By.xpath("//div[@class='orangehrm-edit-employee-name']");





    //check whether employee form is present
    public boolean isEmployeeFormPresent() {
        fluentWait(employeeAddForm, 5);
        return find(employeeAddForm).isDisplayed();
    }

    //check whether the employee page head tag is present by getting the text
    public String getEmployeePageHeadText() {
        fluentWait(employeePageVerifyTag, 5);
        return find(employeePageVerifyTag).getText();
    }

//    add new employee
    public String newEmployeeAdd(String imagePath, String firstName, String middleName, String lastName, String expiryDate) throws InterruptedException {

//        wait until form load and verify
        fluentWait(employeeAddForm, 5);
        find(employeeAddForm).isDisplayed();
//upload the image
        fluentWaitUntilPresent(employeeImageUploadInput, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement fileInput = find(employeeImageUploadInput);
        js.executeScript("arguments[0].style.display='block';", fileInput);
        fileInput.sendKeys(imagePath);

//        enter first last and middle name
        fluentWaitUntilPresent(employeeFirstNameInput, 5);
        set(employeeFirstNameInput, firstName);
        set(employeeMiddleNameInput, middleName);
        set(employeeLastNameInput, lastName);

//        click save button
        click(addNewEmployeeButton);
        fluentWait(employeeAddSuccessVerification, 20);

//        add expiry date for license
        clickJS(licenseExpiryDateInput);
        Thread.sleep(1000);
        set(licenseExpiryDateInput, expiryDate);

//        trigger event using JS
        WebElement dateInput = find(licenseExpiryDateInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateInput);

        find(licenseExpiryDateInput).sendKeys(Keys.TAB);

//scroll to down and click male radio icon
        scrollToElementJs(maleRadioIcon);
        clickJS(maleRadioIcon);
        Thread.sleep(1000);

        //scroll further down and click save
        scrollToElementJs(personalDetailSaveButton);
        clickJS(personalDetailSaveButton);

//        get the expiry field value and return it
        return find(licenseExpiryDateInput).getAttribute("value");

    }








}
