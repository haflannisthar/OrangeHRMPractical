package org.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DropDownUtility extends Utility {

    public static void selectValueFromDropDown(String label, String value) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Locate the dropdown click based on the label text
        WebElement dropdownTrigger = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[normalize-space(text())='" + label.trim() + "']/parent::div/following-sibling::div//div[contains(@class, 'oxd-select-text')]")));
        dropdownTrigger.click();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Locate the option to select in the dropdown
        WebElement optionToSelect = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//div[contains(@class, 'oxd-select-dropdown')]//span[contains(text(), '" + value.trim() + "')]")));
        optionToSelect.click();


    }

}
