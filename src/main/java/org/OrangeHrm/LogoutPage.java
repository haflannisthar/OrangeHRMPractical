package org.OrangeHrm;

import org.openqa.selenium.By;

import static org.Utility.WaitUtility.fluentWait;

public class LogoutPage extends DashboardPage{

    private final By logoutButton=By.xpath("//li/a[text()='Logout']");

//click on logout page
    public String logout(){
        fluentWait(logoutButton,10);
        find(logoutButton).click();
     return   driver.getCurrentUrl();

    }
}
