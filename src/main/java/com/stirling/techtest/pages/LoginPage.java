package com.stirling.techtest.pages;

import com.stirling.techtest.basepage.BasePage;
import com.stirling.techtest.utils.PagesProperties;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.logging.Logger;


public class LoginPage extends BasePage {
    private static final Logger log = Logger.getLogger(LoginPage.class.getName());

    String baseURL = PagesProperties.INSTANCE.getURL();
    String username = PagesProperties.INSTANCE.getUsername();
    String password = PagesProperties.INSTANCE.getPassword();

    @FindBy(css = "#username")
    private WebElement userNameField;

    @FindBy(css = "#password")
    private WebElement passwordField;

    @FindBy(css = "#login")
    private WebElement loginButton;

    @FindBy(css = "#uservoice-activation")
    private WebElement dataUserID;

    @FindBy(xpath = "//a[contains(@href,'Logout')]")
    private WebElement logoutButton;

    @FindBy(xpath = "//h3")
    private WebElement logoutHeadding;


    public LoginPage() {
        waitForPageToLoad();
        PageFactory.initElements(webDriver, this);
    }

    public void doLogin(){
        webDriver.get(baseURL);
        waitForPageToLoad();
        Assert.assertTrue(userNameField.isDisplayed(),"Login Form not available.");
        userNameField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
        waitForPageToLoad();
        Assert.assertTrue(dataUserID.isDisplayed(),"Login not successful.");
    }

    public void doLogout(){
        dataUserID.click();
        logoutButton.click();
        Assert.assertTrue(logoutHeadding.isDisplayed(),"Logout is not successfull.");
    }
}
