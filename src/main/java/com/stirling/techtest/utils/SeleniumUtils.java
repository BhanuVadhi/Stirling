package com.stirling.techtest.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;


public class SeleniumUtils {
    private static final Logger log = LogManager.getLogger(SeleniumUtils.class);

    private static EventFiringWebDriver webDriver;
    private static final Integer WAIT_TIMEOUT=30;
    private static final String userPath=System.getProperty ("user.dir");



    public SeleniumUtils() {
        this.webDriver=DriverUtils.getWebDriver ();
    }


    public static void closeDriver() {
        try{
            Runtime. getRuntime(). exec("taskkill /F /IM chrome.exe /T");
        }catch (Exception e){
            log.debug(e);
        }

    }

    public void waitForPageToLoad() {
        new WebDriverWait(webDriver,  WAIT_TIMEOUT).until (new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(final WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript ("return document.readyState").equals ("complete");

            }
        });
    }



}
