package OrangeHrmTests.Test.LogoutTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.testng.annotations.Test;

public class LogOutTest extends BaseTest {


//    TestCase ID - TC-003 -->  logout test
    @Test
    public void testLogOut() {
        DashboardPage dashboard= loginPage.loginToApp("admin", "admin123");

        dashboard.goToLogoutPage().logout();
    }
}
