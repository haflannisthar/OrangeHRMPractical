package org.OrangeHrm.Recruitment;

import org.OrangeHrm.DashboardPage;
import org.openqa.selenium.By;

import static org.Utility.WaitUtility.fluentWait;

public class RecruitmentPage extends DashboardPage {

    private final By addCandidateButton=By.xpath("//button[@type='button' and normalize-space()='Add']");

//click on add candidate button and return a AddCandidate Page
    public AddCandidatePage goToAddCandidatePage(){
        fluentWait(addCandidateButton,10);
        click(addCandidateButton);
        return new AddCandidatePage();
    }


}
