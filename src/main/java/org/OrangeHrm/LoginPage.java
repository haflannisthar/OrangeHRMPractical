package org.OrangeHrm;

import org.Base.BasePage;
import org.openqa.selenium.By;

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
         fluentWait(usernameField,5);
      set(usernameField,username);
     }

//     set password
     public void setPassword(String password){
         fluentWait(passwordField,5);
         set(passwordField,password);
     }

//     click login button and will return the dashboard
    public DashboardPage clickLoginButton(){
         fluentWait(loginButton,5);
         click(loginButton);
         return new DashboardPage();
    }

    public   String getErrorMessage(){
         fluentWait(errorMessageElement,5);
            return find(errorMessageElement).getText();
    }


    public DashboardPage loginToApp(String username, String password){
         fluentWait(usernameField,5);
         set(usernameField,username);
         set(passwordField,password);
         click(loginButton);

         return new DashboardPage();
    }



}
