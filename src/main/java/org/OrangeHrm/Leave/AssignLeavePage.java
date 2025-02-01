package org.OrangeHrm.Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.Utility.DropDownUtility.selectValueFromDropDown;
import static org.Utility.DropDownUtility.selectValueFromInputDropDown;
import static org.Utility.JavaScriptUtility.clickJS;
import static org.Utility.WaitUtility.fluentWait;

public class AssignLeavePage extends LeavePage {


    private final By assignLeaveHeaderValue = By.xpath("//h6[text()='Assign Leave']");
    private final By employeeNameField = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/child::div/child::div/child::input");
    private final By fromDateField = By.xpath("//label[text()='From Date']/parent::div/following-sibling::div/child::div/child::div/child::input");
    private final By toDateField = By.xpath("//label[text()='To Date']/parent::div/following-sibling::div/child::div/child::div/child::input");
    private final By requiredSpanTag = By.xpath("//span[text()='Required']");
    private final By assignButton = By.xpath("//button[text()=' Assign ']");
    private final By confirmationModal = By.xpath("//div[contains(@class, 'oxd-sheet') and contains(@class, 'oxd-dialog-sheet--shadow')]");
    private final By confirmationModelButton = By.xpath("//button[@type='button' and contains(@class,'oxd-button--secondary')]");
    private final By successToast = By.xpath("//div[contains(@class, 'oxd-toast')]//p[text()='Success']/following-sibling::p[text()='Successfully Saved']");

    //check with leave all the required field empty and click assign button to see whether the all 4 error message are  shown
    public int leaveRequiredFieldEmptyToCheckError() {
        fluentWait(assignLeaveHeaderValue, 10);
        click(assignButton);

        List<WebElement> requiredFields = driver.findElements(requiredSpanTag);

        int count = 0;

        for (WebElement requiredField : requiredFields) {
            if (requiredField.isDisplayed()) {
                count++;
            }
        }


        return count;
    }

//    assign leave to an employee by passing the parameters
    public boolean assignLeaveToEmployeeName(String employeeName, String leaveType, String startDate) throws InterruptedException {
        fluentWait(employeeNameField, 10);

        selectValueFromInputDropDown("Employee Name", employeeName);
        Thread.sleep(500);
        selectValueFromDropDown("Leave Type", leaveType);
        Thread.sleep(1000);

        clickJS(fromDateField);
        Thread.sleep(1000);
        set(fromDateField, startDate);
        //        trigger event using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement fromDateInput = find(fromDateField);
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", fromDateInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", fromDateInput);

        find(fromDateField).sendKeys(Keys.TAB);


        Thread.sleep(2000);


        click(assignButton);

        fluentWait(confirmationModal, 10);
        click(confirmationModelButton);

        fluentWait(successToast, 10);
        return find(successToast).isDisplayed();


    }
}
