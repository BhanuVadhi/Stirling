package com.stirling.techtest.basepage;

import com.stirling.techtest.utils.DriverUtils;
import com.stirling.techtest.utils.PagesProperties;
import com.stirling.techtest.utils.SeleniumUtils;
import org.openqa.selenium.support.events.EventFiringWebDriver;

public class BasePage {

    protected EventFiringWebDriver webDriver;
    private SeleniumUtils seleniumUtils;
    public PagesProperties pagesProperties;

    public BasePage() {
        this.webDriver = DriverUtils.getWebDriver();
        seleniumUtils= new SeleniumUtils ();
    }


    public void closeDriver() {
        seleniumUtils.closeDriver();
    }

    public void waitForPageToLoad() {
        seleniumUtils.waitForPageToLoad();
    }

}
