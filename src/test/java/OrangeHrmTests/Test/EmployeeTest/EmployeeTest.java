package OrangeHrmTests.Test.EmployeeTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.OrangeHrm.Employee.AddEmployeePage;
import org.OrangeHrm.Employee.EmployeePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.IOException;

public class EmployeeTest extends BaseTest {


    //    enter credentials and login then go to Employee page and then check the URL with expected one
    @Test
    public void EmployeePageTest() {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        String ActualUrl = employeePage.getCurrentUrl();
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList";


        Assert.assertEquals(ActualUrl, expectedUrl, "Expected and ActualUrl are not equal");

    }

    //go to employee page and click add employee button and then check the employee for is present
    @Test
    public void addEmployeePageVerificationTest() {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        AddEmployeePage addEmployeePage = employeePage.addNewEmployee();

        String actualResult = addEmployeePage.getEmployeePageHeadText();
        String expectedResult = "Add Employee";
        Assert.assertEquals(actualResult, expectedResult, "Expected and ActualResult are not equal");

//boolean actualResult=addEmployeePage.isEmployeeFormPresent();
//
//Assert.assertTrue(actualResult,"Expected and ActualResult are not equal");

    }

    //    add new employee
    @Test
    public void addNewEmployee() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        AddEmployeePage addEmployeePage = employeePage.addNewEmployee();


//                String imagePath = "C:/Users/Pc/Downloads/testimonial-one-img-1.e88e1b8d.png";

        String filePath = "C:\\Users\\Pc\\Downloads\\testimonial-one-img-1.e88e1b8d.png";


        String actualResult = addEmployeePage.newEmployeeAdd(filePath, "Sanga", "Wikra", "Silva", "2021-30-01");
        System.out.println(actualResult);
        Assert.assertEquals(actualResult, "2021-30-01", "Expected and ActualResult are not equal");
    }


//    check with an employee name that does not exist using search bar
    @Test
    public void checkWithWrongEmployeeName() throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
       boolean actualResult= employeePage.checkWithWrongEmployeeName("Manju");

       Assert.assertTrue(actualResult, "Expected and ActualResult are not equal");

    }



//    update employee details by passing the details which are needed to be applied
    @Test
    public void employeeDetailsUpdate()throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();

        String attachmentFilePath="C:\\Users\\Pc\\Downloads\\Self-Evaluation-Sheet-2024-1.pdf";
        String comment="self evaluation form";

        String actualResult=employeePage.updateEmployee("Amelia","asddj2001","Sri Lankan","2001-16-10",attachmentFilePath,comment);

        Assert.assertEquals(actualResult, comment, "Expected and ActualResult are not equal");

    }

    @Test
    public void employeeDetailsDelete()throws InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
       boolean actualResult=employeePage.deleteEmployee("1235");
       Assert.assertTrue(actualResult, "Expected and ActualResult are not equal");

    }

    @Test
    public void selectFromDropDown() throws IOException, InterruptedException {
        DashboardPage dashboardPage = loginPage.loginToApp("admin", "admin123");
        EmployeePage employeePage = dashboardPage.goToEmployeePage();
        employeePage.selectEmploymentStatus("Full-Time Permanent");
        employeePage.selectEmploymentStatus("Full-Time Contract");
        employeePage.selectEmploymentStatus("Freelance");
        employeePage.selectEmploymentStatus("Full-Time Probation");
        employeePage.selectEmploymentStatus("Part-Time Contract");
    }

}
