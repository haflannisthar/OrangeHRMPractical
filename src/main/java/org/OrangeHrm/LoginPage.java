package org.OrangeHrm;

import org.Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.Utility.WaitUtility.fluentWait;

public class LoginPage extends BasePage {


//    find element in the webpage
    private final By usernameField=By.name("username");
    private final By passwordField=By.name("password");
    private final By loginButton=By.xpath("//button[@type='submit']");
    private final By errorMessageElement =By.xpath("//p[text()='Invalid credentials']");
    private final By dashboardText = By.xpath("//h6[text()='Dashboard']");



    //    set Username
     public void setUsername(String username){
         fluentWait(usernameField,60);
      set(usernameField,username);
     }

//     set password
     public void setPassword(String password){
         fluentWait(passwordField,60);
         set(passwordField,password);
     }

//     click login button and will return the dashboard
    public DashboardPage clickLoginButton(){
         fluentWait(loginButton,60);
         click(loginButton);
         return new DashboardPage();
    }

//    get the error message
    public   String getErrorMessage(){
         fluentWait(errorMessageElement,60);
            return find(errorMessageElement).getText();
    }

//enter login data and click button and return to dashboard
    public DashboardPage loginToApp(String username, String password){
        new WebDriverWait(driver, Duration.ofSeconds(60))
                .until(ExpectedConditions.presenceOfElementLocated(usernameField));
        fluentWait(usernameField,60);

        set(usernameField,username);
         set(passwordField,password);
         click(loginButton);

         return new DashboardPage();
    }



}
