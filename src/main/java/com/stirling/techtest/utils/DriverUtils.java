package com.stirling.techtest.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;


public class DriverUtils {

    private static final Logger log = Logger.getLogger(DriverUtils.class.getName());
    private static String os = System.getProperty("os.name").toLowerCase();
    private static String userPath=System.getProperty ("user.dir");
    private static String home=System.getProperty ("user.home");
    private static final String DRIVER_BASE_PATH = userPath + "/src/test/resources/drivers/";
    private static int implicitWaitTime = 0;


    public static EventFiringWebDriver webdriver;

    static String getOs() {
        return os;
    }

    static int getImplicitWaitTime() {
        return implicitWaitTime;
    }

    static void setImplicitWaitTime(int implicitWaitTime) {
        DriverUtils.implicitWaitTime = implicitWaitTime;
        webdriver.manage ().timeouts ().implicitlyWait (implicitWaitTime, TimeUnit.SECONDS);
    }

    public static EventFiringWebDriver getWebDriver() {
        webdriver=DriverUtils.getDriverInstance ();
        log.info ("Creating new driver instance");
        return webdriver;
    }

    private static EventFiringWebDriver getDriverInstance() {
        log.info("OS detected: "+ os);
        File BROWSER_DRIVER;
        RemoteWebDriver driver = null;
        String driverPath ="";

        HashMap<String, Object> chromePrefs= new HashMap<>();
        chromePrefs.put ("profile.default_content_settings.popups", 0);
        ChromeOptions options=new ChromeOptions ();
        log.info("Initializing Chrome Driver through 'WebDriverManager'");
        WebDriverManager.globalConfig().setTargetPath(DRIVER_BASE_PATH);
        WebDriverManager.chromedriver().clearPreferences();
        WebDriverManager.chromedriver().setup();
        driverPath = WebDriverManager.chromedriver().getBinaryPath();

        options.addArguments ("no-sandbox");
        options.addArguments ("--start-maximized");
        options.setExperimentalOption ("prefs", chromePrefs);
        options.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
        options.setCapability(ChromeOptions.CAPABILITY, options);
        System.setProperty ("webdriver.chrome.silentOutput", "true");

        driver=new ChromeDriver (options);

        Capabilities cap2= driver.getCapabilities ();
        log.info ("Chrome Browser Name    :" + " " + cap2.getBrowserName ());
        log.info ("Chrome Browser Version    :" + " " + cap2.getVersion ());
        log.info ("Browser Driver Path    :" + " " + driverPath);

        webdriver = new EventFiringWebDriver(driver);
        webdriver.manage ().window ().maximize ();
        setImplicitWaitTime(5);
        return webdriver;
    }
}
