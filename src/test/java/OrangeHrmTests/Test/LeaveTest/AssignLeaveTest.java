package OrangeHrmTests.Test.LeaveTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.OrangeHrm.Leave.AssignLeavePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AssignLeaveTest extends BaseTest {


//  TestCase ID - TC-010 -->  click on button without filling any fields to check the system will show error
    @Test
    public void assignLeaveEmptyFieldsTest()  {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
   int actualResult=  dashboardPage.goToLeavePage().clickOnAssignLeave().leaveRequiredFieldEmptyToCheckError();
        System.out.println("actualResult: "+actualResult);
   Assert.assertEquals(actualResult,4,"Assign error message and expected result doesn't match");
   dashboardPage.goToLogoutPage().logout();
    }

//  TestCase ID - TC-011 -->  assign a leave to an employee
    @Test(dependsOnMethods = "addNewEmployeeTest")
    public void assignLeaveSuccessTest() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
       AssignLeavePage assignLeavePage= dashboardPage.goToLeavePage().clickOnAssignLeave();
      boolean actualResult=assignLeavePage.assignLeaveToEmployeeName("Ravi M B","CAN - Personal","2024-01-25");
        Assert.assertTrue(actualResult, "Assign error message and expected result doesn't match");
        dashboardPage.goToLogoutPage().logout();

    }


}
