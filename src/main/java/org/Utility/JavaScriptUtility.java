package org.Utility;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

public class JavaScriptUtility extends Utility {


    // scroll to the element using JavaScript
    public static void scrollToElementJs(By locator) {
        // Find the  element
        WebElement element = driver.findElement(locator);
//        scroll the element into view
        String jsScript = "arguments[0].scrollIntoView();";
        // Execute the JavaScript to scroll to the element
        ((JavascriptExecutor) driver).executeScript(jsScript, element);
    }

    //  click on an element using JavaScript
    public static void clickJS(By locator) {
        // Find  element
        WebElement element = driver.findElement(locator);

        JavascriptExecutor executor = (JavascriptExecutor) driver;
        // Execute the JavaScript to click the element
        executor.executeScript("arguments[0].click()", element);
    }

}
