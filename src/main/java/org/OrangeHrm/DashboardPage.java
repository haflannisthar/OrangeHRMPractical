package org.OrangeHrm;

import org.Base.BasePage;
import org.OrangeHrm.Employee.EmployeePage;
import org.OrangeHrm.Leave.LeavePage;
import org.openqa.selenium.By;

import static org.Utility.WaitUtility.fluentWait;

public class DashboardPage extends BasePage {

    private final By dashboard = By.xpath("//h6[text()='Dashboard']");
    private final By PIMElement = By.xpath("//ul[@class='oxd-main-menu']//li/a[normalize-space()='PIM']");
    private final By leaveElement=By.xpath("//ul[@class='oxd-main-menu']//li/a[normalize-space()='Leave']");

//    check the dashboard text is displayed using the xpath of the h6 tag
        public boolean dashboardIsDisplayed() {
            fluentWait(dashboard,5);
            return find(dashboard).isDisplayed();
        }

//        click PIM element and go to Employee Page
        public EmployeePage goToEmployeePage() {
            fluentWait(PIMElement,5);
            click(PIMElement);
            return new EmployeePage();
        }

        public LeavePage goToLeavePage() {
            fluentWait(leaveElement,5);
            click(leaveElement);
            return new LeavePage();
        }
}
