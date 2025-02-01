package org.OrangeHrm.Recruitment;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.Utility.DropDownUtility.selectValueFromDropDown;
import static org.Utility.JavaScriptUtility.clickJS;
import static org.Utility.JavaScriptUtility.scrollToElementJs;
import static org.Utility.WaitUtility.fluentWait;
import static org.Utility.WaitUtility.fluentWaitUntilPresent;

public class AddCandidatePage extends RecruitmentPage {

    private final By firstNameField = By.name("firstName");
    private final By middleNameField = By.name("middleName");
    private final By lastNameField = By.name("lastName");
    private final By emailField = By.xpath("//label[text()='Email']/parent::div/following-sibling::div/input");
    private final By contactNumberField = By.xpath("//label[text()='Contact Number']/parent::div/following-sibling::div/input");
    private final By resumeUploadField = By.xpath("//input[@type='file']");
    private final By keywordsField = By.xpath("//label[text()='Keywords']/parent::div/following-sibling::div/input");
    private final By dateOfApplicationField = By.xpath("//label[text()='Date of Application']/parent::div/following-sibling::div//input");
    private final By notesField = By.xpath("//label[text()='Notes']/parent::div/following-sibling::div/textArea");
    private final By consentCheckBoxField = By.xpath("//i[@class='oxd-icon bi-check oxd-checkbox-input-icon']");
    private final By candidateSaveButtonField = By.xpath("//button[@type='submit' and normalize-space()='Save']");

    private final By invalidEmailErrorField = By.xpath("//span[text()='Expected format: admin@example.com']");

    private final By candidateAddSuccessValidationField = By.xpath("//h6[text()='Candidate Profile']");


    public String clearDateFieldAndAssignNewDate(String dateOfApplication) throws InterruptedException {
        fluentWait(dateOfApplicationField, 20);
        scrollToElementJs(dateOfApplicationField);
        clickJS(dateOfApplicationField);
        Thread.sleep(500);

        // Clear the existing value
        WebElement dateInput = find(dateOfApplicationField);
        dateInput.sendKeys(Keys.CONTROL + "a");
        dateInput.sendKeys(Keys.BACK_SPACE);

        // set the new date
        set(dateOfApplicationField, dateOfApplication);
        Thread.sleep(500);

        // Trigger change event using JavaScript
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateInput);

        // Move focus away from the field
        dateInput.sendKeys(Keys.TAB);

        return find(dateOfApplicationField).getAttribute("value");

    }


    //    enter email in invalid type/format to check for error message
    public String invalidEmailField(String email) {
        fluentWait(emailField, 10);
        set(emailField, email);

        return find(invalidEmailErrorField).getText();

    }


    //    add new candidate
    public boolean addNewCandidate(String firstName, String middleName, String lastName, String vacancy, String email,
                                   String contactNumber, String resumePath, String keyWords, String dateOfApplication, String notes) throws InterruptedException {
        fluentWait(firstNameField, 10);
        set(firstNameField, firstName);
        set(middleNameField, middleName);
        set(lastNameField, lastName);

        selectValueFromDropDown("Vacancy", vacancy);

        set(contactNumberField, contactNumber);
        set(emailField, email);


        fluentWaitUntilPresent(resumeUploadField, 10);
        WebElement fileInput = find(resumeUploadField);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.display='block';", fileInput);
        fileInput.sendKeys(resumePath);

        Thread.sleep(3000);

        scrollToElementJs(keywordsField);
        set(keywordsField, keyWords);

        clickJS(dateOfApplicationField);
        Thread.sleep(500);

        // Clear the existing value
        WebElement dateInput = find(dateOfApplicationField);
        dateInput.sendKeys(Keys.CONTROL + "a");
        dateInput.sendKeys(Keys.BACK_SPACE);

        // set the new date
        set(dateOfApplicationField, dateOfApplication);
        Thread.sleep(500);

        // Trigger change event using JavaScript
        js.executeScript("arguments[0].dispatchEvent(new Event('input'));", dateInput);
        js.executeScript("arguments[0].dispatchEvent(new Event('change'));", dateInput);

        // Move focus away from the field
        dateInput.sendKeys(Keys.TAB);

        scrollToElementJs(notesField);
        set(notesField, notes);

        scrollToElementJs(consentCheckBoxField);
        find(consentCheckBoxField).click();

        Thread.sleep(1000);


        scrollToElementJs(candidateSaveButtonField);
        click(candidateSaveButtonField);

        fluentWait(candidateAddSuccessValidationField, 20);
        return find(candidateAddSuccessValidationField).isDisplayed();

    }


}
