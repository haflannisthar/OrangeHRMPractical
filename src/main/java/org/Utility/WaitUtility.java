package org.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;

public class WaitUtility extends Utility{
//    fluent wait
//    look for the element in each 500 second and this will happen for seconds value we sent

    public static void fluentWait(By locator, int seconds){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))  // Total wait time
                .pollingEvery(Duration.ofMillis(500))      // Check every 500ms
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class); // Ignore common exceptions

        fluentWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public static void fluentWaitUntilPresent(By locator, int seconds){
        Wait<WebDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(seconds))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class, StaleElementReferenceException.class);

        fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }


}
