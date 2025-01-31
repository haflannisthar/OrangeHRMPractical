package OrangeHrmTests.Test.LeaveTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssignLeaveTest extends BaseTest {


//    click on button without filling any fields to check the system will show error
    @Test
    public void assignLeaveTest()  {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
   boolean actualResult=  dashboardPage.goToLeavePage().clickOnAssignLeave().leaveRequiredFieldEmptyToCheckError();
   Assert.assertTrue(actualResult,"Assign leave failed");
    }
}
