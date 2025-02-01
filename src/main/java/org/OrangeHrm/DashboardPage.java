package org.OrangeHrm;

import org.Base.BasePage;
import org.OrangeHrm.Employee.EmployeePage;
import org.OrangeHrm.Leave.LeavePage;
import org.OrangeHrm.Recruitment.RecruitmentPage;
import org.openqa.selenium.By;

import static org.Utility.WaitUtility.fluentWait;

public class DashboardPage extends BasePage {

    private final By dashboard = By.xpath("//h6[text()='Dashboard']");
    private final By PIMElement = By.xpath("//ul[@class='oxd-main-menu']//li/a[normalize-space()='PIM']");
    private final By leaveElement=By.xpath("//ul[@class='oxd-main-menu']//li/a[normalize-space()='Leave']");
    private final By recruitmentElement=By.xpath("//ul[@class='oxd-main-menu']//li/a[normalize-space()='Recruitment']");
    private final By logoutDropDown=By.xpath("//li[@class='oxd-userdropdown']");

//    check the dashboard text is displayed using the xpath of the h6 tag
        public boolean dashboardIsDisplayed() {
            fluentWait(dashboard,20);
            return find(dashboard).isDisplayed();
        }

//        click PIM element and go to Employee Page
        public EmployeePage goToEmployeePage() {
            fluentWait(PIMElement,10);
            click(PIMElement);
            return new EmployeePage();
        }

//    click leave element and return a Leave page
        public LeavePage goToLeavePage() {
            fluentWait(leaveElement,5);
            click(leaveElement);
            return new LeavePage();
        }
// click on logout element return a logout page
        public LogoutPage goToLogoutPage() {
            fluentWait(logoutDropDown,5);
            click(logoutDropDown);
            return new LogoutPage();
        }
//click on recruitment eleent return a RecruitmentPage
        public RecruitmentPage goToRecruitmentPage() {
            fluentWait(recruitmentElement,5);
            click(recruitmentElement);
            return new RecruitmentPage();
        }

}
