package org.OrangeHrm.Employee;

import org.OrangeHrm.DashboardPage;
import org.openqa.selenium.*;

import static org.Utility.JavaScriptUtility.scrollToElementJs;
import static org.Utility.WaitUtility.fluentWait;

public class EmployeePage extends DashboardPage {


    private final By EmployeeAddButtonElement = By.xpath("//button[@type='button' and text()=' Add ']");

    private final By searchInputFieldByEmployeeName = By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/child::div/child::div/input");
    private final By searchInputFieldByEmployeeID = By.xpath("//label[text()='Employee Id']/parent::div/following-sibling::div/input");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By noRecordFoundText = By.xpath("//span[text()='No Records Found']");
    private final By employeeTableBody = By.className("oxd-table-body");
    private final By clickDeleteEmployeeIcon = By.xpath("//div[contains(@class, 'oxd-table-cell-actions')]/button[2]");

    private final By clickUpdateEmployeeIcon = By.xpath("//div[contains(@class, 'oxd-table-cell-actions')]/button[1]");


    private final By deleteConfirmationModal = By.xpath("//div[contains(@class, 'oxd-sheet') and contains(@class, 'oxd-dialog-sheet--shadow')]");
    private final By deleteConfirmationButton = By.xpath("//button[@type='button' and contains(@class,'oxd-button--label-danger')]");


    //    get the current URL
    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }

    //click add employee and send to add employee page
    public AddEmployeePage goToAddNewEmployee() {
        fluentWait(EmployeeAddButtonElement, 5);
        click(EmployeeAddButtonElement);
        return new AddEmployeePage();

    }


    //    click edit icon and send to employee edit page
    public UpdateEmployeePage goToUpdateEmployee(String firstName) throws InterruptedException {
        fluentWait(searchInputFieldByEmployeeName, 10);
        set(searchInputFieldByEmployeeName, firstName);
        click(searchButton);
        Thread.sleep(2000);
        scrollToElementJs(employeeTableBody);
//        fluentWait(noRecordFoundText,10);
        click(clickUpdateEmployeeIcon);

        return new UpdateEmployeePage();
    }

    //    check the search function with wrong employee name
    public boolean checkWithWrongEmployeeName(String firstName) throws InterruptedException {
        fluentWait(searchInputFieldByEmployeeName, 10);
        set(searchInputFieldByEmployeeName, firstName);
        click(searchButton);
        Thread.sleep(2000);
        scrollToElementJs(noRecordFoundText);
        fluentWait(noRecordFoundText, 10);
        return find(noRecordFoundText).isDisplayed();

    }


    //delete employee using employee id and delete then check for table heading (span) tag with "No Records Found" text
    public boolean deleteEmployee(String employeeID) throws InterruptedException {
        fluentWait(searchInputFieldByEmployeeID, 10);
        set(searchInputFieldByEmployeeID, employeeID);
        click(searchButton);
        Thread.sleep(2000);
        scrollToElementJs(employeeTableBody);
        click(clickDeleteEmployeeIcon);
        fluentWait(deleteConfirmationModal, 10);
        click(deleteConfirmationButton);
//        Thread.sleep(2000);
//        scrollToElementJs(noRecordFoundText);
        fluentWait(noRecordFoundText, 10);
        return find(noRecordFoundText).isDisplayed();
    }


}
