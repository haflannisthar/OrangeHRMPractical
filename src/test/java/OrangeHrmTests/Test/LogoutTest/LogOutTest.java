package OrangeHrmTests.Test.LogoutTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {


//    TestCase ID - TC-003 -->  logout test
    @Test
    public void testLogOut() {
        DashboardPage dashboard= loginPage.loginToApp("admin", "admin123");

       String actualURL= dashboard.goToLogoutPage().logout();
       String expectedUrl="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

       Assert.assertEquals(actualURL,expectedUrl,"Actual URL and Expected URL don't match");
    }
}
