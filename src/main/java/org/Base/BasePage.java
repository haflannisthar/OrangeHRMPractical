package org.Base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
//    initialize driver
    public static WebDriver driver;

//    set the driver
    public static void setDriver(WebDriver driver) {
        BasePage.driver = driver;
    }

//    find the web element by locator
    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

// find the web element by locator and set text to the locator
    protected void set(By locator,String text){
       find(locator).clear();
       find(locator).sendKeys(text);
    }

//    click on web element found by locator
    protected void click(By locator){
        find(locator).click();
    }

    protected String getText(By locator){
       return find(locator).getText();

    }

}
