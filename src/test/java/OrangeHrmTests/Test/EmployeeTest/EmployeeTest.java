package OrangeHrmTests.Test.EmployeeTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.OrangeHrm.Employee.AddEmployeePage;
import org.OrangeHrm.Employee.EmployeePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmployeeTest extends BaseTest {


    //  TestCase ID - TC-004 -->  enter credentials and login then go to Employee page and then check the URL with expected one
    @Test(priority = 1)
    public void EmployeePageTest() {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        String ActualUrl = employeePage.getCurrentUrl();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";


        Assert.assertEquals(ActualUrl, expectedUrl, "Expected and ActualUrl are not equal");

        dashboardPage.goToLogoutPage().logout();

    }

    //TestCase ID - TC-005 -->  go to employee page and click add employee button and then check the employee for is present
    @Test(priority = 2)
    public void addEmployeePageVerificationTest() {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        AddEmployeePage addEmployeePage = employeePage.goToAddNewEmployee();

        String actualResult = addEmployeePage.getEmployeePageHeadText();
        String expectedResult = "Add Employee";
        Assert.assertEquals(actualResult, expectedResult, "Expected and ActualResult are not equal");

        dashboardPage.goToLogoutPage().logout();


//boolean actualResult=addEmployeePage.isEmployeeFormPresent();
//
//Assert.assertTrue(actualResult,"Expected and ActualResult are not equal");

    }

    //TestCase ID - TC-006 -->    add new employee
    @Test(priority = 3)
    public void addNewEmployeeTest() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        AddEmployeePage addEmployeePage = employeePage.goToAddNewEmployee();


        String filePath = "C:\\Users\\Pc\\Downloads\\testimonial-one-img-1.e88e1b8d.png";


        String actualResult = addEmployeePage.newEmployeeAdd(filePath, "Banuka", "Wikra", "Silva", "2021-13-01");
        System.out.println(actualResult);
        Assert.assertEquals(actualResult, "2021-13-01", "Expected and ActualResult are not equal");

        dashboardPage.goToLogoutPage().logout();

    }


    //  TestCase ID - TC-008 -->  update employee details by passing the details which are needed to be applied
//    @Test(dependsOnMethods = "addNewEmployeeTest")
    @Test(dependsOnMethods = "addNewEmployeeTest")
    public void employeeDetailsUpdateTest() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();

        String attachmentFilePath = "C:\\Users\\Pc\\Downloads\\Self-Evaluation-Sheet-2024-1.pdf";
        String comment = "self evaluation form";
        String firstName = "Banuka";

        String actualResult = employeePage.goToUpdateEmployee(firstName).updateEmployee("asddj2001", "Sri Lankan", "2001-16-10", attachmentFilePath, comment);

        Assert.assertEquals(actualResult, comment, "Expected and ActualResult are not equal");


        dashboardPage.goToLogoutPage().logout();

    }

    // TestCase ID - TC-009 -->  delete an employee by searching employee id
//    (dependsOnMethods = "employeeDetailsUpdateTest")
    @Test
    public void employeeDetailsDeleteTest() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        boolean actualResult = employeePage.deleteEmployee("0315");
        Assert.assertTrue(actualResult, "Expected and ActualResult are not equal");


        dashboardPage.goToLogoutPage().logout();

    }


    //   TestCase ID - TC-007 --> check with an employee name that does not exist using search bar
    @Test(dependsOnMethods = "employeeDetailsUpdateTest")
    public void checkWithWrongEmployeeNameTest() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        boolean actualResult = employeePage.checkWithWrongEmployeeName("Dilscoop");

        Assert.assertTrue(actualResult, "Expected and ActualResult are not equal");


        dashboardPage.goToLogoutPage().logout();

    }


}
