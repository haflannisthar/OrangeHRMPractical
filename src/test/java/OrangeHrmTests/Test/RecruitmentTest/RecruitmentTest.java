package OrangeHrmTests.Test.RecruitmentTest;

import OrangeHrmTests.Base.BaseTest;
import org.OrangeHrm.DashboardPage;
import org.OrangeHrm.Recruitment.AddCandidatePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RecruitmentTest extends BaseTest {

//    TestCase ID - TC-012 -->  enter invalid email and check the field will show an error or not
    @Test
    public void checkForInvalidEmailTest(){
        DashboardPage dashboardPage= loginPage.loginToApp("admin","admin123");
       AddCandidatePage addCandidatePage= dashboardPage.goToRecruitmentPage().goToAddCandidatePage();
       String actualTest=addCandidatePage.invalidEmailField("haflan.com");

        Assert.assertEquals(actualTest,"Expected format: admin@example.com","actual format is different from expected");

        dashboardPage.goToLogoutPage().logout();
    }


// TestCase ID - TC-013 -->  add a candidate successfully
    @Test
    public void addNewCandidateTest() throws InterruptedException {
        DashboardPage dashboardPage= loginPage.loginToApp("admin","admin123");

        String firstName="Arshada";
        String middleName="Davida";
        String lastName="Harshada";
        String vacancy="Software Engineer";
        String email="arshada@example.com";
        String contactNo="07772555253";
        String resumePath="C:\\Users\\Pc\\Downloads\\Self-Evaluation-Sheet-2024-1.pdf";
        String keyWords="Java,Spring boot,Mysql";
        String dateOfApplication="2024-10-30";
        String notes="joined newly so need to keep an eye on him to help";


     boolean actualResult= dashboardPage.goToRecruitmentPage().goToAddCandidatePage().addNewCandidate(
            firstName,middleName,lastName,vacancy,email,contactNo,resumePath,keyWords,dateOfApplication,notes
                              );


     Assert.assertTrue(actualResult,"Actual result doesn't match the expected result");

     dashboardPage.goToLogoutPage().logout();
    }


}
