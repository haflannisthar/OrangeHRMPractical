package org.OrangeHrm.Leave;

import org.OrangeHrm.DashboardPage;
import org.openqa.selenium.By;

import static org.Utility.WaitUtility.fluentWait;

public class LeavePage extends DashboardPage {

    private final By assignLeaveLiTag=By.xpath("//li/a[text()='Assign Leave']");


//    click on assign leave element and return a Assign leave page
    public AssignLeavePage clickOnAssignLeave(){
        fluentWait(assignLeaveLiTag,10);
        find(assignLeaveLiTag).click();
        return new AssignLeavePage();
    }
}
