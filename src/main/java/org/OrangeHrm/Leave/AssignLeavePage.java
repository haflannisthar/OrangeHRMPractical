package org.OrangeHrm.Leave;

import org.openqa.selenium.By;

import static org.Utility.WaitUtility.fluentWait;

public class AssignLeavePage extends LeavePage{


    private final By assignLeaveHeaderValue=By.xpath("//h6[text()='Assign Leave']");
    private final By employeeNameField=By.xpath("//label[text()='Employee Name']/parent::div/following-sibling::div/child::div/child::div/child::input");
    private final By fromDateField=By.xpath("//label[text()='From Date']/parent::div/following-sibling::div/child::div/child::div/child::input");
    private final By toDateField=By.xpath("//label[text()='To Date']/parent::div/following-sibling::div/child::div/child::div/child::input");
    private final By requiredSpanTag=By.xpath("//span[text()='Required']");
    private final By assignButton=By.xpath("//button[text()=' Assign ']");

//check with leave all the required field empty and click assign button to see whether the error message will be shown
    public boolean leaveRequiredFieldEmptyToCheckError(){
        fluentWait(assignLeaveHeaderValue,10);
        click(assignButton);
      return find(requiredSpanTag).isDisplayed();
    }
}
