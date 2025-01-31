package OrangeHrmTests.Test.LoginTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {



//    when user enters wrong password system will show an error message

    @Test
    public void loginTestErrorMessage() {
        loginPage.setUsername("admin");
        loginPage.setPassword("admin1234");
        loginPage.clickLoginButton();
      String actualErrorMessage= loginPage.getErrorMessage();
      String expectedErrorMessage="Invalid credentials";

      Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Login Failed");
    }

//    enter username and password and then user will send to dashboard and will check for the visibility of dashboard text
    @Test
    public void loginTestSuccess() {
      DashboardPage dashboard= loginPage.loginToApp("admin", "admin123");
     boolean actualResult= dashboard.dashboardIsDisplayed();
//      boolean expectedResult=true;
      Assert.assertTrue(actualResult,"Login Successful");
    }
}
